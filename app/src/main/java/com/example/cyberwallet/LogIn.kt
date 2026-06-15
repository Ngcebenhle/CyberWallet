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
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.getValue


class LogIn : Fragment() {

//    val login = findViewById<Button>(R.id.login)

    var UserId: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_log_in, container, false)

        val TAG = "login"

        val db = UserDatabase.getDatabase(requireContext().applicationContext)
        val userDao = db.userDao()

        val repository by lazy { UserRepository(db.userDao()) }
        val viewModel: UserViewModel by activityViewModels{
            MyViewModelFactory(repository)
        }

        val login: Button = view.findViewById(R.id.login)
        val error: TextView = view.findViewById(R.id.errorLogIn)
        val email: EditText = view.findViewById(R.id.email)
        val password : EditText = view.findViewById(R.id.password)



        login.setOnClickListener {


            // Handles Exceptions try catch block.

            try {
                // Code that might cause an error

                //Validation Here for Logging In


                if (email.text.toString() != "" && password.text.toString() != "") {
//                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                    Log.d("work tryout ","All Fields Filled")

//                Retrive from Database

                    lifecycleScope.launch {

                        val user = userDao.logIn(Email = email.text.toString(),
                            Password = password.text.toString() )

                        Log.d("user Id ","${user}")

                        if (user > 0){

                            Log.d("UserId", "${user}")
                            viewModel.setUser(user)

                            viewModel.data.observe(viewLifecycleOwner) { message ->
                                // Handle your data here
                                println(message)

                                UserId = message
                                Log.d("UserId_in_login", "${message}")
                            }

                            //Switch To a different Activity.
                            val intent = Intent(requireContext(), Center::class.java).apply {
                                putExtra("USER_ID", "${user}")
                            }
                            startActivity(intent)

                        } else{

                            Log.d("work tryout ","No User Found")

                            //Set red text to visible
                            error.visibility = View.VISIBLE
                        }

                    }
                } else {
                      error.text = "Please Fill in all details"
                      error.visibility = View.VISIBLE
                }

            } catch (e: Exception) {
                // Code to run if an error happens

            }

        }

//        login.setOnClickListener {
//            val intent = Intent(requireContext(), Center::class.java)
//            startActivity(intent)
//
//        }

  return  view
    }


}