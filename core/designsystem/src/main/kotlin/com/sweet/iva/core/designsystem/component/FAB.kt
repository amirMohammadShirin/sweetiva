package com.sweet.iva.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.sweet.iva.core.designsystem.icon.AppIcons
import com.sweet.iva.core.designsystem.theme.BlueRoyal10
import com.sweet.iva.core.designsystem.theme.BlueRoyal700
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.PastelGreen10
import com.sweet.iva.core.designsystem.theme.PastelGreen700

@Composable
fun AppFab(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column {
        FloatingActionButton(
            onClick = { onClick.invoke() },
            containerColor = PastelGreen700,
            contentColor = PastelGreen10,
            content = content
        )
    }

}

@Composable
fun AppMultiItemFab(
    expanded: Boolean = false,
    containerColor: Color = BlueRoyal700,
    contentColor: Color = BlueRoyal10,
    content: @Composable ColumnScope.() -> Unit
) {

    var isExpanded by remember { mutableStateOf(expanded) }

    Column(
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = isExpanded
        ) {
            Column(content = content)
        }
        FloatingActionButton(
            onClick = {
                isExpanded = !isExpanded
            },
            containerColor = containerColor,
            contentColor = contentColor,
        ) {
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    imageVector = AppIcons.Line,
                    contentDescription = "expand"
                )
            }
            AnimatedVisibility(
                visible = !isExpanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(imageVector = AppIcons.Add, contentDescription = "expand")
            }

        }
    }

}

@Composable
fun AppFabItem(
    modifier: Modifier = Modifier,
    containerColor: Color = BlueRoyal700,
    contentColor: Color = BlueRoyal10,
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {

    AppButton(
        modifier = modifier
            .height(50.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        ProvideTextStyle(
            value = MaterialTheme.typography.labelSmall
        ) {
            Text(
                text = title,
                color = contentColor
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            modifier = Modifier.size(width = 40.dp, height = 40.dp),
            imageVector = icon,
            contentDescription = "icon $title",
            tint = contentColor
        )
    }

}


@Composable
@ThemePreviews
fun PreviewAppFab() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AppFab(onClick = {}) {
                Icon(imageVector = AppIcons.Add, contentDescription = "add")
            }
            Spacer(modifier = Modifier.height(10.dp))
            AppMultiItemFab(
                expanded = true,
                containerColor = PastelGreen700,
                contentColor = PastelGreen10
            ) {
                AppFabItem(
                    containerColor = PastelGreen700,
                    contentColor = PastelGreen10,
                    modifier = Modifier
                        .padding(bottom = 10.dp, start = 10.dp),
                    icon = AppIcons.Add,
                    title = "Add Category",
                    onClick = {}
                )
                AppFabItem(
                    containerColor = PastelGreen700,
                    contentColor = PastelGreen10,
                    modifier = Modifier
                        .padding(bottom = 10.dp, start = 10.dp),
                    icon = AppIcons.Settings,
                    title = "Add Task",
                    onClick = {}
                )
            }
        }
    }
}