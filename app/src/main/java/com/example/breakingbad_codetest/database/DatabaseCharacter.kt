package com.example.breakingbad_codetest.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DatabaseCharacter(
    @PrimaryKey
    var char_id: Int,
    var name: String,
    var nickname: String,
    var img: String,
    var status: String,
    var occupation: List<String>,
    var appearance: List<String>

): Parcelable {}




