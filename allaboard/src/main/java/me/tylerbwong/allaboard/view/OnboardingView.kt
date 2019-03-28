package me.tylerbwong.allaboard.view

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import me.tylerbwong.allaboard.R
import android.view.animation.OvershootInterpolator
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class OnboardingView internal constructor(
        rootView: ViewGroup,
        @ColorRes private var backgroundColor: Int? = null,
        private var showIndicator: Boolean = true,
        private val pages: MutableList<PageView> = mutableListOf(),
        private var onFinishHandler: (() -> Unit)? = null
) : ViewPager.OnPageChangeListener {
    private val onboardingView = rootView.inflateAndAttach(R.layout.onboarding_view)
    private val viewPager = ViewCompat.requireViewById<ViewPager>(onboardingView, R.id.view_pager)
    private val tabDots = ViewCompat.requireViewById<TabLayout>(onboardingView, R.id.dot_tabs)
    private val skipPrevButton = ViewCompat.requireViewById<ImageButton>(
            onboardingView,
            R.id.prev_button
    )
    private val nextDoneButton = ViewCompat.requireViewById<ImageButton>(
            onboardingView,
            R.id.next_button
    )
    private val interpolator = OvershootInterpolator()
    private var isLastPage = false

    init {
        backgroundColor?.let {
            onboardingView.setBackgroundColor(ContextCompat.getColor(rootView.context, it))
        }

        viewPager.apply {
            adapter = OnboardingPagerAdapter(pages)
            addOnPageChangeListener(this@OnboardingView)
        }

        tabDots.apply {
            setupWithViewPager(viewPager, true)
            visibility = if (showIndicator) View.VISIBLE else View.GONE
        }

        skipPrevButton.apply {
            background.alpha = BUTTON_ALPHA
            setOnClickListener {
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }

        nextDoneButton.apply {
            background.alpha = BUTTON_ALPHA
            setOnClickListener {
                val curItem = viewPager.currentItem

                if (curItem < pages.size - 1) {
                    viewPager.currentItem = curItem + 1
                }
                else {
                    onFinishHandler?.invoke()
                }
            }
        }
    }

    override fun onPageSelected(position: Int) {
        if (position == pages.size - 1) {
            isLastPage = true
            animate(
                    nextDoneButton,
                    R.drawable.ic_check_white_24dp,
                    FULL_ROTATION,
                    LONG_ROTATION_DURATION
            )
        }
        else {
            if (isLastPage) {
                isLastPage = false
                animate(
                        nextDoneButton,
                        R.drawable.ic_arrow_forward_white_24dp,
                        REVERSE_FULL_ROTATION,
                        LONG_ROTATION_DURATION
                )
            }
        }

        if (position == 0) {
            skipPrevButton.visibility = View.INVISIBLE
        }
        else {
            skipPrevButton.visibility = View.VISIBLE
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    private fun animate(
            button: ImageButton,
            @DrawableRes toIcon: Int,
            rotation: Float,
            duration: Long
    ) {
        button.setImageResource(toIcon)
        ViewCompat.animate(button)
                .rotation(rotation)
                .withLayer()
                .setDuration(duration)
                .setInterpolator(interpolator)
                .start()
    }

    companion object {
        private const val FULL_ROTATION = 360.0f
        private const val REVERSE_FULL_ROTATION = 0.0f
        private const val LONG_ROTATION_DURATION: Long = 1000
        private const val BUTTON_ALPHA = 72
    }
}
