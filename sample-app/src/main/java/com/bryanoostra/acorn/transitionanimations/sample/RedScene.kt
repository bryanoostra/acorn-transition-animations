package com.bryanoostra.acorn.transitionanimations.sample

import android.view.ViewGroup
import com.nhaarman.acorn.android.presentation.ProvidesView
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.util.inflate
import com.nhaarman.acorn.presentation.BasicScene
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.state.SceneState

class RedScene(
    private val listener: Events,
    savedState: SceneState?
) : BasicScene<Container>(savedState),
    ProvidesView {

    override fun createViewController(parent: ViewGroup): ViewController {
        return RedViewController(parent.inflate(R.layout.red_scene))
    }

    interface Events
}