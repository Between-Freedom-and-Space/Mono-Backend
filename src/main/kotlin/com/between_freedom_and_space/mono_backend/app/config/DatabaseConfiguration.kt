package com.between_freedom_and_space.mono_backend.app.config

import com.between_freedom_and_space.mono_backend.app.config.properties.DatabaseProperties
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import kotlin.properties.Delegates

internal fun Application.configureDatabase() {
    val properties = getDatabaseProperties()

    Database.connect(
        url = properties.url,
        driver = properties.driver,
        user = properties.userName,
        password = properties.password,
    )
}

private fun Application.getDatabaseProperties(): DatabaseProperties {
    val config = environment.config

    val url = config.property("dataBase.url")
    val driver = config.property("dataBase.driver")
    val userName = config.property("dataBase.userName")
    val password = config.property("dataBase.password")

    return DatabaseProperties(
        url = url.getString(),
        driver = driver.getString(),
        userName = userName.getString(),
        password = password.getString(),
    )
}