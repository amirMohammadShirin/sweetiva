package com.sweet.iva.core.designsystem.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.jetbrains.annotations.VisibleForTesting

/**
 * Light default theme color scheme
 */
@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = Yellow500,
    onPrimary = Black,
    primaryContainer = Yellow500,
    onPrimaryContainer = Black,
    inversePrimary = BlueRoyal50,
    secondary = BlueRoyal500,
    onSecondary = White,
    secondaryContainer = BlueRoyal500,
    onSecondaryContainer = White,
    tertiary = Gray500,
    onTertiary = Gray800,
    tertiaryContainer = Gray500,
    onTertiaryContainer = Gray800,
    error = Error,
    onError = White,
    errorContainer = ErrorContainer,
    onErrorContainer = Red50,
    background = Gray600,
    onBackground = Black,
    surface = Gray500,
    onSurface = Gray800,
    outline = Gray600,
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkDefaultColorScheme = LightDefaultColorScheme

/**
 * Dark android theme color scheme
 */
@VisibleForTesting
val DarkAndroidColorScheme = DarkDefaultColorScheme

/**
 * Light android theme color scheme
 */
@VisibleForTesting
val LightAndroidColorScheme = LightDefaultColorScheme

/**
 * Light android gradient colors
 */
val LightAndroidGradientColors = GradientColor(container = Metal200)

/**
 * Dark android gradient colors
 */
val DarkAndroidGradientColors = GradientColor(container = Metal200)

/**
 * Light android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = Color.Blue)

/**
 * Dark android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Blue)

val MaterialTheme.dimens: Dimens
    get() = Dimens

/**
 * Mark2 theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme.
 * @param disableDynamicTheming If `true`, disables the use of dynamic theming, even when it is
 *        supported. This parameter has no effect if [androidTheme] is `true`.
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        androidTheme -> if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
        !disableDynamicTheming && supportsDynamicTheming() -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }

    val emptyGradientColor = GradientColor(container = colorScheme.surfaceColorAtElevation(2.dp))
    val defaultGradientColor = GradientColor(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primary,
        container = colorScheme.surface
    )
    val gradientColors = when {
        androidTheme -> if (darkTheme) DarkAndroidGradientColors else LightAndroidGradientColors
        !disableDynamicTheming && supportsDynamicTheming() -> emptyGradientColor
        else -> defaultGradientColor
    }

    val defaultBackgroundTheme = BackgroundTheme(
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 2.dp
    )
    val backgroundTheme = when {
        androidTheme -> if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
        else -> defaultBackgroundTheme
    }

    val tintTheme = when {
        androidTheme -> TintTheme()
        !disableDynamicTheming && supportsDynamicTheming() -> TintTheme(colorScheme.primary)
        else -> TintTheme()
    }

    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = IvaTypography,
            content = content
        )
    }

}


@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
