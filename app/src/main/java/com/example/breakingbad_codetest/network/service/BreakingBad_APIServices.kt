package com.example.breakingbad_codetest.network.service

import com.example.breakingbad_codetest.database.DatabaseCharacter
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface BreakingBad_APIServices {

    @GET(API_CALLS.API_CHARACTERLIST)
    fun getCharacterList(): Deferred<List<DatabaseCharacter>>
}
