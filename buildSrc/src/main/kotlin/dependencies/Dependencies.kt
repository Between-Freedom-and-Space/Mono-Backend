package dependencies

object KtorServerCore: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-core-jvm"
    override val version = Versions.KTOR
}

object KtorServerJetty: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-netty-jvm"
    override val version = Versions.KTOR
}

object KtorServerAuth: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-auth-jvm"
    override val version = Versions.KTOR
}

object KtorMetrics: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-metrics-jvm"
    override val version = Versions.KTOR
}

object KtorMetricsMicrometer: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-metrics-micrometer-jvm"
    override val version = Versions.KTOR
}

object KtorCallLogging: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-call-logging-jvm"
    override val version = Versions.KTOR
}

object KtorCORS: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-cors-jvm"
    override val version = Versions.KTOR
}

object KtorServerTests: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-tests-jvm"
    override val version = Versions.KTOR
}

object KtorKoin: ProjectDependency {
    override val group = "io.insert-koin"
    override val artifact = "koin-ktor"
    override val version = Versions.KOIN
}

object KoinLoggerSlf4j: ProjectDependency {
    override val group = "io.insert-koin"
    override val artifact = "koin-logger-slf4j"
    override val version = Versions.KOIN
}

object KotlinJUnit: ProjectDependency {
    override val group = "org.jetbrains.kotlin"
    override val artifact = "kotlin-test-junit"
    override val version = Versions.KOTLIN
}

object LogbackClassic: ProjectDependency {
    override val group = "ch.qos.logback"
    override val artifact = "logback-classic"
    override val version = Versions.LOG_BACK
}

object MicrometerPrometheus: ProjectDependency {
    override val group = "io.micrometer"
    override val artifact = "micrometer-registry-prometheus"
    override val version = Versions.PROMETHEUS
}

object ExposedCore: ProjectDependency {
    override val group = "org.jetbrains.exposed"
    override val artifact = "exposed-core"
    override val version = Versions.EXPOSED
}

object ExposedDao: ProjectDependency {
    override val group = "org.jetbrains.exposed"
    override val artifact = "exposed-dao"
    override val version = Versions.EXPOSED
}

object ExposedJDBC: ProjectDependency {
    override val group = "org.jetbrains.exposed"
    override val artifact = "exposed-jdbc"
    override val version = Versions.EXPOSED
}

object ExposedKotlinDateTime: ProjectDependency {
    override val group = "org.jetbrains.exposed"
    override val artifact = "exposed-kotlin-datetime"
    override val version = Versions.EXPOSED
}

object H2Database: ProjectDependency {
    override val group = "com.h2database"
    override val artifact = "h2"
    override val version = Versions.H2
}

object PostgresDatabase: ProjectDependency {
    override val group = "org.postgresql"
    override val artifact = "postgresql"
    override val version = Versions.POSTGRES
}

object HikariCP: ProjectDependency {
    override val group = "com.zaxxer"
    override val artifact = "HikariCP"
    override val version = Versions.HIKARI
}
