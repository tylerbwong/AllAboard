package me.tylerbwong.allaboard.view

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        private var subTitleText: String = "",
        @StringRes private var subTitleRes: Int? = null,
        private var customView: View? = null,
        @LayoutRes private var customViewRes: Int? = null
) {
    private fun setImage(image: LottieAnimationView) {
        imageUrl?.let {
            Glide.with(image.context)
                    .load(it)
                    .into(image)
            return
        }

        imageRes?.let {
            image.setImageResource(it)
            return
        }

        lottieFile?.let {
            val file = it
            with (image) {
                setAnimation(file)
                repeatCount = LottieDrawable.INFINITE
                playAnimation()
            }
        }
    }

    private fun getTitle(context: Context): String {
        titleRes?.let {
            return context.getString(it)
        }
        return titleText
    }

    private fun getSubTitle(context: Context): String {
        subTitleRes?.let {
            return context.getString(it)
        }
        return subTitleText
    }

    internal fun getView(context: Context, rootView: ViewGroup): View {
        customView?.let {
            return it
        }
        customViewRes?.let {
            return LayoutInflater.from(context)
                    .inflate(it, rootView, false)
        }

        val view = LayoutInflater.from(context)
                .inflate(R.layout.page_view, rootView, false)
        view.findViewById<LottieAnimationView>(R.id.image).apply { setImage(this) }
        view.findViewById<TextView>(R.id.title).apply { text = getTitle(context) }
        view.findViewById<TextView>(R.id.sub_title).apply { text = getSubTitle(context) }

        return view
    }
}