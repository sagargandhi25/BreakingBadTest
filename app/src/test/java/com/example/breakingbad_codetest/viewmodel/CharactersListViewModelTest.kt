package com.example.breakingbad_codetest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.example.breakingbad_codetest.repository.CharactersRepository
import com.example.breakingbad_codetest.repository.LiveDataTestUtil
import com.example.breakingbad_codetest.util.EspressoIdlingResourceRule
import com.example.breakingbad_codetest.util.networkutils.LoadingState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException
import java.lang.RuntimeException

class CharactersListViewModelTest {

    @Mock
    private lateinit var mRepo: CharactersRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var charactersListViewModel: CharactersListViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    fun test_refreshDataFromRepository_do_what_it_should_do() = runBlocking {

        //prep
        charactersListViewModel = CharactersListViewModel(mRepo)

        val liveDataTestUtil = LiveDataTestUtil<LoadingState>()
        //action
        //when charactersListViewModel is created refreshDataFromRepository is called.
        //verify
        Mockito.verify(mRepo).refreshCharacters()
        val dataEmitted = liveDataTestUtil.getValue(charactersListViewModel.loadingState)
        assertEquals( LoadingState.Status.SUCCESS.name,dataEmitted?.status?.name)
    }

    @Test
    fun test_loading_error_is_called() = runBlocking {
        //prep
        //action
        //when charactersListViewModel is created refreshDataFromRepository is called.
        //verify
        Mockito.`when`(mRepo.refreshCharacters()).thenThrow(RuntimeException())
        charactersListViewModel = CharactersListViewModel(mRepo)
        val mediatorLiveData = MediatorLiveData<LoadingState>()
        mediatorLiveData.addSource(charactersListViewModel.loadingState){
            when(it.status){
                 LoadingState.Status.RUNNING -> {
                      //ignore
                 }
                else -> {
                    assertEquals( LoadingState.Status.FAILED.name,it?.status?.name)

                }
            }
        }
    }
}