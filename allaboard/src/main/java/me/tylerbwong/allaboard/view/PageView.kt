package me.tylerbwong.allaboard.view

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import me.tylerbwong.allaboard.R

class PageView internal constructor(
        private var imageUrl: String? = null,
        private var lottieFile: String? = null,
        @DrawableRes private var imageRes: Int? = null,
        private var titleText: String = "",
        @StringRes private var titleRes: Int? = null,
        @ColorRes private var titleColor: Int? = null,
        private var subTitleText: String = "",
        @StringRes private var subTitleRes: Int? = null,
        @ColorRes private var subTitleColor: Int? = null,
        private var customView: View? = null,
        @LayoutRes private var customViewRes: Int? = null
) {
    private fun LottieAnimationView.applyImage() {
        imageUrl?.let {
            Glide.with(context)
                    .load(it)
                    .into(this)
            return
        }

        imageRes?.let {
            setImageResource(it)
            return
        }

        lottieFile?.let {
            setAnimation(it)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
        }
    }

    private fun TextView.applyTitle() {
        titleColor?.let {
            setTextColor(ContextCompat.getColor(context, it))
        }

        titleRes?.let {
            setText(it)
            return
        }

        text = titleText
    }

    private fun TextView.applySubTitle() {
        subTitleColor?.let {
            setTextColor(it)
        }

        subTitleRes?.let {
            setText(it)
            return
        }

        text = subTitleText
    }

    internal fun getView(rootView: ViewGroup): View {
        customView?.let {
            return it
        }
        customViewRes?.let {
            return rootView.justInflate(it)
        }

        return rootView.justInflate(R.layout.page_view).also {
            ViewCompat.requireViewById<LottieAnimationView>(it, R.id.image).applyImage()
            ViewCompat.requireViewById<TextView>(it, R.id.title).applyTitle()
            ViewCompat.requireViewById<TextView>(it, R.id.sub_title).applySubTitle()
        }
    }
}
