package com.bryanoostra.acorn.transitionanimations

import android.view.ViewGroup
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.transition.SceneTransition

class SlideInRightTransition(
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

        newView.apply {
            translationX = sceneWidth
        }

        callback.attach(newViewResult)

        parent.doOnPreDraw {

            originalChildren.forEach {
                it?.animate()?.translationXBy(-sceneWidth)
            }

            newView.animate()
                .translationX(0f)
                .setDuration(parent.resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
                .withEndAction {

                    originalChildren.forEach { child -> parent.removeView(child) }

                    if (shouldClearBackground) {
                        newView.background = null
                    }

                    callback.onComplete(newViewResult)
                }
        }
    }
}
