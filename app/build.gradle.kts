import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
//    id("com.google.devtools.ksp")
    kotlin("kapt")
}

val properties = loadProperties()

android {
    namespace = "com.android.currencyconverter"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.currencyconverter"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "API_KEY", "\"${properties.getProperty("sdk.open_exchange_api_key")}\"")
    }

//    junitPlatform {
//        // Configure JUnit 5 tests here
//        filters("debug") {
//            excludeTags("slow")
//        }
//
//        // Using local dependency instead of Maven coordinates
//        instrumentationTests.enabled = false
//    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


    testOptions {
        unitTests {
            all {
                it.jvmArgs = listOf("-noverify","-XX:MaxPermSize=256m")
                it.testClassesDirs
            }
            isIncludeAndroidResources = true
        }

    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
//    ksp("com.google.dagger:hilt-android-compiler:2.48")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


    //Room Database
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
//    ksp("androidx.room:room-compiler:2.6.1")

    testImplementation("junit:junit:4.13.2")
    testImplementation ("org.robolectric:robolectric:4.11.1")
    testImplementation("androidx.room:room-testing:2.6.1")
    testImplementation("com.google.dagger:hilt-android-testing:2.48")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    kaptTest("com.google.dagger:hilt-android-compiler:2.48")

    testImplementation("androidx.compose.ui:ui-test-junit4:1.6.8")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.8")

    androidTestImplementation("androidx.test.ext:junit:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")

}

kapt {
    correctErrorTypes = true
}

fun loadProperties(): Properties {
    val properties = Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }
    if (properties.getProperty("sdk.open_exchange_api_key").isEmpty()) {
        throw GradleException("Please Open Exchange API Key in local.properties")
    }
    return properties
}