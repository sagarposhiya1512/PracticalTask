package com.example.practicaltask.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltask.R
import com.example.practicaltask.database.tables.Transaction
import com.example.practicaltask.databinding.ItemTransactionBinding

class TransactionsAdapter (val context : Context) : RecyclerView.Adapter<MyViewHolder>() {
    private val transactionsList = ArrayList<Transaction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemTransactionBinding =
            ItemTransactionBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == 0){

            holder.binding.apply {
                holder.binding.txtAmount.text = "ATM Amount"
                holder.binding.txtNotes100.text = "Rs.100"
                holder.binding.txtNotes200.text ="Rs.200"
                holder.binding.txtNotes500.text = "Rs.500"
                holder.binding.txtNotes2000.text = "Rs.2000"

                holder.binding.txtAmount.setTextColor(context.getColor(R.color.black))
                holder.binding.txtNotes100.setTextColor(context.getColor(R.color.black))
                holder.binding.txtNotes200.setTextColor(context.getColor(R.color.black))
                holder.binding.txtNotes500.setTextColor(context.getColor(R.color.black))
                holder.binding.txtNotes2000.setTextColor(context.getColor(R.color.black))

                holder.binding.txtAmount.setTypeface(Typeface.DEFAULT_BOLD);
                holder.binding.txtNotes100.setTypeface(Typeface.DEFAULT_BOLD);
                holder.binding.txtNotes200.setTypeface(Typeface.DEFAULT_BOLD);
                holder.binding.txtNotes500.setTypeface(Typeface.DEFAULT_BOLD);
                holder.binding.txtNotes2000.setTypeface(Typeface.DEFAULT_BOLD);
            }

        } else {
            holder.bind(transactionsList[position - 1])
        }

    }

    fun setList(transactions: List<Transaction>) {
        transactionsList.clear()
        transactionsList.addAll(transactions)

    }
}

class MyViewHolder(val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(transaction: Transaction) {
        binding.txtAmount.text = transaction.amount.toString()
        binding.txtNotes100.text = transaction.notes100.toString()
        binding.txtNotes200.text = transaction.notes200.toString()
        binding.txtNotes500.text = transaction.notes500.toString()
        binding.txtNotes2000.text = transaction.notes2000.toString()

    }
}