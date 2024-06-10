

plugins {
    alias(libs.plugins.my.android.application)
    alias(libs.plugins.my.android.application.compose)
    alias(libs.plugins.my.android.application.flavors)
    alias(libs.plugins.my.android.application.jacoco)
    alias(libs.plugins.my.android.hilt)
    id("jacoco")
    id("com.google.android.gms.oss-licenses-plugin")
    alias(libs.plugins.kotlinAndroid)
}

android {
    defaultConfig {
        applicationId = "com.sweet.iva"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        // Custom test runner to set up Hilt dependency graph
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
        }
    }

    buildTypes {

        debug {
//            applicationIdSuffix = ProjectBuildTypes.DEBUG.applicationIdSuffix
        }

        val release by getting {
            isMinifyEnabled = true
//            applicationIdSuffix = ProjectBuildTypes.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("release")
        }

    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    namespace = "com.sweet.iva"
}

dependencies {

    implementation(projects.feature.intro)
    implementation(projects.feature.login)

    implementation(projects.core.database)
    implementation(projects.core.network)
    implementation(projects.core.data)
    implementation(projects.core.common)
    implementation(projects.core.analytics)
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.cache)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)

    kaptTest(libs.hilt.compiler)

}
