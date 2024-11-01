package com.ark.little.rainbow.data.koin

import com.ark.little.rainbow.data.repo.AdminAuthRepoImpl
import com.ark.little.rainbow.domain.repo.AdminAuthRepo
import org.koin.dsl.module

val repositoryModule = module {

    single<AdminAuthRepo> {
        AdminAuthRepoImpl(
            supabaseAuthManager = get()
        )
    }

}
