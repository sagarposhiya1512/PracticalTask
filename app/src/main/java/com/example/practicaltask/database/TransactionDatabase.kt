package com.example.practicaltask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicaltask.database.daos.TransactionDao
import com.example.practicaltask.database.tables.Account
import com.example.practicaltask.database.tables.Transaction

@Database(entities = [Transaction::class,Account::class], version = 1)
abstract class TransactionDatabase : RoomDatabase() {

    abstract val transactionDao: TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: TransactionDatabase? = null
        fun getInstance(context: Context): TransactionDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TransactionDatabase::class.java,
                        "Transactions"
                    ).allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }
    }

}