package me.tylerbwong.allaboard.view

import android.support.annotation.ColorRes
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import me.tylerbwong.allaboard.R
import android.support.v4.view.ViewCompat
import android.view.animation.OvershootInterpolator

class Onboarding(
        rootView: ViewGroup,
        @ColorRes private var backgroundColor: Int? = null,
        private var showIndicator: Boolean = true,
        private val pages: MutableList<Page> = mutableListOf(),
        private var onFinishHandler: (() -> Unit)? = null
) : ViewPager.OnPageChangeListener {
    private val onboardingView = LayoutInflater.from(rootView.context)
            .inflate(R.layout.onboarding_view, rootView, true)
    private val viewPager = onboardingView.findViewById<ViewPager>(R.id.view_pager)
    private val tabDots = onboardingView.findViewById<TabLayout>(R.id.dot_tabs)
    private val skipPrevButton = onboardingView.findViewById<ImageButton>(R.id.prev_button)
    private val nextDoneButton = onboardingView.findViewById<ImageButton>(R.id.next_button)
    private val interpolator = OvershootInterpolator()
    private var isLastPage = false

    init {
        backgroundColor?.let {
            onboardingView.setBackgroundColor(ContextCompat.getColor(rootView.context, it))
        }
        viewPager.apply {
            adapter = OnboardingPagerAdapter(pages)
            addOnPageChangeListener(this@Onboarding)
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

    private fun animate(button: ImageButton, toIcon: Int, rotation: Float, duration: Long) {
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