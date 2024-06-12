plugins {
    alias(libs.plugins.my.android.library)
    alias(libs.plugins.my.android.hilt)
}

android {
    namespace = "com.sweet.iva.core.network"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
    implementation(libs.gson)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
}
