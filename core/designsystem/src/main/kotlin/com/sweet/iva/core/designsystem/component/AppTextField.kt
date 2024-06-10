package com.sweet.iva.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.sweet.iva.core.designsystem.icon.AppIcons
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.Metal100


@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    readonly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: (@Composable () -> Unit)? = null,
    placeHolder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = RoundedCornerShape(15.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        disabledLabelColor = MaterialTheme.colorScheme.outline,
        disabledContainerColor = Color.Transparent,
        disabledLeadingIconColor = MaterialTheme.colorScheme.outline,
        disabledPlaceholderColor = MaterialTheme.colorScheme.outline,
        disabledSupportingTextColor = MaterialTheme.colorScheme.outline,
        disabledTextColor = MaterialTheme.colorScheme.outline,
        disabledTrailingIconColor = MaterialTheme.colorScheme.outline,
        disabledBorderColor = MaterialTheme.colorScheme.outline,

        errorTextColor = MaterialTheme.colorScheme.error,
        errorCursorColor = MaterialTheme.colorScheme.error,
        errorContainerColor = Color.Transparent,
        errorBorderColor = MaterialTheme.colorScheme.error,
        errorLabelColor = MaterialTheme.colorScheme.error,
        errorLeadingIconColor = MaterialTheme.colorScheme.error,
        errorPlaceholderColor = Metal100,
        errorSupportingTextColor = MaterialTheme.colorScheme.error,
        errorTrailingIconColor = MaterialTheme.colorScheme.error,

        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
        unfocusedContainerColor = Color.Transparent,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.outline,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,

        focusedContainerColor = Color.Transparent,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
        focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
        focusedTextColor = MaterialTheme.colorScheme.primary,
        focusedSupportingTextColor = MaterialTheme.colorScheme.onSurface,
        focusedTrailingIconColor = MaterialTheme.colorScheme.primary,

        cursorColor = MaterialTheme.colorScheme.primary
    ),
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readonly,
        textStyle = textStyle.copy(
            textDirection = TextDirection.Rtl,
        ),
        label = label,
        placeholder = placeHolder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = null,
        suffix = null,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = VisualTransformation.None,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = if (singleLine) 1 else Int.MAX_VALUE,
        minLines = 1,
        interactionSource = remember { MutableInteractionSource() },
        shape = shape,
        colors = colors,
    )

}

@Composable
fun AppTextFieldLabel(value: String) {
    ProvideTextStyle(
        value = MaterialTheme.typography.bodyMedium.copy(
            textDirection = TextDirection.Rtl
        )
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = value
        )
    }
}

@Composable
fun AppTextFieldPlaceHolder(value: String) {
    ProvideTextStyle(
        value = MaterialTheme.typography.bodyMedium.copy(
            textDirection = TextDirection.Rtl
        )
    ) {
        Text(
            text = value
        )
    }
}

@Composable
fun AppTextFieldSupportingText(value: String) {
    ProvideTextStyle(
        value = MaterialTheme.typography.bodySmall.copy(
            textDirection = TextDirection.Rtl
        )
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = value
        )
    }
}

@Composable
@ThemePreviews
fun PreviewAppTextField() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp)
        ) {

            var text by rememberSaveable { mutableStateOf("") }

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = {
                    AppTextFieldLabel(value = "Hello i am inner label")
                },
                placeHolder = {
                    AppTextFieldPlaceHolder(value = "I Am PlaceHolder")
                },
                leadingIcon = {
                    Icon(imageVector = AppIcons.Add, contentDescription = "leading icon")
                },
                trailingIcon = {
                    Icon(
                        imageVector = AppIcons.BookmarkBorder,
                        contentDescription = "trailing icon"
                    )
                },
                supportingText = {
                    AppTextFieldSupportingText(value = "supporting text")
                }
            )

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = {
                    AppTextFieldLabel(value = "شماره تلفن")
                },
                placeHolder = {
                    AppTextFieldPlaceHolder(value = "نمونه")
                },
                leadingIcon = {
                    Icon(imageVector = AppIcons.Add, contentDescription = "leading icon")
                },
                trailingIcon = {
                    Icon(
                        imageVector = AppIcons.BookmarkBorder,
                        contentDescription = "trailing icon"
                    )
                },
                supportingText = {
                    AppTextFieldSupportingText(value = "توضیحات بیشتر")
                }
            )

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = {
                    AppTextFieldLabel(value = "disable label")
                },
                enabled = false,
                placeHolder = {
                    AppTextFieldPlaceHolder(value = "disable place holder")
                },
                leadingIcon = {
                    Icon(imageVector = AppIcons.Add, contentDescription = "leading icon")
                },
                trailingIcon = {
                    Icon(
                        imageVector = AppIcons.BookmarkBorder,
                        contentDescription = "trailing icon"
                    )
                },
                supportingText = {
                    AppTextFieldSupportingText(value = "supporting text")
                }
            )

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = {
                    AppTextFieldLabel(value = "error label")
                },
                isError = true,
                placeHolder = {
                    AppTextFieldPlaceHolder(value = "I am error")
                },
                leadingIcon = {
                    Icon(imageVector = AppIcons.Add, contentDescription = "leading icon")
                },
                trailingIcon = {
                    Icon(
                        imageVector = AppIcons.BookmarkBorder,
                        contentDescription = "trailing icon"
                    )
                },
                supportingText = {
                    AppTextFieldSupportingText(value = "supporting text")
                }
            )

        }
    }
}

