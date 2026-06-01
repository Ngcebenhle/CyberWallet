package com.example.cyberwallet

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(Caregories: Caregories)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIncome(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpense(ExpensesTable: ExpensesTable)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): Flow<List<User>>


    @Query("SELECT * FROM Caregories WHERE InorEx = :income ")
    fun getAllIncome(income: String = "Income"): Flow<List<Caregories>>


    @Query("SELECT * FROM Caregories WHERE InorEx = :Expense ")
   fun getAllExpense(Expense: String = "Expense"): Flow<List<Caregories>>


    @Query("SELECT id FROM user_table WHERE email =:Email AND password = :Password")
    suspend fun logIn(Email : String, Password: String) : Int

}