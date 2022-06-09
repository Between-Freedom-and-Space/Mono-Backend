package com.between_freedom_and_space.mono_backend.access.entities.rules.tables

import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserRolesTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object AccessedRolesTable: LongIdTable("accessed_rules") {

    val role = reference(
        name = "role_id", foreign = UserRolesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val accessRule = reference(
        name = "access_rule_id", foreign = AccessRulesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    )

    val ruleGivenBy = reference(
        name = "rule_given_by_user_id", foreign = UserRolesTable,
        onDelete = NO_ACTION, onUpdate = NO_ACTION
    ).nullable()

    val createdDate = datetime("created_date").clientDefault { localDateTimeNow() }

    val updatedDate = datetime("updated_date")
}