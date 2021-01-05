package com.bryanoostra.acorn.transitionanimations.sample

import com.nhaarman.acorn.android.navigation.AbstractNavigatorProvider
import com.nhaarman.acorn.state.NavigatorState

class DefaultNavigatorProvider : AbstractNavigatorProvider<DefaultNavigator>() {

    override fun createNavigator(savedState: NavigatorState?): DefaultNavigator {
        return DefaultNavigator(savedState)
    }
}
