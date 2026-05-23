package com.example.cyberwallet

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity
data class ExpensesTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userID: Int,
    val categoryName: String,
    val startDate: String,
    val endDate: String,
    val description: String,
    val payer: String,
    val account: Int,
    val recurring: Boolean?

)
