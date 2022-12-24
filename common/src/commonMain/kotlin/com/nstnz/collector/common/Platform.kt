package com.nstnz.collector.common

import com.squareup.sqldelight.db.SqlDriver

expect class Platform() {
    val platform: String
}