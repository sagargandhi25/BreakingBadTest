package com.example.breakingbad_codetest.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.breakingbad_codetest.database.CharacterDao
import com.example.breakingbad_codetest.database.CharacterDatabase
import org.junit.After
import org.junit.Before
import java.lang.Exception

open class DatabaseTest {
    // system under test
    protected lateinit var database: CharacterDatabase
    val characterDao: CharacterDao
        get() = database.characterDao

    @Before
    open fun init() {
        //Create a temporally database
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharacterDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun finish() {
        try {
            database.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}