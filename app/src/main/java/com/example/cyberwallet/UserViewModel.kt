package com.example.cyberwallet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class UserViewModel(private val repository: UserRepository) : ViewModel() {

    // Converts Flow to LiveData for easy observation in the Activity

    val userID = MutableLiveData<String>()
    private val UserId = MutableLiveData<Int>()

    val data: LiveData<Int> = UserId
    // Function to add/update data
    fun setUser(newValue: Int) {

        UserId.value = newValue

    }


    fun insertExpense(ExpensesTable: ExpensesTable) {
        viewModelScope.launch {
            repository.insertExpense(ExpensesTable)
        }
    }


        val allExpenses: LiveData<List<Caregories>> = repository.allExpenses.asLiveData()

        val allIncome: LiveData<List<Caregories>> = repository.allIncome.asLiveData()

    val getAllRecords: LiveData<List<ExpensesTable>> = repository.getAllRecords.asLiveData()




//    val allIncome: LiveData<List<ExpensesTable>> = repository.allExpenses.asLiveData()
//
//    val allCategories: LiveData<List<ExpensesTable>> = repository.allExpenses.asLiveData()
//
//    val allStatements: LiveData<List<ExpensesTable>> = repository.allExpenses.asLiveData()
//
//    val LogIn: LiveData<List<ExpensesTable>> = repository.allExpenses.asLiveData()


}
