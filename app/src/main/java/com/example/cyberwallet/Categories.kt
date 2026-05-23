package com.example.cyberwallet

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categories)
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