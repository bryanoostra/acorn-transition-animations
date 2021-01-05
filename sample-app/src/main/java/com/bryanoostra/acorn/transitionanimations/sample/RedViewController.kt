package com.bryanoostra.acorn.transitionanimations.sample

import android.view.View
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.presentation.Container

interface RedContainer : Container

class RedViewController(
    override val view: View
) : RedContainer,
    ViewController
