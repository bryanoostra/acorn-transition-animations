package com.bryanoostra.acorn.transitionanimations.sample

import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import com.nhaarman.acorn.android.presentation.ProvidesView
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.util.inflate
import com.nhaarman.acorn.presentation.BasicScene
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.state.SceneState

class BlueScene(
    private val listener: Events,
    savedState: SceneState?
) : BasicScene<Container>(savedState),
    ProvidesView {

    override fun onStart() {
        super.onStart()
        Handler(Looper.getMainLooper())
            .postDelayed(
                {
                    listener.transition()
                },
                1000
            )
    }

    override fun createViewController(parent: ViewGroup): ViewController {
        return BlueViewController(parent.inflate(R.layout.blue_scene))
    }

    interface Events {
        fun transition()
    }
}
