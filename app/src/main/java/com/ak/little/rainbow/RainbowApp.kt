package com.ak.little.rainbow

import android.app.Application
import com.ak.little.rainbow.data.koin.remoteModule
import com.ak.little.rainbow.data.koin.repositoryModule
import com.ak.little.rainbow.domain.koin.useCaseModule
import com.ak.little.rainbow.navigation.koin.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class RainbowApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RainbowApp)
            androidLogger()
            modules(
                navigationModule,
                useCaseModule,
                remoteModule,
                repositoryModule
            )
        }
    }
}
