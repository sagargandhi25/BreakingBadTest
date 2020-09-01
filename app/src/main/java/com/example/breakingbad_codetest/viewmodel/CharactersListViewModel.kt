package com.example.breakingbad_codetest.viewmodel

import androidx.lifecycle.*
import com.example.breakingbad_codetest.database.DatabaseCharacter
import com.example.breakingbad_codetest.repository.CharactersRepository
import com.example.breakingbad_codetest.util.EspressoIdlingResource
import com.example.breakingbad_codetest.util.OpenForTesting
import com.example.breakingbad_codetest.util.networkutils.LoadingState

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

@OpenForTesting
 class CharactersListViewModel (private val charactersRepository: CharactersRepository) : ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    /**
     * This is the job for all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     */
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshDataFromRepository()
    }


    /**
     * Refresh data from the repository. Use a coroutine launch to run in a background thread.
     */
     fun refreshDataFromRepository() {
        viewModelScope.launch {

            try {
                _loadingState.value = LoadingState.LOADING
                charactersRepository.refreshCharacters()
                _loadingState.value = LoadingState.LOADED

            } catch (networkError: Exception) {
                _loadingState.value = LoadingState.error(networkError.message)
            }
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    //for display data in second fragment
    // LiveData to handle navigation to the selected Character
    private val _navigateToSelectedProperty = MutableLiveData<DatabaseCharacter>()
    val navigateToSelectedProperty: LiveData<DatabaseCharacter>
        get() = _navigateToSelectedProperty

    /**
     * When the character is clicked, set the [_navigateToSelectedProperty] [MutableLiveData]
     * @param characterProperty The [Character] that was clicked on.
     */

    fun displayPropertyDetails(characterProperty: DatabaseCharacter) {
        _navigateToSelectedProperty.value = characterProperty
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


    /**
     * For Search feature based on entered text
     */
    lateinit var allItemsSearch: LiveData<List<DatabaseCharacter>>
    var search = MutableLiveData<String>("%")

    init {
        allItemsSearch = Transformations.switchMap(search) { search ->
            charactersRepository.getItemsSearch(search)

        }
    }

    // set the filter for allItemsFiltered
    fun setSearch(newSearch: String) {
        // optional: add wildcards to the filter
        val f = when {
            newSearch.isEmpty() -> "%"
            else -> "%$newSearch%"
        }
        search.postValue(f)
    }

    /**
     * For Filter Feature based on Appearance
     */
    lateinit var allItemsFiltered: LiveData<List<DatabaseCharacter>>
    var filter = MutableLiveData<String>("%")

    init {
        allItemsFiltered = Transformations.switchMap(filter) { filter ->
            charactersRepository.getItemsByAppearance(filter)
        }
    }

    // set the filter for allItemsFiltered
    fun setFilter(newFilter: Int) {
        val f:String = when{
            newFilter == null -> "%"
            else -> "%$newFilter%"
        }
        filter.postValue(f)
    }

}