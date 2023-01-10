package com.nstnz.collector.common.feature.core.domain.model

data class AccountModel(
    val googleAuthToken: String?,
    val email: String?,
    val name: String?,
    val photoUrl: String?,
)