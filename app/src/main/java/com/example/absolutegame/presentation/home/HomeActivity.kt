package com.example.absolutegame.presentation.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.absolutegame.Application
import com.example.absolutegame.R
import com.example.absolutegame.adapter.GameAdapter
import com.example.absolutegame.databinding.ActivityHomeBinding
import com.example.absolutegame.di.ViewModelFactory
import com.example.absolutegame.domain.Game
import com.example.absolutegame.presentation.fragment.HomeFragment
import com.example.absolutegame.presentation.fragment.ProfileFragment
import com.example.absolutegame.presentation.profile.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    private var bottomNav: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()

        bottomNav = findViewById(R.id.bottom_nav)

        bottomNav?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
                    true
                }
                R.id.itemProfile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProfileFragment()).commit()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}