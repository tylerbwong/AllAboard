package me.tylerbwong.allaboard.builder

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.View
import me.tylerbwong.allaboard.view.PageView

fun OnboardingBuilder.page(setup: PageBuilder.() -> Unit): PageView {
    val page = PageBuilder()
    page.setup()
    return page.build().also { addPage(it) }
}

class PageBuilder {
    var imageUrl: String? = null

    var lottieFile: String? = null

    @DrawableRes
    var imageRes: Int? = null

    var titleText: String = ""

    @StringRes
    var titleRes: Int? = null

    var subTitleText: String = ""

    @StringRes
    var subTitleRes: Int? = null

    var customView: View? = null

    @LayoutRes
    var customViewRes: Int? = null

    internal fun build() = PageView(
            imageUrl,
            lottieFile,
            imageRes,
            titleText,
            titleRes,
            subTitleText,
            subTitleRes,
            customView,
            customViewRes
    )

    /**
     * A shadowing method to prevent nesting [page] calls.
     * (Credits to hotkey for this solution http://stackoverflow.com/a/43470027/4465208)
     */
    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR, message = "Pages can't be nested.")
    fun page(param: () -> Unit = {}) {
    }
}