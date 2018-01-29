# All Aboard!

[![Build Status](https://travis-ci.com/tylerbwong/AllAboard.svg?token=AABXGtYrzS4uRtMAUqq3&branch=master)](https://travis-ci.com/tylerbwong/AllAboard)

Create onboarding flows for your app using a clean and simple Kotlin DSL.

# Basic Usage

```kotlin
onboarding {
    showSkip = false
    showIndicator = true
    
    // Specify contents with resource ids or strings
    page {
        imageRes = R.drawable.page_one_image
        titleRes = R.string.page_one_title
        subTitleText = "This is a subtitle."
    }

    page {
        imageUrl = "https://images.com/page_two.png"
        titleText = "This is a train."
        subTitleRes = R.string.page_two_subtitle
    }

    page {
        view = pageThreeView // Use inflated custom views
    }

    page {
        viewRes = R.layout.page_four // Or just pass a layout resource id!
    }

    onFinish {
        finish()
    }
}
```