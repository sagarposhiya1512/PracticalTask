package com.example.practicaltask.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practicaltask.repositories.TransactionRepository
import java.lang.IllegalArgumentException

class TransactionViewModelFactory
    (
    private val repository: TransactionRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            return TransactionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}