package com.example.cahtapplication.ui.home.fragments.my_rooms

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.cahtapplication.R
import com.example.cahtapplication.base.BaseFragment
import com.example.cahtapplication.databinding.FragmentMyRoomsBinding
import com.example.cahtapplication.ui.chat.ChatActivity
import com.example.cahtapplication.ui.home.fragments.adapter.RoomsAdapter

class MyRoomsFragment : BaseFragment<MyRoomsViewModel, FragmentMyRoomsBinding>() {

    private lateinit var adapter: RoomsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = RoomsAdapter(listOf()) { position, room ->
            val intent = Intent(activity, ChatActivity::class.java)
            intent.putExtra(ChatActivity.ROOM_KEY, room)
            startActivity(intent)
        }
        binding.roomsRecyclerView.adapter = adapter
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.rooms.observe(this) {
            adapter.updateRooms(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshRooms()
    }

    override fun initViewModel(): MyRoomsViewModel =
        ViewModelProvider(this)[MyRoomsViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_my_rooms

}