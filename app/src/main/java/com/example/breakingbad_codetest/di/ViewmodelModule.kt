package com.example.breakingbad_codetest.di

import com.example.breakingbad_codetest.viewmodel.CharacterDetailViewModel
import com.example.breakingbad_codetest.viewmodel.CharactersListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharactersListViewModel(get()) }

    viewModel { CharacterDetailViewModel() }
}
