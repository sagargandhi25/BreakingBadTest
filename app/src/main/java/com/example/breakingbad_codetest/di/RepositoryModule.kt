package com.example.breakingbad_codetest.di


import com.example.breakingbad_codetest.database.CharacterDatabase
import com.example.breakingbad_codetest.network.service.BreakingBad_APIServices
import com.example.breakingbad_codetest.repository.CharactersRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideCharacterRepository(api: BreakingBad_APIServices, dao: CharacterDatabase): CharactersRepository {
        return CharactersRepository(api, dao)
    }
    single { provideCharacterRepository(get(), get()) }
}