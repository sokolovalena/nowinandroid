/*
 * Copyright 2026 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.nowinandroid.ui.lesson16.homeworks

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.google.samples.apps.nowinandroid.MainActivity
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import org.junit.Rule
import org.junit.Test

abstract class ConfigureTest : TestCase(Kaspresso.Builder.withComposeSupport()) {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    val kakaoRule = KakaoComposeTestRule(composeTestRule, true)
}

@HiltAndroidTest
class LazyListTests : ConfigureTest() {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkLazyListItems() {
        run {
            step("Проверить элементы в списке") {
                LazyHomeScreen.topicList.childAt<TopicsItems>(0) {
                    image.assertIsDisplayed()
                    title.assertIsDisplayed()
                    iconPlus.assertIsDisplayed()
                }
                LazyHomeScreen.topicList.childAt<TopicsItems>(1) {
                    assertIsDisplayed()
                }
                LazyHomeScreen.topicList.childAt<TopicsItems>(2) {
                    assertIsDisplayed()
                }
            }
            step("Нажать на топик Compose") {
                LazyHomeScreen.topicList.childAt<TopicsItems>(2) {
                    iconPlus.performClick()
                    checkedIcon.assertIsDisplayed()
                }
            }
            step("Проверить что отобразился newList") {
                LazyHomeScreen.newsList.childAt<NewsItems>(0) {
                    assertIsDisplayed()
                }
            }
        }
    }
}