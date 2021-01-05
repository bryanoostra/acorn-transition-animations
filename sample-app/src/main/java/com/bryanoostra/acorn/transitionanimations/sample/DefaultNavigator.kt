package com.bryanoostra.acorn.transitionanimations.sample

import com.nhaarman.acorn.navigation.ReplacingNavigator
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.NavigatorState
import com.nhaarman.acorn.state.SceneState
import kotlin.reflect.KClass

class DefaultNavigator(
    savedState: NavigatorState?,
) : ReplacingNavigator(savedState) {

    override fun initialScene(): Scene<out Container> {
        return RedScene(RedSceneListener(), null)
    }

    private inner class RedSceneListener : RedScene.Events

    override fun instantiateScene(sceneClass: KClass<out Scene<*>>, state: SceneState?): Scene<out Container> {
        error("Not supported")
    }
}