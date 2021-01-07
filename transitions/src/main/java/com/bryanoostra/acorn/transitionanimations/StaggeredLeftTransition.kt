package com.bryanoostra.acorn.transitionanimations

import android.view.View
import android.view.ViewGroup
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.transition.SceneTransition

class StaggeredLeftTransition(
    private val viewController: (ViewGroup) -> ViewController
) : SceneTransition {

    override fun execute(parent: ViewGroup, callback: SceneTransition.Callback) {
        val originalChildren = (0..parent.childCount).map { parent.getChildAt(it) }

        val newViewResult = viewController(parent)

        val newView = newViewResult.view
        parent.addView(newView)

        val shouldClearBackground = newView.background == null
        if (newView.background == null) newView.applyWindowBackground()

        val sceneWidth = parent.width.toFloat()
        val sceneHeight = parent.height.toFloat()

        val duration = parent.resources.getInteger(android.R.integer.config_longAnimTime).toLong()

        newView.apply {
            translationX = sceneWidth
        }

        callback.attach(newViewResult)

        val allChildren = originalChildren.resolveAllChildren()

        parent.doOnPreDraw {

            allChildren.forEach { view ->
                view.animate()
                    .translationXBy(-sceneWidth)
                    .setStartDelay((duration / 2 * view.y / sceneHeight).toLong())
                    .setDuration(duration / 2)
            }

            newView.animate()
                .translationX(0f)
                .setStartDelay(duration / 2)
                .setDuration(duration / 2)
                .withEndAction {

                    originalChildren.forEach { child -> parent.removeView(child) }

                    if (shouldClearBackground) {
                        newView.background = null
                    }

                    callback.onComplete(newViewResult)
                }
        }
    }

    private fun List<View?>.resolveAllChildren(): List<View> {
        return flatMap { parent ->
            when (parent) {
                is ViewGroup -> (0..parent.childCount).map { parent.getChildAt(it) }.resolveAllChildren()
                is View -> listOf(parent)
                else -> emptyList()
            }
        }
    }
}
