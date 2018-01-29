package me.tylerbwong.allaboardexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import me.tylerbwong.allaboard.builder.onboarding
import me.tylerbwong.allaboard.builder.page
import me.tylerbwong.allaboard.view.Onboarding

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
                imageRes = R.mipmap.ic_launcher_round
                titleRes = R.string.app_name
                subTitleText = "This is a subtitle."
            }

            page {
                imageUrl = "https://i.pinimg.com/736x/04/20/86/042086261c2f57af2184705e80244ff2" +
                        "--illustration-styles-dragomir.jpg"
                titleText = "This is a train."
                subTitleRes = R.string.app_name
            }

            page {
                customView = pageThreeView // Use inflated custom views
            }

            page {
                customViewRes = R.layout.page_four // Or just pass a layout resource id!
            }

            onFinish {
                finish()
            }
        }
    }
}