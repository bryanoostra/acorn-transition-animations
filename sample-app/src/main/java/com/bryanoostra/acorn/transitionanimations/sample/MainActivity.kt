package com.bryanoostra.acorn.transitionanimations.sample

import com.nhaarman.acorn.android.AcornAppCompatActivity
import com.nhaarman.acorn.android.navigation.NavigatorProvider
import com.nhaarman.acorn.android.presentation.ViewControllerFactory
import com.nhaarman.acorn.android.transition.SceneTransitionFactory

class MainActivity : AcornAppCompatActivity() {

    override fun provideNavigatorProvider(): NavigatorProvider {
        return DefaultNavigatorProvider()
    }

    override fun provideTransitionFactory(viewControllerFactory: ViewControllerFactory): SceneTransitionFactory = defaultSceneTransitionFactory
}
