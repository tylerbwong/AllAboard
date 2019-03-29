package me.tylerbwong.allaboard.builder

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import me.tylerbwong.allaboard.view.OnboardingView
import me.tylerbwong.allaboard.view.PageNavigator
import me.tylerbwong.allaboard.view.PageView

fun Activity.onboarding(setup: OnboardingBuilder.() -> Unit): PageNavigator {
    val builder = OnboardingBuilder(this)
    builder.setup()
    return builder.build()
}

fun Fragment.onboarding(setup: OnboardingBuilder.() -> Unit): PageNavigator {
    val fragmentActivity = activity
            ?: throw IllegalStateException("Fragment is not attached to an Activity!")
    val builder = OnboardingBuilder(fragmentActivity)
    builder.setup()
    return builder.build()
}

@AllAboardDsl
class OnboardingBuilder(activity: Activity) {
    @ColorRes
    var backgroundColor: Int? = null
    var showIndicator: Boolean = true
    var navigationView: View? = null
    var onPageChangeListener: ViewPager.OnPageChangeListener? = null
    private val pages: MutableList<PageView> = mutableListOf()
    private var onFinishHandler: (() -> Unit)? = null

    private val rootView: ViewGroup = ActivityCompat.requireViewById(activity, android.R.id.content)

    fun onFinish(handler: () -> Unit) {
        onFinishHandler = handler
    }

    internal fun addPage(page: PageView) = pages.add(page)

    internal fun build(): PageNavigator = OnboardingView(
            rootView,
            navigationView,
            onPageChangeListener,
            backgroundColor,
            showIndicator,
            pages,
            onFinishHandler
    )

    /**
     * A shadowing method to prevent nesting [onboarding] calls.
     * (Credits to hotkey for this solution http://stackoverflow.com/a/43470027/4465208)
     */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Onboardings can't be nested.")
    fun onboarding(param: () -> Unit = {}) {
    }
}
