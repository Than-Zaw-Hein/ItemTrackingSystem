package com.tzh.itemTrackingSystem.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tzh.itemTrackingSystem.ulti.Constant

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MyAppDropDown(
    modifier: Modifier = Modifier.fillMaxWidth(), list: List<String>, value: String, onSelected: (String) -> Unit
) {
    // state of the menu
    val expanded = remember {
        mutableStateOf(false)
    }
    ElevatedCard(
        modifier = modifier.height(40.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        onClick = { expanded.value = !expanded.value },
        shape = CircleShape
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                maxLines = 1, overflow = TextOverflow.Ellipsis, text = value, style = TextStyle.Default.copy(
                    fontSize = 14.sp
                ), modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { expanded.value = !expanded.value }) {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded.value
                )
            }
        }

        MyDropDown(
            expanded = expanded, list = list, isEnabled = true
        ) { value ->
            onSelected(value)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDown(
    expanded: MutableState<Boolean>, list: List<String>, isEnabled: Boolean = true, onItemClick: (item: String) -> Unit
) {
    val itemHeights = remember { mutableStateMapOf<Int, Int>() }
    val baseHeight = when (list.size) {
        1 -> 70.dp
        2 -> 110.dp
        3 -> 165.dp
        4 -> 200.dp
        else -> 200.dp
    }
    val density = LocalDensity.current
    val maxHeight = remember(itemHeights.toMap()) {
        if (itemHeights.keys.toSet() != list.indices.toSet()) {
            // if we don't have all heights calculated yet, return default value
            return@remember baseHeight
        }
        val baseHeightInt = with(density) { baseHeight.toPx().toInt() }

        // top+bottom system padding
        var sum = with(density) { Constant.DropdownMenuVerticalPadding.toPx().toInt() } * 2
        for ((i, itemSize) in itemHeights.toSortedMap()) {
            sum += itemSize
            if (sum >= baseHeightInt) {
                return@remember with(density) { (sum - itemSize / 2).toDp() }
            }
        }
        // all items fit into base height
        baseHeight
    }

    // drop down menu
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .requiredSizeIn(maxHeight = maxHeight)
            .background(Color.LightGray.copy(alpha = 0.2f))
    ) {
        if (list.isNotEmpty()) {
            Box(
                modifier = Modifier.size(width = 380.dp, height = baseHeight)

            ) {
                LazyColumn {
                    itemsIndexed(list) { index, itemValue ->
                        DropdownMenuItem(
                            onClick = {
                                onItemClick.invoke(itemValue)
                                expanded.value = false
                            },
                            modifier = Modifier.onSizeChanged {
                                itemHeights[index] = it.height
                            },
                            text = { Text(text = itemValue) },
                        )
                    }
                }
            }
        }
    }
}