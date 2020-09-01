package com.example.breakingbad_codetest.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

//Room does not provide abstracts for easily dealing with non primative types, so serialization to string is needed
class Converters {

//    @TypeConverter
//    fun fromString(value: String): List<String> {
//        val listType = object : TypeToken<List<String>>() {
//        }.type
//        return Gson().fromJson(value, listType)
//    }
//    @TypeConverter
//    fun fromArrayList(list: List<String>): String {
//        val gson = Gson()
//        return gson.toJson(list)
//    }

    companion object {
        var gson = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToStringList(data: String?): List<String> {
            return data?.run {
                val listType: Type =
                    object : TypeToken<List<String?>?>() {}.type
                gson.fromJson<List<String>>(data, listType)
            } ?: listOf()
        }

        @TypeConverter
        @JvmStatic
        fun stringListToString(stringList: List<String?>?): String = gson.toJson(stringList)
    }

}