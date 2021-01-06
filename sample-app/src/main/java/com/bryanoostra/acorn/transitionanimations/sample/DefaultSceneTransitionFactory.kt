package com.bryanoostra.acorn.transitionanimations.sample

import com.bryanoostra.acorn.transitionanimations.SlideInLeftTransition
import com.nhaarman.acorn.android.presentation.ViewControllerFactory
import com.nhaarman.acorn.android.transition.SceneTransition
import com.nhaarman.acorn.android.transition.SceneTransitionFactory
import com.nhaarman.acorn.navigation.TransitionData
import com.nhaarman.acorn.presentation.Scene

class DefaultSceneTransitionFactory(
    private val viewControllerFactory: ViewControllerFactory
) : SceneTransitionFactory {

    private val transitions = listOf(
        RedScene::class to BlueScene::class,
        BlueScene::class to RedScene::class,
    )

    override fun supports(previousScene: Scene<*>, newScene: Scene<*>, data: TransitionData?): Boolean {
        return (previousScene::class to newScene::class) in transitions
    }

    override fun transitionFor(previousScene: Scene<*>, newScene: Scene<*>, data: TransitionData?): SceneTransition {
        return SlideInLeftTransition { parent ->
            viewControllerFactory.viewControllerFor(newScene, parent)
        }
    }
}
