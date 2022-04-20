package com.between_freedom_and_space.mono_backend.access.entities.rules.tables

import com.between_freedom_and_space.mono_backend.profiles.entities.UserProfilesTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object AccessedUsersTable: LongIdTable("accessed_users") {

    val user = reference(
        name = "user_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val accessRule = reference(
        name = "access_rule_id", foreign = AccessRulesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val ruleGivenBy = reference(
        name = "rule_given_by_user_id", foreign = UserProfilesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    ).nullable()

    val createdDate = datetime("created_date")

    val updatedDate = datetime("updated_date")
}