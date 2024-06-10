package com.sweet.iva.uiKit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.sweet.iva.uiKit.ui.AppUiKit
import com.sweet.task.uiKit.R

class UiKitActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark_ui_kit)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppUiKit()
        }
    }
}