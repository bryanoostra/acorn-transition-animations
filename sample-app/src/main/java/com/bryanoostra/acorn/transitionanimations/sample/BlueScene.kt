package com.bryanoostra.acorn.transitionanimations.sample

import android.view.ViewGroup
import com.nhaarman.acorn.android.presentation.ProvidesView
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.util.inflate
import com.nhaarman.acorn.presentation.BasicScene
import com.nhaarman.acorn.state.SceneState

class BlueScene(
    private val listener: Events,
    savedState: SceneState?
) : BasicScene<TransitionContainer>(savedState),
    ProvidesView {

    override fun attach(v: TransitionContainer) {
        super.attach(v)
        v.setTransitionListener(object : TransitionListener {
            override fun transitionWith(transition: TransitionSelector.Transition) {
                listener.transition(transition)
            }
        })
    }

    override fun createViewController(parent: ViewGroup): ViewController {
        return TransitionViewController(parent.inflate(R.layout.blue_scene))
    }

    interface Events {
        fun transition(transition: TransitionSelector.Transition)
    }
}
