package com.ark.little.rainbow.data.remote.supabase

import com.ark.little.rainbow.data.model.dto.SchoolClassDto
import com.ark.little.rainbow.utils.ApiResponse
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.BadRequestRestException
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SupabaseDbManager(
    private val supabaseClient: SupabaseClient
) {

    fun getAllClasses(): Flow<ApiResponse<List<SchoolClassDto>>> {
        return flow {
            try {

                emit(ApiResponse.Loading)
                val response =
                    supabaseClient.postgrest["school_classes"].select().decodeList<SchoolClassDto>()
                emit(ApiResponse.Success(response))

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