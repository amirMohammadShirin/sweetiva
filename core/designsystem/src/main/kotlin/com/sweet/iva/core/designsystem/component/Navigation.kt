package com.sweet.iva.core.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.sweet.iva.core.designsystem.icon.AppIcons
import com.sweet.iva.core.designsystem.theme.AppTheme

@Composable
fun RowScope.AppNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    selectedIcon: ImageVector = icon,
    enabled: Boolean = true,
    label: String? = null,
    alwaysShowLabel: Boolean = true,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        unselectedIconColor = MaterialTheme.colorScheme.outline,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        unselectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
        indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
    )
) {

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected)
                Icon(
                    modifier = Modifier.size(30.dp, 30.dp),
                    imageVector = selectedIcon,
                    contentDescription = "selected icon ${selectedIcon.name}"
                )
            else
                Icon(
                    modifier = Modifier.size(25.dp, 25.dp),
                    imageVector = icon,
                    contentDescription = "selected icon ${icon.name}"
                )
        },
        modifier = modifier,
        enabled = enabled,
        label = {
            if (label != null) ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                Text(label)
            }
        },
        alwaysShowLabel = alwaysShowLabel,
        colors = colors
    )

}

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier
            .clip(
                RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            ),
        contentColor = contentColor,
        containerColor = containerColor,
        content = content,
        windowInsets = WindowInsets(0.dp)
    )
}

@Composable
fun AppNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.background,
        header = header,
        content = content
    )
}

@ThemePreviews
@Composable
fun AppNavigationPreview() {
    val items = listOf("Home", "Profile", "Setting")
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

    AppTheme {
        AppNavigationBar {
            items.forEachIndexed { index, item ->
                AppNavigationBarItem(
                    icon = icons[index],
                    selectedIcon = selectedIcons[index],
                    label = items[index],
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}
