package com.example.cyberwallet

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val allExpenses: Flow<List<Caregories>> = userDao.getAllExpense()

    val allIncome: Flow<List<Caregories>> = userDao.getAllIncome()
//
//    val allIncome: Flow<List<ExpensesTable>> = userDao.getAllExpense()
//
//
//    val insertIncome: Flow<List<ExpensesTable>> = userDao.getAllExpense()
//
//    val allCategories: Flow<List<ExpensesTable>> = userDao.getAllExpense()
//
//    val insertCategory: Flow<List<ExpensesTable>> = userDao.getAllExpense()

    suspend fun insertExpense(ExpensesTable: ExpensesTable){
        userDao.insertExpense(ExpensesTable)
    }


}
