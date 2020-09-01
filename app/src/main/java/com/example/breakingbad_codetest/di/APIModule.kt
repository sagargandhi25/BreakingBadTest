package com.example.breakingbad_codetest.di

import com.example.breakingbad_codetest.network.service.BreakingBad_APIServices
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): BreakingBad_APIServices {
        return retrofit.create(BreakingBad_APIServices::class.java)
    }

    single { provideUserApi(get()) }
}