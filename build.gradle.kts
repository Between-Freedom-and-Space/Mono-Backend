import dependencies.*
import extensions.implementation
import extensions.testImplementation

plugins {
    application
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
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
    implementation(KtorServerJWT)
    implementation(KtorMetrics)
    implementation(KtorMetricsMicrometer)
    implementation(KtorCallLogging)
    implementation(KtorCORS)

    // Serialization
    implementation(KtorSerialization)
    implementation(KotlinXSerializationJson)
    implementation(KtorServerNegotiation)
    implementation(KtorSerializationKotlinX)

    // DI Koin
    implementation(KoinCore)
    implementation(KtorKoin)
    implementation(KoinLoggerSlf4j)

    // Logging and monitoring
    implementation(KotlinLogger)
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

    // Hashing
    implementation(ApacheCommonsCodec)

    // Validation
    implementation(JavaXValidation)
    implementation(HibernateValidator)
    implementation(JavaXUEL)
    implementation(GlassFishUEL)

    // Tests
    testImplementation(KtorServerTests)
    testImplementation(KotlinJUnit)
    testImplementation(KotlinMockito)
}