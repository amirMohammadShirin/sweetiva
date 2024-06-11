package com.sweet.iva

/**
 * Created by aShirin on 6/11/2024.
 */
enum class ProjectBuildTypes(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE(".release")
}
