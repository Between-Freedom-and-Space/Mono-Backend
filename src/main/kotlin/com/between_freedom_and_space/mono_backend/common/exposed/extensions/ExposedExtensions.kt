package com.between_freedom_and_space.mono_backend.common.exposed.extensions

import org.jetbrains.exposed.sql.*

fun <T: FieldSet> T.exists(where: SqlExpressionBuilder.() -> Op<Boolean>): Boolean {
    val existsOp = exists(this.select(where))
    val result = Table.Dual.slice(existsOp).selectAll().first()
    return result[existsOp]
}