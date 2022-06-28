package com.example.practicaltask.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Accounts")
data class Account (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "amount")
    val amount : Int,

    @ColumnInfo(name = "notes2000")
    val notes2000 : Int,

    @ColumnInfo(name = "notes500")
    val notes500 : Int,

    @ColumnInfo(name = "notes200")
    val notes200 : Int,

    @ColumnInfo(name = "notes100")
    val notes100 : Int,

    @ColumnInfo(name = "type")
    val type : String
)