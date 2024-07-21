package com.sweet.iva.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun AppShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier =
        modifier
            .shimmer()
            .background(
                MaterialTheme.colorScheme.outline,
                RoundedCornerShape(10.dp),
            ),
    ) {}
}
