package com.enrech.ulessontest.common_domain

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = ULessonCacheDb.Schema,
            name = DBConstant.FILE_NAME,
            // to be added on a MAC system
//            onConfiguration = { config ->
//                return@NativeSqliteDriver config.copy(
//                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
//                )
//            }
        )
    }
}