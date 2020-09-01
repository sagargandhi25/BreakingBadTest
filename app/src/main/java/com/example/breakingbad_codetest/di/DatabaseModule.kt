package com.example.breakingbad_codetest.di


import android.app.Application
import androidx.room.Room
import com.example.breakingbad_codetest.database.CharacterDao
import com.example.breakingbad_codetest.database.CharacterDatabase

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): CharacterDatabase {
        return Room.databaseBuilder(application, CharacterDatabase::class.java, "breakingbad.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: CharacterDatabase): CharacterDao {
        return database.characterDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}
