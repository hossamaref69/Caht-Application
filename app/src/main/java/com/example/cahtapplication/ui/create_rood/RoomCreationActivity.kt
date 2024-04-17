package com.example.cahtapplication.ui.create_rood

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.cahtapplication.R
import com.example.cahtapplication.base.BaseActivity
import com.example.cahtapplication.databinding.ActivityRoomCreationBinding

class RoomCreationActivity : BaseActivity<RoomCreationViewModel, ActivityRoomCreationBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.events.observe(this){
            it.let {
                when(it){
                     CreateRoomEvents.RoomCreated -> {
                         finish()
                     }
                }
            }
        }
    }

    override fun initViewModel(): RoomCreationViewModel =
        ViewModelProvider(this)[RoomCreationViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.activity_room_creation
}