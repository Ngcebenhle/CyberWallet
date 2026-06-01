package com.example.cyberwallet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StatementsTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val balance: String,
    val IncomeStatement: String,
    val CashFlow: String,
    val ChangeInEquity: String
)
