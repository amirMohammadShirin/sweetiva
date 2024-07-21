package com.sweet.iva.navigation

import com.sweet.iva.R
import com.sweet.iva.core.ui.navigation.ApplicationRoutes

enum class TopLevelDestination(
    val title: String,
    val route: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int,
) {
    IVA_PLUS(
        title = "ایوا پلاس",
        route = "",
        selectedIcon = R.drawable.ic_plus_filled,
        unSelectedIcon = R.drawable.ic_plus_bordered,
    ),
    HOME(
        title = "خانه",
        route = ApplicationRoutes.dashboardScreenRoute,
        selectedIcon = R.drawable.ic_home_filled,
        unSelectedIcon = R.drawable.ic_home_bordered,
    ),
    WALLET(
        title = "کیف پول",
        route = "",
        selectedIcon = R.drawable.ic_wallet_filled,
        unSelectedIcon = R.drawable.ic_wallet_bordered,
    ),
    SERVICES(
        title = "خدمات",
        route = "",
        selectedIcon = R.drawable.ic_menu_filled,
        unSelectedIcon = R.drawable.ic_menu_bordered,
    ),
    ;

    companion object {
        fun findByRoute(route: String): TopLevelDestination {
            var result: TopLevelDestination = HOME
            entries.forEach {
                if (it.route.equals(route, true)) result = it
            }
            return result
        }

        fun isTopLevelDestination(route: String): Boolean = entries.any { it.route.equals(route, true) }
    }
}
