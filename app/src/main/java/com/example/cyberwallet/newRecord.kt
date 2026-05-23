package com.example.cyberwallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class newRecord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_record)

        val db = UserDatabase.getDatabase(applicationContext)
        val userDao = db.userDao()

        val amount : EditText = findViewById(R.id.amount)
        val description : EditText = findViewById(R.id.Description)
        val startDate : EditText = findViewById(R.id.startDate)
        val endDate : EditText = findViewById(R.id.endDate)
        val reciptImage : ImageView = findViewById(R.id.reciptImage)
        val addImageIcon : ImageView = findViewById(R.id.addImageIcon)

        val saveButton : Button = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {

            lifecycleScope.launch {
                userDao.insertExpense(ExpensesTable(userID = 1,
                    categoryName = "Transport",
                    startDate = "30/02/2020",
                    endDate = "20/03/2020",
                    description = "This is a test",
                    payer = "mom",
                    account = 2233837,
                    recurring = true))

            }


            //Switch Pages
        }


    }
}