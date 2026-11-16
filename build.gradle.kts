plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    `maven-publish`
}

group = "com.github.Pobiwan007"
version = "1.0.0"

android {
    namespace = "com.basecomposeview"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(libs.pobiwan.common)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.graphics.shapes)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil.compose)

    testImplementation(libs.junit)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            artifactId = "baseComposeView"
            afterEvaluate { from(components["release"]) }
        }
    }
}
