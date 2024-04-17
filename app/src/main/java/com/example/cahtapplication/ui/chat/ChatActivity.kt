package com.example.cahtapplication.ui.chat

import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.cahtapplication.R
import com.example.cahtapplication.base.BaseActivity
import com.example.cahtapplication.databinding.ActivityChatBinding
import com.example.cahtapplication.model.Room
import com.example.cahtapplication.ui.chat.adapter.MessageAdapter
import com.example.cahtapplication.ui.home.fragments.adapter.RoomsAdapter

class ChatActivity : BaseActivity<ChatViewModel, ActivityChatBinding>() {
    companion object {
        const val ROOM_KEY = "room_key"
    }

    lateinit var room: Room
    lateinit var messagesAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        room = if (Build.VERSION.SDK_INT < TIRAMISU){
            intent.getSerializableExtra(ROOM_KEY) as Room
        }else{
            intent.getSerializableExtra(ROOM_KEY, Room::class.java)!!
        }
        viewModel.room = room
        viewModel.startListening()
        binding.activityTitleTv.text = room.title
        initMessagesRecyclerView()
    }

    override fun initViewModel(): ChatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.activity_chat

    private fun initMessagesRecyclerView(){
        messagesAdapter = MessageAdapter(listOf())
        binding.messagesRv.adapter = messagesAdapter
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.messages.observe(this){
            messagesAdapter.updateMessagesList(it)
        }
    }
}