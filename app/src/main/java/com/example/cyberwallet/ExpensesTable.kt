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
    val amount: Int,
    val categoryName: String,
    val startDate: Date,
    val endDate: Date,
    val description: String,
    val payer: String,
    val startmonth: Int,
    val startyear: Int,
    val startday: Int,
    val endmonth: Int,
    val endyear: Int,
    val endday: Int,
    val account: Int,
    val image: String,
    val recurring: Boolean?

)
