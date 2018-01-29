package me.tylerbwong.allaboard.builder

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.View
import me.tylerbwong.allaboard.Page

fun OnboardingBuilder.page(setup: PageBuilder.() -> Unit): Page {
    val page = PageBuilder()
    page.setup()
    return page.build().also { addPage(it) }
}

class PageBuilder {
    var imageUrl: String? = null

    @DrawableRes
    var imageRes: Int? = null

    var titleText: String? = null

    @StringRes
    var titleRes: Int? = null

    var subTitleText: String? = null

    @StringRes
    var subTitleRes: Int? = null

    var view: View? = null

    @LayoutRes
    var viewRes: Int? = null

    internal fun build(): Page = Page(
            imageUrl,
            imageRes,
            titleText,
            titleRes,
            subTitleText,
            subTitleRes,
            view,
            viewRes
    )
}