package com.example.cahtapplication.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cahtapplication.R
import com.example.cahtapplication.base.BaseActivity
import com.example.cahtapplication.databinding.ActivityHomeBinding
import com.example.cahtapplication.ui.auth.AuthActivity
import com.example.cahtapplication.ui.create_rood.RoomCreationActivity
import com.example.cahtapplication.ui.home.adapter.RoomsViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRoomsViewPager()
        navigationToRegisterFragment()
    }

    private fun initRoomsViewPager() {
        val adapter = RoomsViewPagerAdapter(this)
        binding.roomsViewPager.adapter = adapter
        TabLayoutMediator(binding.roomsTabLayout, binding.roomsViewPager) { tab, position ->
            val tabTitles =
                resources?.getStringArray(R.array.rooms_fragments_titles) ?: emptyArray()
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun navigationToRegisterFragment(){
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, RoomCreationActivity::class.java))
        }
    }

}
