package com.bryanoostra.acorn.transitionanimations

import android.view.ViewGroup
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.transition.SceneTransition

class PopInTransition(
    private val durationMs: Long? = null,
    private val viewController: (ViewGroup) -> ViewController
) : SceneTransition {

    override fun execute(parent: ViewGroup, callback: SceneTransition.Callback) {
        val originalChildren = (0..parent.childCount).map { parent.getChildAt(it) }

        val newViewResult = viewController(parent)

        val newView = newViewResult.view
        parent.addView(newView)

        val shouldClearBackground = newView.background == null
        if (newView.background == null) newView.applyWindowBackground()

        val duration = durationMs ?: parent.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        newView.apply {
            scaleX = 0f
            scaleY = 0f
        }

        callback.attach(newViewResult)

        parent.doOnPreDraw {

            newView.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(duration)
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
