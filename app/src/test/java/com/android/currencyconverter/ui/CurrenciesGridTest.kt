package com.android.currencyconverter.ui


import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import com.android.currencyconverter.components.CurrenciesGrid
import com.android.currencyconverter.data.CurrencyConverted
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
//@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
//@Config(
//    manifest = Config.NONE,
//    application = HiltTestApplication::class,
//)
class CurrenciesGridTest {
//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

//    @Before
//    fun `set up`() {
//        hiltRule.inject()
//    }


    @Test
    fun `is currencies grid shown`() {
        val tagGrid = "Currency grid"
        val tagGridItem = "Currency grid item"
        val currenciesConverted = mutableListOf<CurrencyConverted>().apply {
            add(CurrencyConverted("PKR", "Pakistan", 10.0.toBigDecimal()))
            add(CurrencyConverted("USD", "USA", 10.0.toBigDecimal()))
        }
        composeTestRule.setContent {
            CurrencyConverterTheme {
                CurrenciesGrid(currenciesConverted = currenciesConverted)
            }
        }
        composeTestRule.onNodeWithTag(tagGrid).assertIsDisplayed()
        composeTestRule.onNodeWithTag(tagGrid)
            .onChildren()
            .assertCountEquals(currenciesConverted.size)
            .onFirst()
            .assert(hasTestTag(tagGridItem))
    }
}