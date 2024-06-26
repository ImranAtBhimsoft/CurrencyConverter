package com.android.currencyconverter.ui


import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.android.currencyconverter.MainActivity
import com.android.currencyconverter.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 22/12/2023.
 * se.muhammadimran@gmail.com
 */

@RunWith(RobolectricTestRunner::class)
class MainContentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun isTopAppbarShown() {
        composeTestRule.onNodeWithText("Currency Converter").assertIsDisplayed()
    }

    @Test
    fun isFormatterFormatting() {
        val contentDescription = composeTestRule.activity.getString(R.string.currency_text_field)
        val textToFormat = "123456.5678"
        val expectedText = "123 456.5678"
        composeTestRule.onNodeWithContentDescription(contentDescription)
            .performTextInput(textToFormat)
        composeTestRule.onNodeWithContentDescription(contentDescription)
            .assert(hasText(expectedText))
    }

    @Test
    fun isCurrenciesBottomSheetShown() {
        val tagBottomSheet = composeTestRule.activity.getString(R.string.currency_bottom_sheet)
        val tagList = composeTestRule.activity.getString(R.string.currency_list)
        val tagDroDown = "drop_down"

        composeTestRule.onNodeWithTag(tagDroDown).performClick()
        composeTestRule.onNodeWithTag(tagBottomSheet).assertIsDisplayed()
        composeTestRule.onNodeWithTag(tagList).performClick()
    }
}