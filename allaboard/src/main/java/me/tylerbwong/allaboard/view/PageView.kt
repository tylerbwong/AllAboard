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
            with(image) {
                setAnimation(file)
                repeatCount = LottieDrawable.INFINITE
                playAnimation()
            }
        }
    }

    private fun setTitle(textView: TextView) {
        titleColor?.let {
            textView.setTextColor(ContextCompat.getColor(textView.context, it))
        }

        titleRes?.let {
            textView.setText(it)
            return
        }

        textView.text = titleText
    }

    private fun setSubTitle(textView: TextView) {
        subTitleColor?.let {
            textView.setTextColor(it)
        }

        subTitleRes?.let {
            textView.setText(it)
            return
        }

        textView.text = subTitleText
    }

    internal fun getView(rootView: ViewGroup): View {
        customView?.let {
            return it
        }
        customViewRes?.let {
            return rootView.justInflate(it)
        }

        return rootView.justInflate(R.layout.page_view).also {
            ViewCompat.requireViewById<LottieAnimationView>(it, R.id.image).apply { setImage(this) }
            ViewCompat.requireViewById<TextView>(it, R.id.title).apply { setTitle(this) }
            ViewCompat.requireViewById<TextView>(it, R.id.sub_title).apply { setSubTitle(this) }
        }
    }
}
