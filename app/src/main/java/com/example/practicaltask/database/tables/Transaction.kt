package com.example.practicaltask.database.tables

import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Transactions")
data class Transaction(

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

    @ColumnInfo(name = "totalAmount")
    val totalAmount : Int,

    @ColumnInfo(name = "totalNotes2000")
    val totalNotes2000 : Int,

    @ColumnInfo(name = "totalNotes500")
    val totalNotes500 : Int,

    @ColumnInfo(name = "totalNotes200")
    val totalNotes200 : Int,

    @ColumnInfo(name = "totalNotes100")
    val totalNotes100 : Int,

    @ColumnInfo(name = "type")
    val type : String

)