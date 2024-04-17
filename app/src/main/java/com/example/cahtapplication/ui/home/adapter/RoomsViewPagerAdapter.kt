package com.example.cahtapplication.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cahtapplication.ui.home.fragments.my_rooms.MyRoomsFragment
import com.example.cahtapplication.ui.home.fragments.all_rooms.AllRoomsFragment

const val PAGES_NUM = 2

class RoomsViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = PAGES_NUM

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 ->  MyRoomsFragment()
            else -> AllRoomsFragment()
        }
        return fragment
    }
}

