package com.amarinag.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    val results: T
)