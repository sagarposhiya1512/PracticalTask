package com.example.practicaltask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaltask.R
import com.example.practicaltask.adapters.TransactionsAdapter
import com.example.practicaltask.database.TransactionDatabase
import com.example.practicaltask.database.tables.Transaction
import com.example.practicaltask.databinding.ActivityMainBinding
import com.example.practicaltask.models.TransactionViewModel
import com.example.practicaltask.models.TransactionViewModelFactory
import com.example.practicaltask.repositories.TransactionRepository
import com.example.practicaltask.utils.Utils

class ATMActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var transactionsAdapter: TransactionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = TransactionDatabase.getInstance(application).transactionDao
        val repository = TransactionRepository(dao)
        val factory = TransactionViewModelFactory(repository)
        transactionViewModel = ViewModelProvider(this, factory).get(TransactionViewModel::class.java)
        binding.myViewModel = transactionViewModel
        binding.lifecycleOwner = this

        transactionViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        initRecyclerView()
        val isAdded : Boolean? = Utils.getBoolean(this,Utils.isAdded)
        if (isAdded == false){
            transactionViewModel.insertAmount(Transaction(0,0,0,0,0,0,75000,20,30,65,70,Utils.AMOUNT))
        }
        getAmount()
        getLastTransaction()
    }

    private fun initRecyclerView() {
        binding.rvTransactions.layoutManager = LinearLayoutManager(this)
        transactionsAdapter = TransactionsAdapter(this)
        binding.rvTransactions.adapter = transactionsAdapter
        displayTransactionList()
    }

    private fun displayTransactionList() {
        transactionViewModel.getTransactions().observe(this, Observer {
            transactionsAdapter.setList(it)
            transactionsAdapter.notifyDataSetChanged()
        })
    }

    private fun getAmount(){
        transactionViewModel.getAmount().observe(this,{
            binding.txtAmount.text = it.totalAmount.toString()
            binding.txtNotes100.text = it.totalNotes100.toString()
            binding.txtNotes200.text = it.totalNotes200.toString()
            binding.txtNotes500.text = it.totalNotes500.toString()
            binding.txtNotes2000.text = it.totalNotes2000.toString()
            Utils.saveBoolean(this,Utils.isAdded,true)
            Log.e("Transaction", it.toString())
        })
    }

    private fun getLastTransaction(){
        transactionViewModel.getLatsTransaction().observe(this,{
            if (it != null) {
                binding.txtLastAmount.text = it.amount.toString()
                binding.txtLastNotes100.text = it.notes100.toString()
                binding.txtLastNotes200.text = it.notes200.toString()
                binding.txtLastNotes500.text = it.notes500.toString()
                binding.txtLastNotes2000.text = it.notes2000.toString()
                Log.e("LASTTransaction", it.toString())
            }
        })
    }

}