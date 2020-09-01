package com.example.breakingbad_codetest.repository

import androidx.lifecycle.LiveData
import com.example.breakingbad_codetest.database.CharacterDatabase
import com.example.breakingbad_codetest.database.DatabaseCharacter
import com.example.breakingbad_codetest.network.service.BreakingBad_APIServices
import com.example.breakingbad_codetest.util.OpenForTesting
import com.example.breakingbad_codetest.util.networkutils.LoadingState


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


@OpenForTesting
class CharactersRepository(private val breakingbadApiservices: BreakingBad_APIServices, private val database: CharacterDatabase) {
    suspend fun refreshCharacters() {
        withContext(Dispatchers.IO) {
                Timber.d("refresh characters is called");
                val characterList = breakingbadApiservices.getCharacterList().await()
                database.characterDao.insertAll(characterList)
            }
    }

    //function for Search
    fun getItemsSearch(filter: String): LiveData<List<DatabaseCharacter>> {
        return database.characterDao.getItemsSearch(filter)
    }

    //function for filter based on appearance
    fun getItemsByAppearance(season: String): LiveData<List<DatabaseCharacter>> {
        return database.characterDao.getLocalCharactersByAppearance(season)
    }
}