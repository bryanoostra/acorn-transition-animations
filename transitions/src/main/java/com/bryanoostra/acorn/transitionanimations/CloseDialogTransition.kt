package com.bryanoostra.acorn.transitionanimations

import android.view.ViewGroup
import android.widget.FrameLayout
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.transition.SceneTransition

class CloseDialogTransition(
    private val durationMs: Long? = null,
    private val viewController: (ViewGroup) -> ViewController
) : SceneTransition {

    override fun execute(parent: ViewGroup, callback: SceneTransition.Callback) {
        val dialogLayout: FrameLayout? = parent.findViewById(R.id.dialogLayout)

        val newViewResult = viewController(parent)

        if (dialogLayout == null) {
            parent.addView(newViewResult.view)
        }

        val duration = durationMs ?: parent.resources.getInteger(android.R.integer.config_longAnimTime).toLong()

        callback.attach(newViewResult)

        parent.doOnPreDraw {
            dialogLayout.let {
                if (it != null) {
                    it.animate()
                        .alpha(0f)
                        .setDuration(duration)
                        .withEndAction {
                            parent.removeView(dialogLayout)
                            callback.onComplete(newViewResult)
                        }
                } else {
                    callback.onComplete(newViewResult)
                }
            }
        }
    }
}
