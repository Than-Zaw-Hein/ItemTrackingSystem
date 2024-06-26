package com.tzh.itemTrackingSystem.screen.dialog

import android.annotation.SuppressLint
import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.tzh.itemTrackingSystem.screen.common.TitleText


@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PowerBottomSheet(
    isShow: Boolean, sheetState: SheetState, dismiss: () -> Unit, onSave: (power: Int) -> Unit
) {

    val density = LocalDensity.current
    val bottomPadding = WindowInsets.navigationBars.getBottom(density).dp
    var power by remember {
        mutableIntStateOf(0)
    }
    if (isShow) {
        ModalBottomSheet(
            onDismissRequest = {
                dismiss()
            },
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .padding(bottom = bottomPadding)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                TitleText(text = "Power Setting", Modifier.padding(8.dp))
                IconButton(
                    onClick = {
                        dismiss()
                    }, modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "")
                }
            }
            AndroidView<NumberPicker>(modifier = Modifier
                .fillMaxWidth()
                .weight(1f), factory = { context ->
                NumberPicker(context).apply {
                    setOnValueChangedListener { numberPicker, i, newValue ->
                        power = newValue
                    }
                    value = power
                    minValue = 0
                    maxValue = 26
                }
            }, update = { view ->
                view.setOnValueChangedListener { numberPicker, i, newValue ->
                    power = newValue
                }
            })
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp), Arrangement.spacedBy(8.dp)
            ) {
                ElevatedButton(onClick = {
                    onSave(power)
                }) {
                    Text(text = "Save")
                }
            }
        }
    }
}