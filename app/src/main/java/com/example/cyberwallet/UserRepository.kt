package com.example.cyberwallet

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.util.Date

class UserRepository(private val userDao: UserDao, private val api: ApiServices) {

    val allExpenses: Flow<List<Caregories>> = userDao.getAllExpense()

    val allIncome: Flow<List<Caregories>> = userDao.getAllIncome()

    val getAllRecords: Flow<List<ExpensesTable>> = userDao.getAllRec()


    fun getRecordsByDates(startDate: Date,endDate: Date): Flow<List<ExpensesTable>> {
        return userDao.getAllRecWithDates(startDate,endDate)
    }


//    val insertIncome: Flow<List<ExpensesTable>> = userDao.getAllExpense()
//
//    val allCategories: Flow<List<ExpensesTable>> = userDao.getAllExpense()
//
//    val insertCategory: Flow<List<ExpensesTable>> = userDao.getAllExpense()


    suspend fun insertExpense(ExpensesTable: ExpensesTable){
        userDao.insertExpense(ExpensesTable)
    }

   suspend fun Test(): Response<ResponseData>{
       return RetrofitInstance.RetrofitIN.api.Test()
    }

    suspend fun signUp(request : backendUserNodejs): Response<backendUserNodejsResponse>{
        return RetrofitInstance.RetrofitIN.api.signup(request)
    }





}
