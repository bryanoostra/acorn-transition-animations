package com.bryanoostra.acorn.transitionanimations

import android.view.ViewGroup
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.transition.SceneTransition

class FadeOutInAlphaTransition(
    private val viewController: (ViewGroup) -> ViewController
) : SceneTransition {

    override fun execute(parent: ViewGroup, callback: SceneTransition.Callback) {
        val originalChildren = (0..parent.childCount).map { parent.getChildAt(it) }

        val newViewResult = viewController(parent)

        val newView = newViewResult.view
        parent.addView(newView)

        val shouldClearBackground = newView.background == null
        if (newView.background == null) newView.applyWindowBackground()

        newView.apply {
            alpha = 0f
        }

        val duration = parent.resources.getInteger(android.R.integer.config_longAnimTime).toLong()

        callback.attach(newViewResult)

        parent.doOnPreDraw {

            parent.animate()
                .alpha(0f)
                .setDuration(duration / 2)
                .withEndAction {
                    originalChildren.forEach { child -> parent.removeView(child) }

                    if (shouldClearBackground) {
                        newView.background = null
                    }

                    newView.alpha = 1f

                    parent.animate()
                        .alpha(1f)
                        .setDuration(duration / 2)
                        .withEndAction {
                            callback.onComplete(newViewResult)
                        }
                }
        }
    }
}
