package com.example.cyberwallet

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class CenterViewAdapter(activity: FragmentActivity, private val tabCount: Int):
    FragmentStateAdapter(activity){

    override fun createFragment(p: Int): Fragment {
        return when(p){
            0-> Planner()
            1-> Dashboard()
            2-> Profile()
            else -> Dashboard()
        }

    }

    override fun getItemCount(): Int = tabCount
}