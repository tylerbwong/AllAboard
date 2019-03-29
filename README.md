# All Aboard!

[![Build Status](https://travis-ci.org/tylerbwong/AllAboard.svg?branch=master)](https://travis-ci.org/tylerbwong/AllAboard)
[![Download](https://api.bintray.com/packages/tylerbwong/maven/AllAboard/images/download.svg)](https://bintray.com/tylerbwong/maven/AllAboard/_latestVersion)

Create onboarding flows for your Android apps using a clean and simple Kotlin DSL.

<img src="/art/screenshot.png" width="30%">

# Download

Add the following to your app's ```build.gradle```.

```gradle
dependencies {
    implementation 'me.tylerbwong:allaboard:1.0.2'
}
```

# Basic Usage

Adding an onboarding flow is as easy as adding this call to your Activity's ```onCreate()``` (or Fragment's ```onCreateView()```).

```kotlin
onboarding {
    
}
```

Then add some pages! 

```kotlin
onboarding {
    page {
        
    }
}
```

Here's a complete example.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    ...
    
    // Use a PageNavigator to programmatically change pages
    val navigator = onboarding {
        backgroundColor = R.color.colorPrimary
        showIndicator = true
        onPageChangeListener = ... // Set an onPageChangeListener to listen to page changes
        navigationView = ... // Set a custom navigation view to handle switching pages
        
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
            customView = pageThreeView // Use inflated custom views
        }
    
        page {
            customViewRes = R.layout.page_four // Or just pass a layout resource id!
        }
    
        onFinish {
            Toast.makeText(this@AllAboardActivity, "We're all done here!", Toast.LENGTH_LONG).show()
        }
    }
}
```

# Acknowledgements

This library was heavily inspired by [zsmb13/MaterialDrawerKt](https://github.com/zsmb13/MaterialDrawerKt). Definitely go check it out!

Other libraries used in this project

* [bumptech/glide](https://github.com/bumptech/glide)
* [airbnb/lottie-android](https://github.com/airbnb/lottie-android)

# License

    Copyright 2018 Tyler Wong

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
