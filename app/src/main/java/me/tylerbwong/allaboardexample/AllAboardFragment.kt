package me.tylerbwong.allaboardexample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import me.tylerbwong.allaboard.builder.onboarding
import me.tylerbwong.allaboard.builder.page

class AllAboardFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        onboarding {
            backgroundColor = R.color.colorAccent
            showIndicator = false

            page {
                imageRes = R.mipmap.ic_launcher_round
                titleRes = R.string.app_name
                subTitleText = "This is a subtitle in a fragment."
            }

            page {
                imageUrl = "http://weart.co/v2/wp-content/uploads/2016/01/WE_ART_TIMO_MEYER_27.jpg"
                titleText = "This is a train in a fragment."
                subTitleRes = R.string.app_name
            }

            page {
                customViewRes = R.layout.page_three
            }

            page {
                customViewRes = R.layout.page_four
            }

            onFinish {
                Toast.makeText(activity, "We're all done here!", Toast.LENGTH_LONG).show()
            }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        fun newInstance(): AllAboardFragment {
            return AllAboardFragment()
        }
    }
}