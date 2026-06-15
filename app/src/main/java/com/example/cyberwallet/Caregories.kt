package com.example.cyberwallet

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date


@Entity
data class Caregories(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val UserID: Int,
    val InorEx: String,
    val CategoryName: String,
    val dateCreated: String,
    val MaxGoalAmount:Int,
    val MinGoalAmount:Int
)
