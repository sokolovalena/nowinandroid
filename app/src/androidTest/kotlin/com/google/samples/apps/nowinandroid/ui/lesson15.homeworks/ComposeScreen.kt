package com.google.samples.apps.nowinandroid.ui.lesson15.homeworks

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.google.samples.apps.nowinandroid.MainActivity
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import org.junit.Rule
import org.junit.Test


abstract class ConfiguredComposeTestCase : TestCase(kaspressoBuilder = Kaspresso.Builder.withComposeSupport()) {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    val kakaoRule = KakaoComposeTestRule(composeTestRule, true)
}

@HiltAndroidTest
class SimpleTest : ConfiguredComposeTestCase() {

    @Test
    fun checkToolbarTitle() {
        run {
            ForyouScreen {
                toolbarTitle.assertTextEquals("Now in Android")
            }
        }
    }
}