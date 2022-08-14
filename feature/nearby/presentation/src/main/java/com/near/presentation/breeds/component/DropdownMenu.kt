package com.near.presentation.breeds.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun <T> DropdownMenuButton(
    items: List<T>,
    onItemClick: (item: T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    dismissOnItemClick: Boolean = true,
    text: @Composable () -> Unit,
    itemText: @Composable (item: T) -> Unit,
    itemLeadingIcon: @Composable ((item: T) -> Unit)? = null,
    itemTrailingIcon: @Composable ((item: T) -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
         Button(
            onClick = { expanded = true },
            enabled = enabled,
            text = text,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded)  Icons.Rounded.ArrowDropUp else  Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        )
         DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            items = items,
            onItemClick = onItemClick,
            dismissOnItemClick = dismissOnItemClick,
            itemText = itemText,
            itemLeadingIcon = itemLeadingIcon,
            itemTrailingIcon = itemTrailingIcon
        )
    }
}

@Composable
fun <T>  DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    items: List<T>,
    onItemClick: (item: T) -> Unit,
    dismissOnItemClick: Boolean = true,
    itemText: @Composable (item: T) -> Unit,
    itemLeadingIcon: @Composable ((item: T) -> Unit)? = null,
    itemTrailingIcon: @Composable ((item: T) -> Unit)? = null
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = { itemText(item) },
                onClick = {
                    onItemClick(item)
                    if (dismissOnItemClick) onDismissRequest()
                },
                leadingIcon = if (itemLeadingIcon != null) {
                    { itemLeadingIcon(item) }
                } else {
                    null
                },
                trailingIcon = if (itemTrailingIcon != null) {
                    { itemTrailingIcon(item) }
                } else {
                    null
                }
            )
        }
    }
}

val Icons.Rounded.ArrowDropUp: ImageVector
    get() {
        if (_arrowDropUp != null) {
            return _arrowDropUp!!
        }
        _arrowDropUp = materialIcon(name = "Rounded.ArrowDropUp") {
            materialPath {
                moveTo(8.71f, 12.29f)
                lineTo(11.3f, 9.7f)
                curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f)
                lineToRelative(2.59f, 2.59f)
                curveToRelative(0.63f, 0.63f, 0.18f, 1.71f, -0.71f, 1.71f)
                horizontalLineTo(9.41f)
                curveToRelative(-0.89f, 0.0f, -1.33f, -1.08f, -0.7f, -1.71f)
                close()
            }
        }
        return _arrowDropUp!!
    }

private var _arrowDropUp: ImageVector? = null
