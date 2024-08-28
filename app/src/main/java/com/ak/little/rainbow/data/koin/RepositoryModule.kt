package com.ak.little.rainbow.data.koin

import com.ak.little.rainbow.data.repo.AdminAuthRepoImpl
import com.ak.little.rainbow.domain.repo.AdminAuthRepo
import org.koin.dsl.module

val repositoryModule = module {

    single<AdminAuthRepo> {
        AdminAuthRepoImpl(
            supabaseAuthManager = get()
        )
    }

}
