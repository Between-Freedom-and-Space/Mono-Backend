package dependencies

object KtorServerCore: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-core-jvm"
    override val version = Versions.KTOR
}

object KtorSerialization: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-serialization"
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

object KtorServerJWT: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-auth-jwt-jvm"
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

object KoinCore: ProjectDependency {
    override val group = "io.insert-koin"
    override val artifact = "koin-core"
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

object KotlinMockito: ProjectDependency {
    override val group = "org.mockito.kotlin"
    override val artifact = "mockito-kotlin"
    override val version = Versions.KOTLIN_MOCKITO
}

object KotlinXSerializationJson: ProjectDependency {
    override val group = "org.jetbrains.kotlinx"
    override val artifact = "kotlinx-serialization-json"
    override val version = Versions.KOTLINX_SERIALIZATION
}

object KtorServerNegotiation: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-server-content-negotiation"
    override val version = Versions.KTOR
}

object KtorSerializationKotlinX: ProjectDependency {
    override val group = "io.ktor"
    override val artifact = "ktor-serialization-kotlinx-json"
    override val version = Versions.KTOR
}

object KotlinLogger: ProjectDependency {
    override val group = "io.github.microutils"
    override val artifact = "kotlin-logging-jvm"
    override val version = Versions.KOTLIN_LOGGING
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

object ApacheCommonsCodec: ProjectDependency {
    override val group = "commons-codec"
    override val artifact = "commons-codec"
    override val version = Versions.APACHE_COMMONS
}

object JavaXValidation: ProjectDependency {
    override val group = "javax.validation"
    override val artifact = "validation-api"
    override val version = Versions.JAVAX_VALIDATION

}

object HibernateValidator: ProjectDependency {
    override val group = "org.hibernate.validator"
    override val artifact = "hibernate-validator"
    override val version = Versions.HIBERNATE_VALIDATOR
}

object JavaXUEL: ProjectDependency {
    override val group = "javax.el"
    override val artifact = "javax.el-api"
    override val version = Versions.JAVAX_UEL
}

object GlassFishUEL: ProjectDependency {
    override val group = "org.glassfish.web"
    override val artifact = "javax.el"
    override val version = Versions.UEL
}