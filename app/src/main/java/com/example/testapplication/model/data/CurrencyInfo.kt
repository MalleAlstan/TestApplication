package com.example.testapplication.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class CurrencyInfo (
    @PrimaryKey val id: String = "",
    @ColumnInfo (name = "name") val name: String = "",
    @ColumnInfo (name = "symbol") val symbol: String = ""
)