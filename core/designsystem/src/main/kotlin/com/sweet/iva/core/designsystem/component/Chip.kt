package com.sweet.iva.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sweet.iva.core.designsystem.icon.AppIcons
import com.sweet.iva.core.designsystem.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppFilterChip(
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    label: @Composable () -> Unit
) {

    FilterChip(
        selected = selected,
        onClick = { onSelectedChange(!selected) },
        label = {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                label()
            }
        },
        modifier = modifier,
        enabled = enable,
        leadingIcon = if (selected) {
            {
                Icon(imageVector = AppIcons.Check, contentDescription = null)
            }
        } else {
            null
        },
        shape = CircleShape,
        border = FilterChipDefaults.filterChipBorder(
            selected = selected,
            enabled = enable,
            borderColor = MaterialTheme.colorScheme.outline,
            selectedBorderColor = MaterialTheme.colorScheme.onBackground,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            selectedBorderWidth = AppChipDefaults.ChipBorderWidth
        ),
        colors = FilterChipDefaults.filterChipColors(
            labelColor = MaterialTheme.colorScheme.onBackground,
            iconColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = if (selected) {
                MaterialTheme.colorScheme.onBackground
            } else Color.Transparent,
            disabledLabelColor = MaterialTheme.colorScheme.onBackground,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary
        )
    )

}

@ThemePreviews
@Composable
fun ChipPreview() {
    AppTheme {
        AppBackground(modifier = Modifier.size(80.dp, 100.dp)) {
            Column {
                AppFilterChip(selected = true, onSelectedChange = {}) {
                    Text(text = "Chip")
                }
                AppFilterChip(selected = false, onSelectedChange = {}) {
                    Text(text = "Chips")
                }
            }

        }
    }
}

object AppChipDefaults {
    const val DisabledChipContainerAlpha = 0.12f
    const val DisabledChipContentAlpha = 0.38f
    val ChipBorderWidth = 1.dp
}
