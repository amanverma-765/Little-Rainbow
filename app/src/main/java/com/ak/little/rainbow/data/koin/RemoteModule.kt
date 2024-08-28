package com.ak.little.rainbow.data.koin

import com.ak.little.rainbow.BuildConfig
import com.ak.little.rainbow.data.remote.supabase.SupabaseAuthManager
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import org.koin.dsl.module

val remoteModule = module {

    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = BuildConfig.supabaseUrl,
            supabaseKey = BuildConfig.supabaseApiKey
        ) {
//            defaultSerializer = KotlinXSerializer(
//                Json {
//                    ignoreUnknownKeys = true
////                    explicitNulls = false
//                }
//            )
            install(Postgrest)
            install(Auth)
        }
    }

    single { SupabaseAuthManager(supabaseClient = get()) }

}
