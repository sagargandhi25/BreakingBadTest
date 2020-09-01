package com.example.breakingbad_codetest.util

import com.example.breakingbad_codetest.database.DatabaseCharacter

object FakeObjectsUtils{
val DBDatabaseCharacter = DatabaseCharacter(char_id = 1,  name = "Skyler White" , nickname = "Sky" , img = "https://www.globalcosmeticsnews.com/wp-content/uploads/2020/04/Google.png", status = "visible", occupation = listOf()
, appearance = listOf())

val listDBDatabaseCharacter = listOf(DBDatabaseCharacter)
}