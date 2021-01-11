package com.bryanoostra.acorn.transitionanimations

import android.view.ViewGroup
import android.widget.FrameLayout
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.transition.SceneTransition
import com.nhaarman.acorn.android.util.inflateView

class OpenDialogTransition(
    private val durationMs: Long? = null,
    private val viewController: (ViewGroup) -> ViewController
) : SceneTransition {

    override fun execute(parent: ViewGroup, callback: SceneTransition.Callback) {
        val existingDialog: FrameLayout? = parent.findViewById(R.id.dialogLayout)

        val dialogLayout = existingDialog ?: parent.inflateView(R.layout.dialog_layout) as FrameLayout

        val container = dialogLayout.findViewById<FrameLayout>(R.id.container)
        container.removeAllViews()

        if (existingDialog == null) {
            parent.addView(dialogLayout)
        }

        val newViewResult = viewController(container)
        val newView = newViewResult.view
        container.addView(newView)

        callback.attach(newViewResult)

        val duration = durationMs ?: parent.resources.getInteger(android.R.integer.config_longAnimTime).toLong()

        dialogLayout.apply {
            alpha = 0f
        }

        parent.doOnPreDraw {

            dialogLayout.animate()
                .alpha(1f)
                .setDuration(duration)
                .withEndAction {
                    callback.onComplete(newViewResult)
                }
        }
    }
}
