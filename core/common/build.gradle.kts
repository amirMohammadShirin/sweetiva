plugins {
    alias(libs.plugins.my.android.library)
    alias(libs.plugins.my.android.library.jacoco)
    alias(libs.plugins.my.android.hilt)
}

android {
    namespace = "com.sweet.iva.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}