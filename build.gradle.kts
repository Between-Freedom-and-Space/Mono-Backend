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

    // Ktor plugins and modules
    implementation(KtorServerCore)
    implementation(KtorServerJetty)
    implementation(KtorServerAuth)
    implementation(KtorMetrics)
    implementation(KtorMetricsMicrometer)
    implementation(KtorCallLogging)
    implementation(KtorCORS)

    // DI Koin
    implementation(KtorKoin)
    implementation(KoinLoggerSlf4j)

    // Logging and monitoring
    implementation(LogbackClassic)
    implementation(MicrometerPrometheus)

    // Exposed ORM
    implementation(ExposedCore)
    implementation(ExposedDao)
    implementation(ExposedJDBC)
    implementation(ExposedKotlinDateTime)

    // Databases
    implementation(H2Database)
    implementation(PostgresDatabase)
    implementation(HikariCP)

    // Tests
    testImplementation(KtorServerTests)
    testImplementation(KotlinJUnit)
}