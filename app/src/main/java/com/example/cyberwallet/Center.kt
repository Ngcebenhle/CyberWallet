package com.example.cyberwallet

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.github.mikephil.charting.data.Entry
import com.google.android.material.tabs.TabLayout
import kotlin.getValue

class Center : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_center)
        SetUpTabBar()



        val db = UserDatabase.getDatabase(applicationContext)
        val userDao = db.userDao()
        val retrofitApi = RetrofitInstance.RetrofitIN.api


        val repository by lazy { UserRepository(db.userDao(), api = retrofitApi) }
        val viewModel: UserViewModel by viewModels{
            MyViewModelFactory(repository)
        }

        viewModel.getAllRecords.observe(this){
                rec ->
            Log.d("recs_in_center","${rec}")

//            rec.forEach { i->
//                entries.add(Entry(i.startmonth.toFloat(),i.amount.toFloat()))
////                entries.add(Entry(0f,3f))
////                entries.add(Entry(3f,5f))
////                entries.add(Entry(5f,10f))
////                entries.add(Entry(9f,15f))
//            }


        }



        val id = intent.getStringExtra("USER_ID")
        Log.d("userId_in_center_Activity", "${id}")


        if(id != null){
            viewModel.setUser(id.toString().toInt())
        }


    }


    private fun SetUpTabBar(){
        val tablayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager2)

        val  adapterr = CenterViewAdapter(this, tablayout.tabCount)
        viewPager.adapter = adapterr

        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {

                tablayout.selectTab(tablayout.getTabAt(position))

            }
        })
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

        })

    }


}