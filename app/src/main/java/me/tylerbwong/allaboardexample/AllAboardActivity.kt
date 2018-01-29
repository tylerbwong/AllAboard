package me.tylerbwong.allaboardexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.tylerbwong.allaboard.dsl.builder.onFinish
import me.tylerbwong.allaboard.dsl.builder.onboarding
import me.tylerbwong.allaboard.dsl.builder.page

class AllAboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onboarding {
            showSkip = false
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
                view = null // Use inflated custom views
            }

            page {
                viewRes = R.layout.page_four // Or just pass a layout resource id!
            }

            onFinish {
                finish()
            }
        }
    }
}