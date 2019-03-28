package me.tylerbwong.allaboard.view

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

internal class OnboardingPagerAdapter(private val pages: List<PageView>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): View =
            pages[position].getView(container).also { container.addView(it) }

    override fun destroyItem(container: ViewGroup, position: Int, page: Any) =
        container.removeView(page as View)

    override fun getCount(): Int = pages.size

    override fun isViewFromObject(view: View, page: Any): Boolean = view === page
}
