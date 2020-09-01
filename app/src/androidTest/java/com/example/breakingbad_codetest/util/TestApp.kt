package com.example.breakingbad_codetest.util

import android.app.Application
import androidx.test.InstrumentationRegistry.getTargetContext
import com.example.breakingbad_codetest.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        /**
         * Start Koin
         */
        startKoin {
          androidContext(  this@TestApp)
            modules(listOf())
        }}
}