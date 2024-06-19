package com.sweet.iva.core.ui.navigation

object ApplicationRoutes {


    //region Graphs
    const val introGraphRoute = "intro_graph_route"
    const val loginGraphRoute = "login_graph_route"
    //endregion


    //region Screens
    const val introScreenRoute = "intro_screen_route"
    const val phoneEntryScreenRoute = "login_entry_screen_route"
    val loginVerificationScreenRoute = "login_entry_screen_route/{${NavigationParam.TRACKING_CODE}}"
    //endregion

}