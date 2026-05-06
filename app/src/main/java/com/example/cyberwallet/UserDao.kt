package com.example.cyberwallet

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): Flow<List<User>>
}