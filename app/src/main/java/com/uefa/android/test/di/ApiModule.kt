package com.uefa.android.test.di

import com.uefa.android.test.SquadsApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideApi(retrofit: Retrofit): SquadsApi {
        return retrofit.create(SquadsApi::class.java)
    }
    single { provideApi(get()) }

}