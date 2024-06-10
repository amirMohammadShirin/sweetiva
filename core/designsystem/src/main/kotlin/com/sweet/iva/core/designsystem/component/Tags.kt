package com.sweet.iva.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sweet.iva.core.designsystem.theme.AppTheme


@Composable
fun AppTag(
    modifier: Modifier = Modifier,
    followed: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: @Composable () -> Unit
) {

    Box(modifier = modifier) {
        val containerColor = if (followed) {
            MaterialTheme.colorScheme.secondary
        } else {
            MaterialTheme.colorScheme.onSecondary.copy(
                alpha = AppTagDefault.UnfollowedTopicTagContainerAlpha,
            )
        }
        TextButton(
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.textButtonColors(
                containerColor = containerColor,
                contentColor = contentColorFor(
                    backgroundColor = containerColor
                ),
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = AppTagDefault.DisabledTopicTagContainerAlpha
                )
            )
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                text()
            }
        }
    }

}


@ThemePreviews
@Composable
fun AppTagPreview() {
    AppTheme {
        Column {
            AppTag(followed = true, onClick = {}) {
                Text(text = "Topic1".uppercase())
            }
            AppTag(followed = false, onClick = {}) {
                Text(text = "Topic2".uppercase())
            }
        }
    }
}

object AppTagDefault {
    const val UnfollowedTopicTagContainerAlpha = 0.5f
    const val DisabledTopicTagContainerAlpha = 0.12f
}
