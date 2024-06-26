package com.tzh.itemTrackingSystem.screen.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ConnectingDialog(isShow: Boolean, selectedBtDevice: String?) {

    if (isShow) {
        Dialog(
            onDismissRequest = { }, DialogProperties(usePlatformDefaultWidth = false)
        ) {
            ElevatedCard(modifier = Modifier.padding(16.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.3f)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Connecting the bluetooth device to :\n${selectedBtDevice}",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}