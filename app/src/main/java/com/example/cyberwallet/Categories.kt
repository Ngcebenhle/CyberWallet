package com.example.cyberwallet

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlin.getValue

class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categories)

        val db = UserDatabase.getDatabase(applicationContext)
        val userDao = db.userDao()

        val repository by lazy { UserRepository(db.userDao()) }
        val viewModel: UserViewModel by viewModels{
            MyViewModelFactory(repository)
        }

        viewModel.allExpenses.observe(this) { users ->
            // Update your UI components, like a RecyclerView adapter

            for (i in users)  {

                Log.d("work tryout ", "${i}")
//                data.add(Item(R.drawable.add,
//                    "Item","${i.categoryName}"))

            }

//            users.forEach { i ->
//                Log.d("work please ", "${i}")
//
//                data.add(Item(R.drawable.add,
//                        "Item","${i.categoryName}"))
//            }
//            adapter.submitList(users)
        }

        viewModel.allIncome.observe(this) { users ->
            // Update your UI components, like a RecyclerView adapter

            for (i in users)  {

                Log.d("work Income ", "${i.CategoryName}")


//                data.add(Item(R.drawable.add,
//                    "Item","${i.CategoryName}"))

            }

//            users.forEach { i ->
//                Log.d("work please ", "${i}")
//
//                data.add(Item(R.drawable.add,
//                        "Item","${i.categoryName}"))
//            }
//            adapter.submitList(users)
        }

        SetUpTabBar()


        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {

            val modal = MyModalSheet()
            modal.show(supportFragmentManager, MyModalSheet.TAG)

        }



    }
    private fun SetUpTabBar(){
        val tablayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager2)

        val  adapterr = CategoriesViewAdapter(this, tablayout.tabCount)
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