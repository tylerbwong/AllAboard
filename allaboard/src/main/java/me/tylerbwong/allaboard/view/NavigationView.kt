package me.tylerbwong.allaboard.view

import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.ImageButton
import androidx.annotation.DrawableRes
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import me.tylerbwong.allaboard.R

internal class NavigationView(
        rootView: ViewGroup,
        private val numPages: Int,
        private val pageNavigator: PageNavigator
) : ViewPager.OnPageChangeListener {
    private val skipPrevButton = ViewCompat.requireViewById<ImageButton>(rootView, R.id.prev_button)
    private val nextDoneButton = ViewCompat.requireViewById<ImageButton>(rootView, R.id.next_button)
    private val interpolator = OvershootInterpolator()
    private var isLastPage = false

    init {
        skipPrevButton.apply {
            background.alpha = BUTTON_ALPHA
            setOnClickListener { pageNavigator.goToPrevious() }
        }

        nextDoneButton.apply {
            background.alpha = BUTTON_ALPHA
            setOnClickListener { pageNavigator.goToNext() }
        }
    }

    override fun onPageSelected(position: Int) {
        if (position == numPages - 1) {
            isLastPage = true
            animate(
                    nextDoneButton,
                    R.drawable.ic_check_white_24dp,
                    FULL_ROTATION,
                    LONG_ROTATION_DURATION
            )
        } else {
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
        } else {
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
