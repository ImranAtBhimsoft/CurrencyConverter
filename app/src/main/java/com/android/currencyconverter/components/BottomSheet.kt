package com.android.currencyconverter.components

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.android.currencyconverter.R

/**
 * Created by m.imran
 * Senior Software Engineer at
 * Enhance IT on 21/12/2023.
 * se.muhammadimran@gmail.com
 */

/**
 * @param content which is a composable function
 * @param onDismiss invoked when bottom sheet is dismissed
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(content: @Composable () -> Unit, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        modifier = Modifier.testTag(stringResource(id = R.string.currency_bottom_sheet)),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        content()
    }
}