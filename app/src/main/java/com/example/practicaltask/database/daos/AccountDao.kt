package com.example.practicaltask.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practicaltask.database.tables.Account
import com.example.practicaltask.database.tables.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert
    fun insertAccount(account: Account) : Long

    @Update
    fun updateAccount(account: Account) : Long

    @Query("SELECT * FROM `Accounts`")
    fun getAccount(): Flow<List<Account>>
}