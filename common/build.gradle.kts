plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.apollo)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.apollo.runtime)
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}
apollo {
    service("rickAndMorty") {
        packageName.set("com.example.common")
        introspection {
            endpointUrl.set("https://rickandmortyapi.com/graphql")
            schemaFile.set(file("src/main/graphql/schema.graphqls"))
        }
    }
}
