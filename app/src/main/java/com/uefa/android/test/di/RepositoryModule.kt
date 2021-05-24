package com.uefa.android.test.di

import com.uefa.android.test.SquadsApi
import com.uefa.android.test.repository.IRepository
import com.uefa.android.test.repository.DemoRepositoryImpl
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideRepository(api: SquadsApi, context: Context): IRepository {
        return DemoRepositoryImpl(api, context)
    }
    single { provideRepository(get(), androidContext()) }

}