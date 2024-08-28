package com.ak.little.rainbow.data.remote.supabase

import com.ak.little.rainbow.data.model.SupabaseAuthStatus
import com.ak.little.rainbow.utils.ApiResponse
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SupabaseAuthManager(
    private val supabaseClient: SupabaseClient
) {

    fun signInWithMagicLink(email: String): Flow<ApiResponse<Unit>> {
        return flow {
            try {

                emit(ApiResponse.Loading)

                supabaseClient.auth.signInWith(provider = OTP) {
                    this.email = email
                    this.createUser = false
                }

                emit(ApiResponse.Success(Unit))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.message))
            }
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String): Flow<ApiResponse<Unit>> {
        return flow {
            try {

                emit(ApiResponse.Loading)

                supabaseClient.auth.signInWith(provider = Email) {
                    this.email = email
                    this.password = password
                }

                emit(ApiResponse.Success(Unit))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.message))
            }
        }
    }

    fun adminSignOut(): Flow<ApiResponse<Unit>> {
        return flow {
            try {

                emit(ApiResponse.Loading)
                supabaseClient.auth.signOut()
                emit(ApiResponse.Success(Unit))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.message))
            }
        }
    }

    fun listenAuthStatus(): Flow<ApiResponse<SupabaseAuthStatus>> {
        return flow {
            try {

                emit(ApiResponse.Loading)
                supabaseClient.auth.sessionStatus.collect { status ->
                    when (status) {
                        is SessionStatus.Authenticated ->{
                            val metadata = status.session.user?.userMetadata
                            emit(ApiResponse.Success(SupabaseAuthStatus.Authenticated(metadata)))
                        }
                        is SessionStatus.LoadingFromStorage -> emit(ApiResponse.Success(SupabaseAuthStatus.LoadingFromStorage))
                        is SessionStatus.NetworkError -> emit(ApiResponse.Success(SupabaseAuthStatus.NetworkError))
                        is SessionStatus.NotAuthenticated -> emit(ApiResponse.Success(SupabaseAuthStatus.NotAuthenticated))
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.message))
            }
        }
    }
}
