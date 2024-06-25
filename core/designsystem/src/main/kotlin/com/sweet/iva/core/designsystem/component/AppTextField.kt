package com.sweet.iva.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sweet.iva.core.designsystem.icon.AppIcons
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.Metal100
import com.sweet.iva.core.designsystem.theme.dimens
import com.sweet.iva.core.designsystem.theme.iransansFamily


@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    readonly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: String? = null,
    placeHolder: String? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    supportingText: String? = null,
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
        focusedTextColor = MaterialTheme.colorScheme.onBackground,
        focusedSupportingTextColor = MaterialTheme.colorScheme.onSurface,
        focusedTrailingIconColor = MaterialTheme.colorScheme.primary,

        cursorColor = MaterialTheme.colorScheme.primary
    ),
) {

    Column(
        modifier = modifier
            .padding(MaterialTheme.dimens.defaultPadding),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        label?.let {
            AppTextFieldLabel(
                value = label,
                Modifier.padding(horizontal = MaterialTheme.dimens.smallPadding)
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.defaultGap))

        Box {

            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                enabled = enabled,
                readOnly = readonly,
                textStyle = textStyle.copy(
                    textDirection = TextDirection.Rtl,
                    fontFamily = iransansFamily,

                    ),
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                prefix = null,
                suffix = null,
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

            placeHolder?.let {
                if (value.isEmpty() || value.isBlank()) {
                    AppTextFieldPlaceHolder(
                        value = placeHolder,
                        modifier
                            .offset(
                                x = if (trailingIcon != null) (-50).dp else (-10).dp,
                                y = (20).dp
                            )
                            .matchParentSize()
                            .padding(horizontal = MaterialTheme.dimens.smallPadding)
                    )
                }
            }

        }

        supportingText?.let {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.smallGap))
            AppTextFieldSupportingText(
                value = supportingText,
                modifier = Modifier.padding(horizontal = MaterialTheme.dimens.smallPadding),
                color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
            )
        }


    }

}

@Composable
fun AppTextFieldLabel(value: String, modifier: Modifier = Modifier) {
    ProvideTextStyle(value = MaterialTheme.typography.bodyMedium) {
        Text(
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium.copy(
                textDirection = TextDirection.Rtl
            ),
            text = value
        )
    }
}

@Composable
fun AppTextFieldPlaceHolder(value: String, modifier: Modifier = Modifier) {
    ProvideTextStyle(
        value = MaterialTheme.typography.labelMedium.copy(
            textDirection = TextDirection.Rtl
        )
    ) {
        Text(
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier,
            textAlign = TextAlign.Start,
            text = value
        )
    }
}

@Composable
fun OTPInput(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    size: Int
) {


    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            if (it.length <= size) onValueChange.invoke(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(size) { index ->
                    OTPCharView(
                        index = index,
                        text = value
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )

}

@Composable
private fun OTPCharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }

    ProvideTextStyle(value = MaterialTheme.typography.bodyLarge) {
        Text(
            maxLines = 1,
            modifier = Modifier
                .width(40.dp)
                .height(50.dp)
                .border(
                    1.dp, when {
                        isFocused -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.onSurface
                    }, RoundedCornerShape(8.dp)
                )
                .padding(2.dp),
            text = char,
            color = if (isFocused) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            textAlign = TextAlign.Center,
            lineHeight = 50.sp
        )
    }

}

@Composable
fun AppTextFieldSupportingText(
    value: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    ProvideTextStyle(
        value = MaterialTheme.typography.labelSmall.copy(
            textDirection = TextDirection.Rtl
        )
    ) {
        Text(
            color = color,
            modifier = modifier,
            textAlign = TextAlign.Center,
            text = value
        )
    }
}

@ThemePreviews
@Composable
private fun PreviewOTPInput() {

    AppTheme {

        var value by remember {
            mutableStateOf("")
        }


        OTPInput(
            modifier = Modifier.fillMaxWidth(),
            value = value, onValueChange = { value = it },
            size = 6
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
                label = "Hello i am inner label",
                placeHolder = "I Am PlaceHolder",
                leadingIcon = {
                    Icon(imageVector = AppIcons.Add, contentDescription = "leading icon")
                },
                trailingIcon = {
                    Icon(
                        imageVector = AppIcons.BookmarkBorder,
                        contentDescription = "trailing icon"
                    )
                },
                supportingText = "supporting text"
            )

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = "شماره تلفن",
                placeHolder = "نمونه",
                leadingIcon = {
                    Icon(imageVector = AppIcons.Add, contentDescription = "leading icon")
                },
                supportingText = "توضیحات بیشتر"
            )

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = "disable label",
                enabled = false,
                placeHolder = "disable place holder",
                leadingIcon = {
                    Icon(imageVector = AppIcons.Add, contentDescription = "leading icon")
                },
                trailingIcon = {
                    Icon(
                        imageVector = AppIcons.BookmarkBorder,
                        contentDescription = "trailing icon"
                    )
                },
                supportingText = "supporting text"
            )

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = "error label",
                isError = true,
                placeHolder = "I am error",
                leadingIcon = {
                    Icon(imageVector = AppIcons.Add, contentDescription = "leading icon")
                },
                trailingIcon = {
                    Icon(
                        imageVector = AppIcons.BookmarkBorder,
                        contentDescription = "trailing icon"
                    )
                },
                supportingText = "supporting text"
            )

        }
    }
}

