package com.example.cyberwallet

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity
data class Caregories(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val UserID: Int,
    val CategoryName: String,
    val dateCreated: String?
)
