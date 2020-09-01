package com.example.breakingbad_codetest.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.breakingbad_codetest.util.Converters

@Dao
interface CharacterDao {
    @Query("select * from DatabaseCharacter")
        fun getLocalDBCharacters(): LiveData<List<DatabaseCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( characters: List<DatabaseCharacter>)

    // Dao query with filter - For search functionality
    @Query("SELECT * from DatabaseCharacter WHERE name LIKE :filter ")
    fun getItemsSearch(filter: String): LiveData<List<DatabaseCharacter>>

    //Dao query with season - For filter functionality
    @Query("select * from DatabaseCharacter where appearance LIKE :season")
    fun getLocalCharactersByAppearance(season: String) : LiveData<List<DatabaseCharacter>>

}

@Database(entities = [DatabaseCharacter::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharacterDatabase: RoomDatabase() {
    abstract val characterDao: CharacterDao
}
