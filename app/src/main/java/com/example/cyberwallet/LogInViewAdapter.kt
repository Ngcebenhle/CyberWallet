package com.example.cyberwallet

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class LogInViewAdapter(activity: FragmentActivity, private val tabCount: Int):
    FragmentStateAdapter(activity){

    override fun createFragment(p: Int): Fragment {
        return when(p){
            0-> LogIn()
            1-> Register()
            else -> LogIn()
        }
    }

    override fun getItemCount(): Int = tabCount
}