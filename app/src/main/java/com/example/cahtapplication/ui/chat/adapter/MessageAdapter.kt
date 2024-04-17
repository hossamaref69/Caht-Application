package com.example.cahtapplication.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cahtapplication.R
import com.example.cahtapplication.databinding.ReceivedMessageBinding
import com.example.cahtapplication.databinding.SendMessageBinding
import com.example.cahtapplication.model.RoomMessage
import com.example.cahtapplication.model.UserProvider

class MessageAdapter(var message: List<RoomMessage>) : RecyclerView.Adapter<MessageAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: ViewDataBinding) : ViewHolder(binding.root) {
        fun bind(message: RoomMessage) {
            if (binding is ReceivedMessageBinding) {
                (binding as ReceivedMessageBinding).message = message
            } else {
                (binding as SendMessageBinding).message = message
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        if (viewType == 0) {
            val binding = DataBindingUtil.inflate<SendMessageBinding>(
                LayoutInflater.from(parent.context),
                R.layout.send_message,
                parent,
                false
            )
            return MyViewHolder(binding)
        } else {
            val binding = DataBindingUtil.inflate<ReceivedMessageBinding>(
                LayoutInflater.from(parent.context),
                R.layout.received_message,
                parent,
                false
            )
            return MyViewHolder(binding)
        }
    }

    fun updateMessagesList(newMessage: List<RoomMessage>){
        message = newMessage
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val message = message[position]
        return if (message.senderId == UserProvider.user!!.id) {
            0
        } else {
            1
        }
    }

    override fun getItemCount(): Int = message.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(message[position])
    }
}
