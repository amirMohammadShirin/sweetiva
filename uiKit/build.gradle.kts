import com.sweet.iva.FlavorDimension
import com.sweet.iva.IvaFlavor


plugins {
    alias(libs.plugins.my.android.application)
    alias(libs.plugins.my.android.application.compose)
}

android {
    defaultConfig {
        applicationId = "com.sweet.iva.uiKit"
        versionCode = 1
        versionName = "0.0.1"
        minSdk = 26

        missingDimensionStrategy(FlavorDimension.contentType.name, IvaFlavor.demo.name)

    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    namespace = "com.sweet.task.uiKit"
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(libs.androidx.activity.compose)
}