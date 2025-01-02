import io.gitlab.arturbosch.detekt.Detekt


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt.plugin) apply true
    alias(libs.plugins.android.library) apply false
}

detekt {
    toolVersion = "1.23.7"
    config.setFrom(file("detekt.yml"))
    buildUponDefaultConfig = true
    val input = projectDir
    val exclude = listOf("**/build/**", "**/resources/**")
    source.setFrom(
        fileTree(input) {
            exclude(exclude)
        }
    )

    parallel = true // Run Detekt in parallel
}

dependencies {
    detektPlugins(libs.detekt)
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}
