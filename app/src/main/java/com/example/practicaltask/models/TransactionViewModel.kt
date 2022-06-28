package com.example.practicaltask.models

import android.util.Log
import androidx.lifecycle.*
import com.example.practicaltask.database.tables.Transaction
import com.example.practicaltask.repositories.TransactionRepository
import com.example.practicaltask.utils.Event
import com.example.practicaltask.utils.Utils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {

    val withdrawAmount = MutableLiveData<Int>()
    var transactionMutableLiveData: MutableLiveData<Transaction> = MutableLiveData<Transaction>()
    var lastTransactionMutableLiveData: MutableLiveData<Transaction> = MutableLiveData<Transaction>()
    lateinit var transaction : Transaction

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    fun afterUserNameChange(s: CharSequence) {
        if (s.length > 0) {
            withdrawAmount.value = s.toString().toInt()
        }
    }

    fun withdrawMoney() {
        var isCorrect : Boolean = false
        if (withdrawAmount.value!! % 100 == 0){
            isCorrect = true
        }
        if (withdrawAmount.value == null || withdrawAmount.value == 0) {
            statusMessage.value = Event("Please enter Valid Amount")
        } else if (!isCorrect){
            statusMessage.value = Event("Please enter Amount in multiplication of 100")
        } else {
            if (withdrawAmount.value!! > transaction.totalAmount){
                statusMessage.value = Event("Insufficient Funds")
            } else {
                val amount = withdrawAmount.value!!
                statusMessage.value = Event("Amount withdraw Successfully ${withdrawAmount.value}")
                noteCounting2()
            }
        }
    }

     fun insertAmount(transaction: Transaction) = viewModelScope.launch {
        val newRowId = repository.insert(transaction)
        if (newRowId > -1) {
            statusMessage.value = Event("Amount inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    private fun insertTransaction(transaction: Transaction) = viewModelScope.launch {
        val newRowId = repository.insert(transaction)
        if (newRowId > -1) {
            statusMessage.value = Event("Amount withdraw Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
        getTransactions()
        getLatsTransaction()
    }

    fun getTransactions() = liveData<List<Transaction>> {
        repository.transactions.collect {
            emit(it)
        }
    }

    fun getAmount() = liveData<Transaction> {
        repository.amount.collect {
            transactionMutableLiveData.value = it
            transaction = it
            emit(it)
            Log.e("Data","${it.type}  Amount ${it.totalAmount}")
        }
    }

//    fun getLatsTransaction(id : Int) = viewModelScope.launch{
//        val last = repository.lastTransaction(id)
//        lastTransactionMutableLiveData = last
//    }

    fun getLatsTransaction() = liveData<Transaction> {
       repository.lastTransaction.collect {
           lastTransactionMutableLiveData.value = it
           emit(it)
       }
    }

     fun updateTransaction(transaction: Transaction){
         repository.updateAmount(transaction)
         getAmount()
    }

    fun noteCounting2(){
        val notes = intArrayOf(2000, 500, 200, 100)
        val lefts = intArrayOf(transaction.totalNotes2000, transaction.totalNotes500, transaction.totalNotes200, transaction.totalNotes100)
        val count = intArrayOf(0, 0, 0, 0)
        var totalCorpus: Int = 0

        for (i in 0 until notes.size) {
            totalCorpus = totalCorpus + notes.get(i) * lefts.get(i)
        }

        var amount = withdrawAmount.value
        if (amount!! <= totalCorpus) {
            for (i in 0 until notes.size) {
                if (notes[i] <= amount!!) {
                    val noteCount: Int = amount / notes.get(i)
                    if (lefts.get(i) > 0) {
                        count[i] = if (noteCount >= lefts.get(i)) lefts.get(i) else noteCount
                        lefts[i] =
                            if (noteCount >= lefts.get(i)) 0 else lefts.get(i) - noteCount
                        totalCorpus = totalCorpus - count.get(i) * notes.get(i)
                        amount = amount - count.get(i) * notes.get(i)
                    }
                }
            }

            var note2000 : Int = 0
            var note500 : Int = 0
            var note200 : Int = 0
            var note100 : Int = 0

            for (i in 0 until count.size) {
                if (count[i] !== 0) {
                    Log.e("NOTES","No of ${notes[i].toString()} + \" * \" + ${count[i]} + \" = \" + ${notes[i] * count[i]}" )
                    if (notes[i] == 2000){
                        note2000 = count[i]
                    }

                    if (notes[i] == 500){
                        note500 = count[i]
                    }

                    if (notes[i] == 200){
                        note200 = count[i]
                    }

                    if (notes[i] == 100){
                        note100 = count[i]
                    }
                }
            }

            var count = repository.getCount()
            count = count + 1

            insertTransaction(Transaction(count,withdrawAmount.value!!,note2000,note500,note200,note100,0,0,0,0,0,Utils.TRANSACTION))

            var left2000 : Int = 0
            var left500 : Int = 0
            var left200 : Int = 0
            var left100 : Int = 0

            for (i in 0 until notes.size) {
                Log.e("NOTES","Left  ${notes[i].toString()} + \" left are \" + ${lefts[i]} ")

                if (notes[i] == 2000){
                    left2000 = lefts[i]
                }

                if (notes[i] == 500){
                    left500 = lefts[i]
                }

                if (notes[i] == 200){
                    left200 = lefts[i]
                }

                if (notes[i] == 100){
                    left100 = lefts[i]
                }
            }
            val leftAmonut = transaction.totalAmount - withdrawAmount.value!!
            updateTransaction(Transaction(1,0,0,0,0,0,leftAmonut,left2000,left500,left200,left100,Utils.AMOUNT))
            getAmount()
            getLatsTransaction()
        } else {
            println("Unable to dispense cash at this moment for this big amount")
        }
    }



}