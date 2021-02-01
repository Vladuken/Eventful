package com.vladuken.eventful

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vladuken.features.events.presentation.events.favorite.FavoriteEventListFragment
import com.vladuken.features.events.presentation.events.recent.RecentEventListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            changeFragment(FavoriteEventListFragment())
        }

    }

    private fun changeFragment(fragment: Fragment) {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container, fragment)
            commit()
        }
    }

}