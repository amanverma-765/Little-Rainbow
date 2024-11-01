package com.ark.little.rainbow.data.koin

import com.ark.little.rainbow.BuildConfig
import com.ark.little.rainbow.data.remote.supabase.SupabaseAuthManager
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val remoteModule = module {

    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = BuildConfig.supabaseUrl,
            supabaseKey = BuildConfig.supabaseApiKey
        ) {
            defaultSerializer = KotlinXSerializer(
                Json {
                    ignoreUnknownKeys = true
//                    explicitNulls = false
                }
            )
            install(Postgrest)
            install(Auth)
        }
    }

    single { SupabaseAuthManager(supabaseClient = get()) }

}
