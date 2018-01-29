package me.tylerbwong.allaboard

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.View

class Page(
        internal var imageUrl: String? = null,
        @DrawableRes internal var imageRes: Int? = null,
        internal var titleText: String? = null,
        @StringRes internal var titleRes: Int? = null,
        internal var subTitleText: String? = null,
        @StringRes internal var subTitleRes: Int? = null,
        internal var view: View? = null,
        @LayoutRes internal var viewRes: Int? = null
)