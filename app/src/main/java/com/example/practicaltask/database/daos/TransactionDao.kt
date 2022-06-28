package com.example.practicaltask.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practicaltask.database.tables.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert
     fun insertTransaction(transaction: Transaction) : Long

    @Query("SELECT * FROM `Transactions` WHERE NOT (type = 'AMOUNT')") //
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM 'Transactions' WHERE type = :type")
    fun getAmount(type : String) : Flow<Transaction>

    @Query("UPDATE transactions SET totalAmount=:totalAmount WHERE id = :id")
    fun update(totalAmount: Int, id: Int)

    @Update
    fun update(transaction: Transaction)

    @Query("SELECT COUNT(id) FROM transactions")
    fun getCount(): Int

    @Query("SELECT * FROM Transactions WHERE NOT (type = 'AMOUNT') ORDER BY id DESC LIMIT 1")
    fun getLastTransaction() : Flow<Transaction>

    @Query("SELECT * FROM Transactions WHERE id = :id")
    fun getLastTransaction(id : Int) : Flow<Transaction>
}