package com.example.breakingbad_codetest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.breakingbad_codetest.database.DatabaseCharacter
import com.example.breakingbad_codetest.network.service.BreakingBad_APIServices
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CharactersRepositoryTest : DatabaseTest() {

    private lateinit var mRepo: CharactersRepository

    @Mock
    lateinit var breakingbadApiservices: BreakingBad_APIServices


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    override fun init() {
        super.init()
        MockitoAnnotations.initMocks(this);

        mRepo = CharactersRepository(
            breakingbadApiservices,
            database
        )
    }

    @Test
    fun test_character_repo_retrieves_expected_data() =
        runBlocking {
            val fakeToReturn =
                CompletableDeferred(FakeObjectsUtils.listDBDatabaseCharacter)

            Mockito.`when`(breakingbadApiservices.getCharacterList())
                .thenReturn(fakeToReturn)

            val dataReceived = mRepo.refreshCharacters()

            assertNotNull(dataReceived)
        }

    @Test
    fun test_getItemsSearch_retrieves_expected_data() = runBlocking {
        //prep
        val fakeObject = FakeObjectsUtils.listDBDatabaseCharacter
        database.characterDao.insertAll(fakeObject)
        val liveDataUtils = LiveDataTestUtil<List<DatabaseCharacter>>()

        //action
        val dataReceived = mRepo.getItemsSearch(fakeObject[0].name)
        val dataReturned = liveDataUtils.getValue(dataReceived)?.get(0)

        //verify
        assertNotNull(dataReceived)
        assertNotNull(dataReturned)

        assertEquals(fakeObject?.get(0)?.name, dataReturned?.name)
        assertEquals(fakeObject?.get(0)?.char_id, dataReturned?.char_id)
        assertEquals(fakeObject?.get(0)?.appearance, dataReturned?.appearance)
        assertEquals(fakeObject?.get(0)?.nickname, dataReturned?.nickname)
        assertEquals(fakeObject?.get(0)?.img, dataReturned?.img)
        assertEquals(fakeObject?.get(0)?.occupation, dataReturned?.occupation)
        assertEquals(fakeObject?.get(0)?.status, dataReturned?.status)

    }

    @Test
    fun test_list_is_empty_when_filter__not_found_anything() {
        //prep
        val fakeObject = FakeObjectsUtils.listDBDatabaseCharacter
        val liveDataUtils = LiveDataTestUtil<List<DatabaseCharacter>>()

        //action
        val dataReceived = mRepo.getItemsSearch(fakeObject[0].name)
        val dataReturned = liveDataUtils.getValue(dataReceived)

        //verify
        assertNotNull(dataReceived)
        assertNotNull(dataReturned)
        assertEquals(0, dataReturned?.size)

    }

    @Test
    fun test_getItemsByAppearance_return_expected_value() {
        //prep
        val fakeObject = FakeObjectsUtils.listDBDatabaseCharacter
        database.characterDao.insertAll(fakeObject)
        val liveDataUtils = LiveDataTestUtil<List<DatabaseCharacter>>()
        val appearance = "%1%"
        //action
        val dataReceived = mRepo.getItemsByAppearance(appearance)
        val dataReturned = liveDataUtils.getValue(dataReceived)?.get(0)

        //verify
        assertNotNull(dataReceived)
        assertNotNull(dataReturned)

        assertEquals(fakeObject?.get(0)?.name, dataReturned?.name)
        assertEquals(fakeObject?.get(0)?.char_id, dataReturned?.char_id)
        assertEquals(fakeObject?.get(0)?.appearance, dataReturned?.appearance)
        assertEquals(fakeObject?.get(0)?.nickname, dataReturned?.nickname)
        assertEquals(fakeObject?.get(0)?.img, dataReturned?.img)
        assertEquals(fakeObject?.get(0)?.occupation, dataReturned?.occupation)
        assertEquals(fakeObject?.get(0)?.status, dataReturned?.status)
    }
}