package com.example.cyberwallet

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        SetUpTabBar()

        //Tab View


        // Fragment Change with buttons

//        val frag1 = LogIn()
//        val frag2 = Register()
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.fragHolder, frag1)
//            commit()
//        }
//
//        // Buttons to Switch Between Log IN And Register Screen
//        val login = findViewById<Button>(R.id.login)
//        val register = findViewById<Button>(R.id.Register)
//
//
//        // Switch to Log in page
//        login.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragHolder, frag1)
//                commit()
//            }
//        }
//
//        // Switch to register page
//        register.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragHolder, frag2)
//                commit()
//            }
//        }

        //Save to Database

//        val db = UserDatabase.getDatabase(applicationContext)
//        val userDao = db.userDao()
//
//       // Saving data to the database
//        lifecycleScope.launch {
//            userDao.insert(User(name = "tim", password = "987654321"))
//            userDao.insert(User(name = "paul", password = "2468"))
//            userDao.insert(User(name = "ann", password = "723454"))
//            userDao.insert(User(name = "moss", password = "887654"))
//
//        }

    }
    private fun SetUpTabBar(){
        val tablayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager2)

        val  adapterr = LogInViewAdapter(this, tablayout.tabCount)
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