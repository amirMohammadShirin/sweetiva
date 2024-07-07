package com.sweet.iva.core.designsystem.component.model

import androidx.compose.ui.graphics.Color

data class UserCardUiModel(
    val id: String,
    val bankImage: Int,
    val bankName: String,
    val pan: String,
    val month: String,
    val year: String,
    var name: String,
    val containerColor: Color,
    val contentColor: Color
)
