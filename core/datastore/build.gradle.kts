plugins {
    alias(libs.plugins.my.android.library)
    alias(libs.plugins.my.android.hilt)
}

android {
    namespace = "com.sweet.iva.core.datastore"
}

dependencies {
    implementation(libs.datastore.preferences)
    implementation(libs.datastore.core)
    implementation(libs.gson)
    implementation(projects.core.domain)
}
