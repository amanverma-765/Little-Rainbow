package com.ark.little.rainbow.data.remote.supabase

import com.ark.little.rainbow.data.model.SupabaseAuthStatus
import com.ark.little.rainbow.utils.ApiResponse
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.OTP
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.exceptions.BadRequestRestException
import io.github.jan.supabase.exceptions.RestException
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

            } catch (e: BadRequestRestException) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.description))
            } catch (e: RestException) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.description))
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

            } catch (e: BadRequestRestException) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.description))
            } catch (e: RestException) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.description))
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

            } catch (e: BadRequestRestException) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.description))
            } catch (e: RestException) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.description))
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
                        is SessionStatus.Authenticated -> {
                            val metadata = status.session.user?.userMetadata
                            emit(ApiResponse.Success(SupabaseAuthStatus.Authenticated(metadata)))
                        }

                        is SessionStatus.NotAuthenticated -> emit(
                            ApiResponse.Success(
                                SupabaseAuthStatus.NotAuthenticated
                            )
                        )

                        is SessionStatus.Initializing -> Unit
                        is SessionStatus.RefreshFailure -> Unit
                    }
                }

            } catch (e: BadRequestRestException) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.description))
            } catch (e: RestException) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.description))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResponse.Error(e.message))
            }
        }
    }
}
