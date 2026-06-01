package com.example.cyberwallet

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class IncomeTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userID: Int,
    val categoryName: String,
    val startDate: Date,
    val endDate: Date,
    val description: String,
    val payer: String,
    val account: Int,
    val recurring: Boolean?
)
