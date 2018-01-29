package me.tylerbwong.allaboard.dsl.builder

import me.tylerbwong.allaboard.dsl.OnboardingMarker

fun OnboardingBuilder.onFinish(setup: OnboardingBuilder.() -> Unit = {}) {

}

@OnboardingMarker
class OnFinishBuilder {
    var onFinish: (() -> Unit)? = null
}