package com.hamed.presentation.allBreeds.component

/*

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
}*/
