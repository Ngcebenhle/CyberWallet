package com.example.cyberwallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Planner : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_planner, container, false)


        // getting the recyclerview by its id
        val recyclerview: RecyclerView = view.findViewById(R.id.PllannerRecyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<events>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(events( "Item $i"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = PlannerAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter


        return view


    }


}