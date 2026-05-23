package com.example.cyberwallet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class Register : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val db = UserDatabase.getDatabase(requireContext().applicationContext)
        val userDao = db.userDao()

        val email: EditText = view.findViewById(R.id.email)
        val username: EditText = view.findViewById(R.id.username)
        val password: EditText = view.findViewById(R.id.password)

//        Register Button
        val register: Button = view.findViewById(R.id.Register)



//        Register Button.
        register.setOnClickListener {

//            Handles Exceptions try catch block.

            try {
                // Code that might cause an error
            } catch (e: Exception) {
                // Code to run if an error happens
            } finally {
                // (Optional) Code that runs no matter what
            }


//        Data Validation And Encryption.

            if (email.text.toString() != "" || password.text.toString() != "" || username.text.toString() != ""  ){

//                Then Continue here with registration and move to the next activity.

                // Saving data to the database
                lifecycleScope.launch {
                    userDao.insert(User(email = email.text.toString(),
                        name = username.text.toString(),
                        password = password.text.toString()))

                }

//            Switch Activities
                val intent = Intent(requireContext(), Center::class.java)
                startActivity(intent)

            }else{

            }



        }







        return view
    }

}