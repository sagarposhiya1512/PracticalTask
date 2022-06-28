package com.example.practicaltask.repositories

import com.example.practicaltask.database.daos.TransactionDao
import com.example.practicaltask.database.tables.Transaction
import com.example.practicaltask.utils.Utils

class TransactionRepository(private val dao: TransactionDao) {

    val transactions = dao.getAllTransactions()

    val amount = dao.getAmount(Utils.AMOUNT)

    val lastTransaction = dao.getLastTransaction()

    fun lastTransaction1() {
        dao.getLastTransaction(getCount())
    }

     fun insert(transaction: Transaction): Long {
        return dao.insertTransaction(transaction)
    }

     fun updateAmount(totalAmount: Int,total100 : Int,total200 : Int,total500 : Int,total2000 : Int) {
         dao.update(totalAmount,1)
    }
    fun updateAmount(transaction: Transaction) {
        dao.update(transaction)
    }

    fun getCount() : Int{
        return dao.getCount()
    }
}