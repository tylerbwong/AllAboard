package me.tylerbwong.allaboard.view

import android.view.View
import android.view.ViewGroup
import me.tylerbwong.allaboard.R
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

internal class OnboardingView(
        rootView: ViewGroup,
        navigationView: View? = null,
        onPageChangeListener: ViewPager.OnPageChangeListener? = null,
        @ColorRes private val backgroundColor: Int? = null,
        private val showIndicator: Boolean = true,
        private val pages: MutableList<PageView> = mutableListOf(),
        private val onFinishHandler: (() -> Unit)? = null
) : PageNavigator {
    private val onboardingView = rootView.inflateAndAttach(R.layout.onboarding_view)
    private val navigationContainer = ViewCompat.requireViewById<ViewGroup>(onboardingView, R.id.navigation_container)
    private val viewPager = ViewCompat.requireViewById<ViewPager>(onboardingView, R.id.view_pager)
    private val tabDots = ViewCompat.requireViewById<TabLayout>(onboardingView, R.id.dot_tabs)

    init {
        backgroundColor?.let {
            onboardingView.setBackgroundColor(ContextCompat.getColor(rootView.context, it))
        }

        tabDots.apply {
            setupWithViewPager(viewPager, true)
            visibility = if (showIndicator) View.VISIBLE else View.GONE
        }

        if (navigationView != null) {
            navigationContainer.addView(navigationView)
        } else {
            val defaultNavigationView = navigationContainer.inflateAndAttach(R.layout.navigation_view)
            val navigation = NavigationView(defaultNavigationView as ViewGroup, pages.size, this)
            viewPager.addOnPageChangeListener(navigation)
        }

        if (onPageChangeListener != null) {
            viewPager.addOnPageChangeListener(onPageChangeListener)
        }

        viewPager.adapter = OnboardingPagerAdapter(pages)
    }

    override fun goToPrevious() {
        viewPager.currentItem = viewPager.currentItem - 1
    }

    override fun goToNext() {
        val curItem = viewPager.currentItem

        if (curItem < pages.size - 1) {
            viewPager.currentItem = curItem + 1
        } else {
            finish()
        }
    }

    override fun finish() {
        onFinishHandler?.invoke()
    }
}
