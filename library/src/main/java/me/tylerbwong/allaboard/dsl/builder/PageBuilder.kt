package me.tylerbwong.allaboard.dsl.builder

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.View

fun OnboardingBuilder.page(setup: PageBuilder.() -> Unit) {

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
}