package com.example.practicaltask.utils

import android.content.Context
import android.content.SharedPreferences

class Utils {

    companion object {

        val isAdded = "isAdded"
        val AMOUNT = "AMOUNT"
        val TRANSACTION = "TRANSACTION"

        public fun saveBoolean(context: Context, key: String, value: Boolean) {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("ACCOUNT", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(key, value)
            editor.apply()
            editor.commit()
        }

        fun getBoolean(context: Context, key: String): Boolean? {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("ACCOUNT", Context.MODE_PRIVATE)
            val value: Boolean? = sharedPreferences.getBoolean(key, false);
            return value
        }
    }

}