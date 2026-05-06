package com.example.cyberwallet

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = UserDatabase.getDatabase(applicationContext)
        val userDao = db.userDao()

       // Saving data to the database
        lifecycleScope.launch {
            userDao.insert(User(name = "tim", password = "987654321"))
            userDao.insert(User(name = "paul", password = "2468"))
            userDao.insert(User(name = "ann", password = "723454"))
            userDao.insert(User(name = "moss", password = "887654"))

        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}