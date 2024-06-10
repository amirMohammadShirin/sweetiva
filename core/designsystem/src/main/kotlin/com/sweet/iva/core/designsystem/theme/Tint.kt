package com.sweet.iva.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * A class to model background color and tonal elevation values for Mark2.
 */
@Immutable
data class TintTheme(
    val iconTint: Color? = null
)

/**
 * A composition local fo [TintTheme]
 */
val LocalTintTheme = staticCompositionLocalOf { TintTheme() }
