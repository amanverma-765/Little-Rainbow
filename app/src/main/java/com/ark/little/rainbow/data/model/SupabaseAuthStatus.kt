package com.ark.little.rainbow.data.model

import kotlinx.serialization.json.JsonObject

sealed interface SupabaseAuthStatus {

    data class Authenticated(val metadata: JsonObject?) : SupabaseAuthStatus
    data object NotAuthenticated : SupabaseAuthStatus
    data object LoadingFromStorage : SupabaseAuthStatus
    data object NetworkError : SupabaseAuthStatus

}
