package com.bryanoostra.acorn.transitionanimations.sample

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.presentation.Container

interface BlueContainer : Container {
    fun setTransitionListener(listener: TransitionListener)
}

class BlueViewController(
    override val view: View
) : BlueContainer,
    ViewController {

    private var transitionListener: TransitionListener? = null

    override fun setTransitionListener(listener: TransitionListener) {
        transitionListener = listener
    }

    init {
        view.findViewById<AppCompatButton>(R.id.slideInTop)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.SlideInTop) }
        view.findViewById<AppCompatButton>(R.id.slideInRight)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.SlideInRight) }
        view.findViewById<AppCompatButton>(R.id.slideInBottom)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.SlideInBottom) }
        view.findViewById<AppCompatButton>(R.id.slideInLeft)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.SlideInLeft) }
        view.findViewById<AppCompatButton>(R.id.fadeInAlpha)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.FadeInAlpha) }
        view.findViewById<AppCompatButton>(R.id.fadeOutInAlpha)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.FadeOutInAlpha) }
    }
}
