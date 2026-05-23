package com.example.cyberwallet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cyberwallet.UserDatabase
import kotlinx.coroutines.launch

class Expenses : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_expenses, container, false)



        val db = UserDatabase.getDatabase(requireContext().applicationContext)
        val userDao = db.userDao()


        // getting the recyclerview by its id
        val recyclerview: RecyclerView = view.findViewById(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Item>()

        // This loop will create 20 Views containing
        // the image with the count of view
//        for (i in 1..20) {
//            data.add(Item(R.drawable.add, "Item $i",target = "2000", amountSpent = "300"))
//        }
        lifecycleScope.launch {
            val Expens = userDao.getAllExpense()

            Expens.forEach { i ->

                Log.d("work please ", "${i}")
                data.add(Item(R.drawable.add, i.categoryName, target = "2000", amountSpent = "300"))


            }

        }

        // This will pass the ArrayList to our Adapter
        val adapter = Adapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        return  view
    }


}