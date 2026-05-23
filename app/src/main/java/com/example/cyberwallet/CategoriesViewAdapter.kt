package com.example.cyberwallet

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter



class CategoriesViewAdapter(activity: FragmentActivity, private val tabCount: Int):
    FragmentStateAdapter(activity){

    override fun createFragment(p: Int): Fragment {
        return when(p){
            0-> Statements()
            1-> Expenses()
            2-> Income()
            else -> Income()
        }
    }

    override fun getItemCount(): Int = tabCount
}