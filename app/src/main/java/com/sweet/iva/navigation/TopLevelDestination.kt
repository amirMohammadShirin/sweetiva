package com.sweet.iva.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.sweet.iva.core.designsystem.icon.AppIcons

enum class TopLevelDestination(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
) {
    HOME(
        title = "Home",
        route = "",
        selectedIcon = AppIcons.Home,
        unSelectedIcon = AppIcons.HomeBorder
    );


    companion object {

        fun findByRoute(route: String): TopLevelDestination {
            var result: TopLevelDestination = HOME
            entries.forEach {
                if (it.route.equals(route, true)) result = it
            }
            return result
        }

        fun isTopLevelDestination(route: String): Boolean {
            return entries.any { it.route.equals(route, true) }
        }


    }


}

