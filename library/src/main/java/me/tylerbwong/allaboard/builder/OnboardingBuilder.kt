package me.tylerbwong.allaboard.builder

import android.app.Activity
import android.support.annotation.ColorRes
import android.support.v4.app.Fragment
import android.view.ViewGroup
import me.tylerbwong.allaboard.view.Onboarding
import me.tylerbwong.allaboard.view.Page

fun Activity.onboarding(setup: OnboardingBuilder.() -> Unit = {}): Onboarding {
    val builder = OnboardingBuilder(this)
    builder.setup()
    return builder.build()
}

fun Fragment.onboarding(setup: OnboardingBuilder.() -> Unit = {}): Onboarding {
    val fragmentActivity = activity ?: throw IllegalStateException("Fragment is not attached to an Activity!")
    val builder = OnboardingBuilder(fragmentActivity)
    builder.setup()
    return builder.build()
}

@OnboardingMarker
class OnboardingBuilder(activity: Activity) {
    @ColorRes
    var backgroundColor: Int? = null
    var showIndicator: Boolean = true
    private val pages: MutableList<Page> = mutableListOf()
    private var onFinishHandler: (() -> Unit)? = null

    private val rootView: ViewGroup = activity.findViewById(android.R.id.content)

    internal fun build(): Onboarding {
        return Onboarding(rootView, backgroundColor, showIndicator, pages, onFinishHandler)
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
