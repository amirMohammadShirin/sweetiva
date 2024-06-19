package com.sweet.iva.core.ui.navigation

enum class NavigationParam {

    TRACKING_CODE,
    PHONE_NUMBER;

    companion object {
        fun getByName(name: String): NavigationParam? {
            entries.forEach {
                if (name.equals(it.name, true)) return it
            }
            return null
        }
    }

}