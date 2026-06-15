package com.example.cyberwallet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cyberwallet.UserDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

class Expenses : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_expenses, container, false)

        val db = UserDatabase.getDatabase(requireContext().applicationContext)
        val userDao = db.userDao()

         val repository by lazy { UserRepository(db.userDao()) }
                val viewModel: UserViewModel by activityViewModels{
                    MyViewModelFactory(repository)
                }

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

       viewModel.allExpenses.observe(viewLifecycleOwner) { users ->
            // Update your UI components, like a RecyclerView adapter

           for (i in users)  {

               Log.d("work Expese ", "${i.CategoryName}")


               data.add(Item(R.drawable.add,
                   "Item","${i.CategoryName}"))

           }

//            users.forEach { i ->
//                Log.d("work please ", "${i}")
//
//                data.add(Item(R.drawable.add,
//                        "Item","${i.categoryName}"))
//            }
//            adapter.submitList(users)
        }




        viewModel.data.observe(viewLifecycleOwner) { message ->
            // Handle your data here
            println(message)
            Log.d("UserId_in_stat", "${message}")

        }
        Log.d("UserId_out_stat", "its a dud")

//        for (i in 1..1) {
//            data.add(Item(R.drawable.add,
//                "Item", "New------")
//            )
//        }

        // This will pass the ArrayList to our Adapter
        val adapter = Adapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

//        val newRecordButton: View = view.findViewById(R.id.newRecord)
//        newRecordButton.setOnClickListener {
//
//
//            val intent = Intent(requireContext(), newRecord::class.java)
//            startActivity(intent)
//        }

        return  view
    }


}

//        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

//        lifecycleScope.launch {
//            val info: Flow<List<ExpensesTable>> = userDao.getAllExpense()
//
//
//            info.collect { i ->
//
//                Log.d("work please ", "${i[1].categoryName}")
//
//                i.forEach { v ->
//
//                    Log.d("work please ", "${v}")
//                    data.add(Item(R.drawable.add,
//                        "Item $i",
//                        target = "2000",
//                        amountSpent = "300",
//                        catName = "${v.categoryName}"))
//
//                }
//
//
//            }
//
//        }
