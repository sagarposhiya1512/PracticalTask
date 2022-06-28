package com.example.practicaltask.repositories

import com.example.practicaltask.database.daos.AccountDao
import com.example.practicaltask.database.daos.TransactionDao
import com.example.practicaltask.database.tables.Account
import com.example.practicaltask.database.tables.Transaction

class AccountRepository(private val dao: AccountDao) {

    val transactions = dao.getAccount()

    suspend fun insert(account: Account): Long {
        return dao.insertAccount(account)
    }
}