package me.tylerbwong.allaboard.builder

import android.app.Activity
import android.view.ViewGroup
import me.tylerbwong.allaboard.Onboarding
import me.tylerbwong.allaboard.Page

fun Activity.onboarding(setup: OnboardingBuilder.() -> Unit = {}): Onboarding {
    val builder = OnboardingBuilder(this)
    builder.setup()
    return builder.build()
}

@OnboardingMarker
class OnboardingBuilder(val activity: Activity) {
    var showIndicator: Boolean = true
    var showSkip: Boolean = true
    private val pages: MutableList<Page> = mutableListOf()
    private var onFinishHandler: (() -> Unit)? = null

    private val rootView: ViewGroup = activity.findViewById(android.R.id.content)

    internal fun build(): Onboarding {
        val result = Onboarding()
        return result
    }

    /**
     * A shadowing method to prevent nesting [onboarding] calls.
     * (Credits to hotkey for this solution http://stackoverflow.com/a/43470027/4465208)
     */
    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR, message = "Onboardings can't be nested.")
    fun onboarding(param: () -> Unit = {}) {
    }

    fun onFinish(handler: () -> Unit) {
        onFinishHandler = handler
    }

    internal fun addPage(page: Page) = pages.add(page)
}
