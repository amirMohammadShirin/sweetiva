package com.sweet.iva.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sweet.iva.core.designsystem.R
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.dimens

@Composable
fun RowScope.AppNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: Int,
    modifier: Modifier = Modifier,
    selectedIcon: Int = icon,
    enabled: Boolean = true,
    label: String? = null,
    alwaysShowLabel: Boolean = true,
    colors: NavigationBarItemColors =
        NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.secondary,
            unselectedIconColor = MaterialTheme.colorScheme.outline,
            selectedTextColor = MaterialTheme.colorScheme.secondary,
            unselectedTextColor = MaterialTheme.colorScheme.outline,
            indicatorColor = MaterialTheme.colorScheme.background,
        ),
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                Icon(
                    modifier = Modifier.size(30.dp, 30.dp),
                    painter = painterResource(id = selectedIcon),
                    contentDescription = "selected icon $selectedIcon",
                )
            } else {
                Icon(
                    modifier = Modifier.size(25.dp, 25.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "selected icon $icon",
                )
            }
        },
        modifier = modifier,
        enabled = enabled,
        label = {
            if (label != null) {
                ProvideTextStyle(
                    value =
                        MaterialTheme.typography.labelSmall,
                ) {
                    Text(label)
                }
            }
        },
        alwaysShowLabel = alwaysShowLabel,
        colors = colors,
    )
}

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.secondary,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        modifier =
            modifier
                .padding(horizontal = MaterialTheme.dimens.largeGap)
                .clip(RoundedCornerShape(10.dp)),
        tonalElevation = 5.dp,
        shadowElevation = 5.dp,
        color = MaterialTheme.colorScheme.background,
        border = BorderStroke(0.25.dp, MaterialTheme.colorScheme.surface),
    ) {
        NavigationBar(
            contentColor = contentColor,
            containerColor = containerColor,
            content = content,
        )
    }
}

@Composable
fun AppNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.background,
        header = header,
        content = content,
    )
}

@ThemePreviews
@Composable
fun AppNavigationPreview() {
    val items = listOf("Home", "Profile", "Setting")
    val icons =
        listOf(
            R.drawable.ic_list_bullet,
            R.drawable.ic_edit,
            R.drawable.ic_setting,
        )
    val selectedIcons =
        listOf(
            R.drawable.ic_list_bullet,
            R.drawable.ic_edit,
            R.drawable.ic_setting,
        )

    AppTheme {
        AppNavigationBar(
            modifier =
                Modifier
                    .height(30.dp),
        ) {
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
