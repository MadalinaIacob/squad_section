package com.uefa.android.test.core

import android.app.Application
import com.uefa.android.test.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UefaAndroidTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
      startKoin {
            androidLogger()
            androidContext(this@UefaAndroidTestApplication)
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule
            )
        }
    }
}