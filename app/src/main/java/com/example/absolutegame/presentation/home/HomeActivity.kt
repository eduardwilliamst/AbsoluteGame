package com.example.absolutegame.presentation.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.absolutegame.R
import com.example.absolutegame.presentation.fragment.FavoriteFragment
import com.example.absolutegame.presentation.fragment.GenreFragment
import com.example.absolutegame.presentation.fragment.HomeFragment
import com.example.absolutegame.presentation.fragment.ProfileFragment
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
                R.id.itemGenre -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, GenreFragment()).commit()
                    true
                }
                R.id.itemFav -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FavoriteFragment()).commit()
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