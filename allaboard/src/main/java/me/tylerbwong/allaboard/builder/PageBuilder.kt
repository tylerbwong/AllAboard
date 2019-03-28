package me.tylerbwong.allaboard.builder

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import me.tylerbwong.allaboard.view.PageView

fun OnboardingBuilder.page(setup: PageBuilder.() -> Unit): PageView {
    val page = PageBuilder()
    page.setup()
    return page.build().also { addPage(it) }
}

@AllAboardDsl
class PageBuilder {
    var imageUrl: String? = null

    var lottieFile: String? = null

    @DrawableRes
    var imageRes: Int? = null

    var titleText: String = ""

    @StringRes
    var titleRes: Int? = null

    @ColorRes
    var titleColor: Int? = null

    var subTitleText: String = ""

    @StringRes
    var subTitleRes: Int? = null

    @ColorRes
    var subTitleColor: Int? = null

    var customView: View? = null

    @LayoutRes
    var customViewRes: Int? = null

    internal fun build() = PageView(
            imageUrl,
            lottieFile,
            imageRes,
            titleText,
            titleRes,
            titleColor,
            subTitleText,
            subTitleRes,
            subTitleColor,
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

@DslMarker
annotation class AllAboardDsl
