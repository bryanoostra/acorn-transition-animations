package com.bryanoostra.acorn.transitionanimations.sample

import android.view.View
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.presentation.Container

interface BlueContainer : Container

class BlueViewController(
    override val view: View
) : BlueContainer,
    ViewController
