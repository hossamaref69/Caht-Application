package com.example.cahtapplication.ui.home.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cahtapplication.R
import com.example.cahtapplication.databinding.ItemRoomBinding
import com.example.cahtapplication.model.Room

class RoomsAdapter(private var rooms: List<Room>, var onItemClick: (Int ,Room) -> Unit ) :
    RecyclerView.Adapter<RoomsAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: ItemRoomBinding) : ViewHolder(binding.root) {

        fun bind(position: Int, room: Room) {
            binding.room = room
            binding.root.setOnClickListener {
                onItemClick.invoke(position, room)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<ItemRoomBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_room,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position, rooms[position])
    }

    fun updateRooms(newsRooms: List<Room>){
        rooms = newsRooms
        notifyDataSetChanged()
    }
}