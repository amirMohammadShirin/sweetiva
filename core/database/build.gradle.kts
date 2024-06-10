plugins {
    alias(libs.plugins.my.android.library)
    alias(libs.plugins.my.android.hilt)
    alias(libs.plugins.my.android.room)
}

android {
    namespace = "com.sweet.iva.core.database"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.common)
    implementation(projects.core.data)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
}
