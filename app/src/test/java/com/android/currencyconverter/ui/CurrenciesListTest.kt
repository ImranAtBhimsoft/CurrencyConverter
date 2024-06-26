package com.android.currencyconverter.ui


import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import com.android.currencyconverter.components.CurrenciesList
import com.android.currencyconverter.fakeCurrencies
import com.android.currencyconverter.theme.CurrencyConverterTheme
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
class CurrenciesListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `is currencies list shown`() {
        val tagList = "Currency List"
        val tagListItem = "Currency List Item"
        val currencies = fakeCurrencies()
        composeTestRule.setContent {
            CurrencyConverterTheme {
                CurrenciesList(currencies = currencies, onItemClicked = {})
            }
        }
        composeTestRule.onNodeWithTag(tagList).assertIsDisplayed()
        composeTestRule.onNodeWithTag(tagList)
            .onChildren()
            .assertCountEquals(currencies.size)
            .onFirst()
            .assert(hasContentDescription(tagListItem))
    }
}