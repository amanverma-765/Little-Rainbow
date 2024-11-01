package com.ark.little.rainbow.domain.model

import kotlinx.serialization.json.JsonObject

sealed interface AuthStatus {

    data class Authenticated(val metadata: JsonObject?) : AuthStatus
    data object NotAuthenticated : AuthStatus

}
