package com.example.lksmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView

class Navigation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        var bottomNavbar = findViewById<BottomNavigationView>(R.id.bottom_nav);
        val defaulFragment = MenuFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame,defaulFragment).commit()
        bottomNavbar.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.menu -> {
                    val menuFragment = MenuFragment();
                    supportFragmentManager.beginTransaction().replace(R.id.frame,menuFragment).commit()
                    true
                }
                R.id.invoice -> {
                    val invoiceFragment = InvoiceFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame,invoiceFragment).commit()
                    true
                }
                R.id.profile -> {
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frame, profileFragment).commit()
                    true
                }
                else -> false
        }
        }
    }
}