package me.tylerbwong.allaboard.dsl.builder

import android.app.Activity
import me.tylerbwong.allaboard.dsl.OnboardingMarker

fun Activity.onboarding(setup: OnboardingBuilder.() -> Unit = {}) {
    val builder = OnboardingBuilder(this)
    builder.setup()
    return builder.build()
}

@OnboardingMarker
class OnboardingBuilder(val activity: Activity) {
    var showIndicator: Boolean = true
    var showSkip: Boolean = true

    internal fun build() {

    }
}
