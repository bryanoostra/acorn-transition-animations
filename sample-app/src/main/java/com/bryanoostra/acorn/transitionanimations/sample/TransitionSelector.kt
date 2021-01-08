package com.bryanoostra.acorn.transitionanimations.sample

class TransitionSelector {

    var selectedTransition = TransitionSelector.Transition.SlideInLeft

    enum class Transition {
        SlideInBottom,
        SlideInTop,
        SlideInLeft,
        SlideInRight,
        FadeInAlpha,
        FadeOutInAlpha,
        StaggeredLeft,
        StaggeredRight,
        PopIn,
        Flip,
    }
}
