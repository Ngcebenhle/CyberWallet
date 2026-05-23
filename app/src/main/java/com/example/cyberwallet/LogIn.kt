package com.example.cyberwallet

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class LogIn : Fragment() {

//    val login = findViewById<Button>(R.id.login)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_log_in, container, false)

        val TAG = "login"

        val db = UserDatabase.getDatabase(requireContext().applicationContext)
        val userDao = db.userDao()

        val login: Button = view.findViewById(R.id.login)
//
        login.setOnClickListener {

//            &&

            //            Handles Exceptions try catch block.

            try {
                // Code that might cause an error
            } catch (e: Exception) {
                // Code to run if an error happens
            } finally {
                // (Optional) Code that runs no matter what
            }

             //Validation Here for Logging In

            val email: EditText = view.findViewById(R.id.email)
            val password : EditText = view.findViewById(R.id.password)

//                .text.toString()

            if (email.text.toString() != "" && password.text.toString() != "") {
//                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()


//                Retrive from Database

                lifecycleScope.launch {
                         val user = userDao.logIn(Email = email.text.toString(),
                            Password = password.text.toString() )

                    if (user > 0){

                        //Switch To a different Activity.
                        val intent = Intent(requireContext(), Center::class.java)
                        startActivity(intent)

                    } else{ }

                    }

               } else {
//                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
               }

        }


  return  view
    }


}