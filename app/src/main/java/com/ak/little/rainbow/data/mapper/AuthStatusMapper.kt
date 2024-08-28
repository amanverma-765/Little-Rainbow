package com.ak.little.rainbow.data.mapper

import com.ak.little.rainbow.data.model.SupabaseAuthStatus
import com.ak.little.rainbow.domain.model.AuthStatus

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
