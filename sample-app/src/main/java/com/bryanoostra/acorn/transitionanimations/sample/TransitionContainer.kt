package com.bryanoostra.acorn.transitionanimations.sample

import android.view.View
import android.widget.TextView
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.presentation.Container

interface TransitionContainer : Container {
    fun setTransitionListener(listener: TransitionListener)
}

class TransitionViewController(
    override val view: View
) : TransitionContainer,
    ViewController {

    private var transitionListener: TransitionListener? = null

    override fun setTransitionListener(listener: TransitionListener) {
        transitionListener = listener
    }

    init {
        view.findViewById<TextView>(R.id.slideInTop)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.SlideInTop) }
        view.findViewById<TextView>(R.id.slideInRight)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.SlideInRight) }
        view.findViewById<TextView>(R.id.slideInBottom)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.SlideInBottom) }
        view.findViewById<TextView>(R.id.slideInLeft)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.SlideInLeft) }
        view.findViewById<TextView>(R.id.fadeInAlpha)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.FadeInAlpha) }
        view.findViewById<TextView>(R.id.fadeOutInAlpha)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.FadeOutInAlpha) }
        view.findViewById<TextView>(R.id.staggeredLeft)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.StaggeredLeft) }
        view.findViewById<TextView>(R.id.staggeredRight)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.StaggeredRight) }
        view.findViewById<TextView>(R.id.popIn)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.PopIn) }
        view.findViewById<TextView>(R.id.flip)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.Flip) }
        view.findViewById<TextView>(R.id.openDialog)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.OpenDialog) }
        view.findViewById<TextView>(R.id.closeDialog)
            .setOnClickListener { transitionListener?.transitionWith(TransitionSelector.Transition.CloseDialog) }
    }
}
