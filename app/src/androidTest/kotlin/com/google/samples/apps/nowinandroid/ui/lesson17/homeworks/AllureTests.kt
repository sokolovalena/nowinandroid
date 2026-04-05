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

package com.google.samples.apps.nowinandroid.ui.lesson17.homeworks

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.google.samples.apps.nowinandroid.MainActivity
import com.google.samples.apps.nowinandroid.ui.lesson15.homeworks.ForyouScreen
import com.google.samples.apps.nowinandroid.ui.lesson15.homeworks.SearchScreen
import com.google.samples.apps.nowinandroid.ui.lesson16.homeworks.LazyHomeScreen
import com.google.samples.apps.nowinandroid.ui.lesson16.homeworks.NewsItems
import com.google.samples.apps.nowinandroid.ui.lesson16.homeworks.TopicsItems
import com.kaspersky.components.alluresupport.withForcedAllureSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.ExperimentalTestApi

abstract class ConfigureTest : TestCase(kaspressoBuilder = Kaspresso.Builder.withForcedAllureSupport(false)) {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    val kakaoRule = KakaoComposeTestRule(composeTestRule, true)
}

@HiltAndroidTest
class AllureTests : ConfigureTest() {

    @Test
    fun checkToolbar() {
        run {
            step("Проверить элементы главного экрана"){
                ForyouScreen {
                    step("Проверить тулбар"){
                        toolbarTitle{
                            assertTextEquals("Now in Android")
                        }
                        toolbarSearchIcon{
                            assertIsDisplayed()
                        }
                        toolbarGearIcon{
                            assertIsDisplayed()
                        }
                    }
                    step("Проверить заголовок экрана"){
                        title{
                            assertIsDisplayed()
                            assertTextEquals("What are you interested in?")
                        }
                        subtitle{
                            assertIsDisplayed()
                            assertTextEquals("Updates from topics you follow will appear here. Follow some things to get started.")
                        }
                    }
                    step("Нажать на кнопку поиска"){
                        ForyouScreen{
                            toolbarSearchIcon.performClick()
                        }
                    }
                    step("Проверить экран поиска"){
                        Thread.sleep(5_000)
                        SearchScreen{
                            textField.assertIsDisplayed()
                            iconSearch.assertIsDisplayed()
                            backButton.assertIsDisplayed()
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkListItems() {
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