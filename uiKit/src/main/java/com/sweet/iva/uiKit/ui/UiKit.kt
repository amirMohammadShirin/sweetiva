package com.sweet.iva.uiKit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sweet.iva.core.designsystem.component.AppButton
import com.sweet.iva.core.designsystem.component.AppFilterChip
import com.sweet.iva.core.designsystem.component.AppIconToggleButton
import com.sweet.iva.core.designsystem.component.AppNavigationBar
import com.sweet.iva.core.designsystem.component.AppNavigationBarItem
import com.sweet.iva.core.designsystem.component.AppOutlinedButton
import com.sweet.iva.core.designsystem.component.AppTab
import com.sweet.iva.core.designsystem.component.AppTabRow
import com.sweet.iva.core.designsystem.component.AppTag
import com.sweet.iva.core.designsystem.component.AppTextButton
import com.sweet.iva.core.designsystem.component.ThemePreviews
import com.sweet.iva.core.designsystem.icon.AppIcons
import com.sweet.iva.core.designsystem.theme.AppTheme

/**
 * Mark component catalog.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppUiKit() {
    AppTheme {
        Surface {
            val contentPadding = WindowInsets
                .systemBars
                .add(WindowInsets(left = 16.dp, top = 16.dp, right = 16.dp, bottom = 16.dp))
                .asPaddingValues()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    Text(
                        text = "Mark Catalog",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
                item { Text("Buttons", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        AppButton(onClick = {}) {
                            Text(text = "Enabled")
                        }
                        AppOutlinedButton(onClick = {}) {
                            Text(text = "Enabled")
                        }
                        AppTextButton(onClick = {}) {
                            Text(text = "Enabled")
                        }
                    }
                }
                item { Text("Disabled buttons", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        AppButton(
                            onClick = {},
                            enabled = false,
                        ) {
                            Text(text = "Disabled")
                        }
                        AppOutlinedButton(
                            onClick = {},
                            enabled = false,
                        ) {
                            Text(text = "Disabled")
                        }
                        AppTextButton(
                            onClick = {},
                            enabled = false,
                        ) {
                            Text(text = "Disabled")
                        }
                    }
                }
                item { Text("Buttons with leading icons", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        AppButton(
                            onClick = {},
                            text = { Text(text = "Enabled") },
                            leadingIcon = {
                                Icon(imageVector = AppIcons.Add, contentDescription = null)
                            },
                        )
                        AppOutlinedButton(
                            onClick = {},
                            text = { Text(text = "Enabled") },
                            leadingIcon = {
                                Icon(imageVector = AppIcons.Add, contentDescription = null)
                            },
                        )
                        AppTextButton(
                            onClick = {},
                            text = { Text(text = "Enabled") },
                            leadingIcon = {
                                Icon(imageVector = AppIcons.Add, contentDescription = null)
                            },
                        )
                    }
                }
                item { Text("Disabled buttons with leading icons", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        AppButton(
                            onClick = {},
                            enabled = false,
                            text = { Text(text = "Disabled") },
                            leadingIcon = {
                                Icon(imageVector = AppIcons.Add, contentDescription = null)
                            },
                        )
                        AppOutlinedButton(
                            onClick = {},
                            enabled = false,
                            text = { Text(text = "Disabled") },
                            leadingIcon = {
                                Icon(imageVector = AppIcons.Add, contentDescription = null)
                            },
                        )
                        AppTextButton(
                            onClick = {},
                            enabled = false,
                            text = { Text(text = "Disabled") },
                            leadingIcon = {
                                Icon(imageVector = AppIcons.Add, contentDescription = null)
                            },
                        )
                    }
                }
                item { Text("Dropdown menus", Modifier.padding(top = 16.dp)) }
                item { Text("Chips", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        var firstChecked by rememberSaveable { mutableStateOf(false) }
                        AppFilterChip(
                            selected = firstChecked,
                            onSelectedChange = { checked -> firstChecked = checked },
                            label = { Text(text = "Enabled") },
                        )
                        var secondChecked by rememberSaveable { mutableStateOf(true) }
                        AppFilterChip(
                            selected = secondChecked,
                            onSelectedChange = { checked -> secondChecked = checked },
                            label = { Text(text = "Enabled") },
                        )
                        AppFilterChip(
                            selected = false,
                            onSelectedChange = {},
                            enable = false,
                            label = { Text(text = "Disabled") },
                        )
                        AppFilterChip(
                            selected = true,
                            onSelectedChange = {},
                            enable = false,
                            label = { Text(text = "Disabled") },
                        )
                    }
                }
                item { Text("Icon buttons", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        var firstChecked by rememberSaveable { mutableStateOf(false) }
                        AppIconToggleButton(
                            checked = firstChecked,
                            onCheckedChange = { checked -> firstChecked = checked },
                            icon = {
                                Icon(
                                    imageVector = AppIcons.BookmarkBorder,
                                    contentDescription = null,
                                )
                            },
                            checkedIcon = {
                                Icon(
                                    imageVector = AppIcons.Bookmark,
                                    contentDescription = null,
                                )
                            },
                        )
                        var secondChecked by rememberSaveable { mutableStateOf(true) }
                        AppIconToggleButton(
                            checked = secondChecked,
                            onCheckedChange = { checked -> secondChecked = checked },
                            icon = {
                                Icon(
                                    imageVector = AppIcons.BookmarkBorder,
                                    contentDescription = null,
                                )
                            },
                            checkedIcon = {
                                Icon(
                                    imageVector = AppIcons.Bookmark,
                                    contentDescription = null,
                                )
                            },
                        )
                        AppIconToggleButton(
                            checked = false,
                            onCheckedChange = {},
                            icon = {
                                Icon(
                                    imageVector = AppIcons.BookmarkBorder,
                                    contentDescription = null,
                                )
                            },
                            checkedIcon = {
                                Icon(
                                    imageVector = AppIcons.Bookmark,
                                    contentDescription = null,
                                )
                            },
                            enabled = false,
                        )
                        AppIconToggleButton(
                            checked = true,
                            onCheckedChange = {},
                            icon = {
                                Icon(
                                    imageVector = AppIcons.BookmarkBorder,
                                    contentDescription = null,
                                )
                            },
                            checkedIcon = {
                                Icon(
                                    imageVector = AppIcons.Bookmark,
                                    contentDescription = null,
                                )
                            },
                            enabled = false,
                        )
                    }
                }
                item { Text("View toggle", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        var firstExpanded by rememberSaveable { mutableStateOf(false) }

                        //View toggle

                    }
                }
                item { Text("Tags", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        AppTag(
                            followed = true,
                            onClick = {},
                            text = { Text(text = "Topic 1".uppercase()) },
                        )
                        AppTag(
                            followed = false,
                            onClick = {},
                            text = { Text(text = "Topic 2".uppercase()) },
                        )
                        AppTag(
                            followed = false,
                            onClick = {},
                            text = { Text(text = "Disabled".uppercase()) },
                            enabled = false,
                        )
                    }
                }
                item { Text("Tabs", Modifier.padding(top = 16.dp)) }
                item {
                    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
                    val titles = listOf("Topics", "People")
                    AppTabRow(selectedTabIndex = selectedTabIndex) {
                        titles.forEachIndexed { index, title ->
                            AppTab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                title = title,
                            )
                        }
                    }
                }
                item { Text("Navigation", Modifier.padding(top = 16.dp)) }
                item {
                    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
                    val items = listOf("For you", "Saved", "Interests")
                    val icons = listOf(
                        AppIcons.UpcomingBorder,
                        AppIcons.BookmarksBorder,
                        AppIcons.Grid3x3,
                    )
                    val selectedIcons = listOf(
                        AppIcons.Upcoming,
                        AppIcons.Bookmarks,
                        AppIcons.Grid3x3,
                    )
                    AppNavigationBar {
                        items.forEachIndexed { index, item ->
                            AppNavigationBarItem(
                                icon = icons[index],
                                selectedIcon = selectedIcons[index],
                                label = item,
                                selected = selectedItem == index,
                                onClick = { selectedItem = index },
                            )
                        }
                    }
                }
                item{
                    Text(text = "متن نمونه")
                }
                item{
                    ProvideTextStyle(value = MaterialTheme.typography.displayLarge) {
                        Text(text = "متن نمونه")
                    }
                }
            }
        }
    }
}

@Composable
@ThemePreviews
fun PreviewUiKit() {
    AppUiKit()
}