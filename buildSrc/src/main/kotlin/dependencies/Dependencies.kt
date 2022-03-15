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