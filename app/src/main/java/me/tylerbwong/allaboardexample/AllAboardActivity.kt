package me.tylerbwong.allaboardexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.tylerbwong.allaboard.builder.onboarding
import me.tylerbwong.allaboard.builder.page

class AllAboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pageThreeView = LayoutInflater.from(this)
                .inflate(
                        R.layout.page_three,
                        window.decorView.rootView as ViewGroup,
                        false
                )

        onboarding {
            backgroundColor = R.color.colorPrimary
            showIndicator = true

            page {
                imageRes = R.drawable.train
                titleRes = R.string.app_name
                subTitleRes = R.string.page_one_sub_title
            }

            page {
                lottieFile = "success.json"
                titleText = "Lottie Support"
                subTitleText = "Works with Lottie too! Just pass in your file name."
            }

            page {
                customView = pageThreeView // Use inflated custom views
            }

            page {
                customViewRes = R.layout.page_four // Or just pass a layout resource id!
            }

            onFinish {
                Toast.makeText(
                        this@AllAboardActivity,
                        "We're all done here!",
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
