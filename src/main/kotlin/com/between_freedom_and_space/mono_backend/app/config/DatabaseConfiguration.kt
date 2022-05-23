package com.between_freedom_and_space.mono_backend.app.config

import org.jetbrains.exposed.sql.Database
import kotlin.properties.Delegates

internal class DatabaseConfiguration {

    var url by Delegates.notNull<String>()

    var driver by Delegates.notNull<String>()

    var userName by Delegates.notNull<String>()

    var password by Delegates.notNull<String>()
}

internal fun configureDatabase() {
    Database.connect(
        url = "jdbc:postgresql://localhost:9000/mono_backend_dev",
        driver = "org.postgresql.Driver",
        user = "user_main_admin",
        password = "WhatIsYourNameDarling?"
    )
}