package com.bryanoostra.acorn.transitionanimations

import android.view.View
import android.view.ViewGroup
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.transition.SceneTransition

class FlipTransition(
    private val viewController: (ViewGroup) -> ViewController
) : SceneTransition {

    override fun execute(parent: ViewGroup, callback: SceneTransition.Callback) {
        val originalChildren = (0 until parent.childCount).map { parent.getChildAt(it)!! }

        val newViewResult = viewController(parent)

        val newView = newViewResult.view
        parent.addView(newView)

        val shouldClearBackground = newView.background == null
        if (newView.background == null) newView.applyWindowBackground()

        val duration = parent.resources.getInteger(android.R.integer.config_longAnimTime).toLong()

        newView.apply {
            visibility = View.INVISIBLE
        }

        callback.attach(newViewResult)

        parent.doOnPreDraw {

            parent.animate()
                .scaleX(0f)
                .setDuration(duration / 2)
                .withEndAction {
                    originalChildren.forEach { child -> parent.removeView(child) }

                    if (shouldClearBackground) {
                        newView.background = null
                    }

                    newView.visibility = View.VISIBLE

                    parent.animate()
                        .scaleX(1f)
                        .setDuration(duration / 2)
                        .withEndAction {
                            callback.onComplete(newViewResult)
                        }
                }
        }
    }
}
