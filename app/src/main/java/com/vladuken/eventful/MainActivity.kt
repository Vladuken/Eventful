package com.vladuken.eventful

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vladuken.features.events.presentation.events.favorite.FavoriteEventListFragment
import com.vladuken.features.events.presentation.events.recent.RecentEventListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnw = findViewById<BottomNavigationView>(R.id.bnwMain)

        if (savedInstanceState == null) {
            bnw.selectedItemId = R.id.menu_recent
            changeFragment(RecentEventListFragment())
        }

        bnw.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_recent -> {
                    changeFragment(RecentEventListFragment())
                    true
                }
                R.id.menu_favorite -> {
                    changeFragment(FavoriteEventListFragment())
                    true
                }
                else -> false
            }
        }

    }

    private fun changeFragment(fragment: Fragment) {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container, fragment)
            commit()
        }
    }

}