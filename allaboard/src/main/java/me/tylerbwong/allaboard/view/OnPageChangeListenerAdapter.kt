package me.tylerbwong.allaboard.view

import androidx.viewpager.widget.ViewPager

abstract class OnPageChangeListenerAdapter : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
        // Sub-classes may override
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        // Sub-classes may override
    }

    override fun onPageSelected(position: Int) {
        // Sub-classes may override
    }
}
