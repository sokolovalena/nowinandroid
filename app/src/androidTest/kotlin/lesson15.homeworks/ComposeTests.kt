package lesson15.homeworks

import com.google.samples.apps.nowinandroid.feature.foryou.impl.ForYouScreen
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class ComposeTests : ConfiguredComposeTestCase() {

    @Test
    fun checkToolbarInForYouScreenAndSearchScreen() {
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
}