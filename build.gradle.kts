import dependencies.*
import extensions.testImplementation
import extensions.implementation

plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = Config.GROUP
version = Config.VERSION
application {
    mainClass.set(Config.APPLICATION_MAIN_CLASS)
}

repositories {
    mavenCentral()
    google()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation(KtorServerCore)
    implementation(KtorServerJetty)
    implementation(KtorServerAuth)
    implementation(KtorMetrics)
    implementation(KtorMetricsMicrometer)
    implementation(KtorCallLogging)
    implementation(KtorCORS)

    implementation(LogbackClassic)
    implementation(MicrometerPrometheus)

    testImplementation(KtorServerTests)
    testImplementation(KotlinJUnit)
}