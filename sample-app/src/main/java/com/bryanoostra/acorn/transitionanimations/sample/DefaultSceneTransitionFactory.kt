package com.bryanoostra.acorn.transitionanimations.sample

import com.bryanoostra.acorn.transitionanimations.CloseDialogTransition
import com.bryanoostra.acorn.transitionanimations.FadeInAlphaTransition
import com.bryanoostra.acorn.transitionanimations.FadeOutInAlphaTransition
import com.bryanoostra.acorn.transitionanimations.FlipTransition
import com.bryanoostra.acorn.transitionanimations.OpenDialogTransition
import com.bryanoostra.acorn.transitionanimations.PopInTransition
import com.bryanoostra.acorn.transitionanimations.SlideInBottomTransition
import com.bryanoostra.acorn.transitionanimations.SlideInLeftTransition
import com.bryanoostra.acorn.transitionanimations.SlideInRightTransition
import com.bryanoostra.acorn.transitionanimations.SlideInTopTransition
import com.bryanoostra.acorn.transitionanimations.StaggeredLeftTransition
import com.bryanoostra.acorn.transitionanimations.StaggeredRightTransition
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.CloseDialog
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.FadeInAlpha
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.FadeOutInAlpha
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.Flip
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.OpenDialog
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.PopIn
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.SlideInBottom
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.SlideInLeft
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.SlideInRight
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.SlideInTop
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.StaggeredLeft
import com.bryanoostra.acorn.transitionanimations.sample.TransitionSelector.Transition.StaggeredRight
import com.nhaarman.acorn.android.presentation.ViewControllerFactory
import com.nhaarman.acorn.android.transition.SceneTransition
import com.nhaarman.acorn.android.transition.SceneTransitionFactory
import com.nhaarman.acorn.navigation.TransitionData
import com.nhaarman.acorn.presentation.Scene

class DefaultSceneTransitionFactory(
    private val viewControllerFactory: ViewControllerFactory,
    private val transitionSelector: TransitionSelector,
) : SceneTransitionFactory {

    override fun supports(previousScene: Scene<*>, newScene: Scene<*>, data: TransitionData?): Boolean {
        return true
    }

    override fun transitionFor(previousScene: Scene<*>, newScene: Scene<*>, data: TransitionData?): SceneTransition {
        return when (transitionSelector.selectedTransition) {
            SlideInBottom -> SlideInBottomTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            SlideInTop -> SlideInTopTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            SlideInLeft -> SlideInLeftTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            SlideInRight -> SlideInRightTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            FadeInAlpha -> FadeInAlphaTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            FadeOutInAlpha -> FadeOutInAlphaTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            StaggeredLeft -> StaggeredLeftTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            StaggeredRight -> StaggeredRightTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            PopIn -> PopInTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            Flip -> FlipTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            OpenDialog -> OpenDialogTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
            CloseDialog -> CloseDialogTransition(durationMs = 5000) { parent -> viewControllerFactory.viewControllerFor(newScene, parent) }
        }
    }
}
