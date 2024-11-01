package com.ark.little.rainbow.data.mapper

import com.ark.little.rainbow.data.model.SupabaseAuthStatus
import com.ark.little.rainbow.domain.model.AuthStatus

object AuthStatusMapper {

    fun SupabaseAuthStatus.toAuthStatus(): AuthStatus {
        return when (this) {
            is SupabaseAuthStatus.Authenticated -> AuthStatus.Authenticated(metadata)
            is SupabaseAuthStatus.NotAuthenticated -> AuthStatus.NotAuthenticated
            is SupabaseAuthStatus.LoadingFromStorage,
            is SupabaseAuthStatus.NetworkError -> AuthStatus.NotAuthenticated
        }
    }

}
