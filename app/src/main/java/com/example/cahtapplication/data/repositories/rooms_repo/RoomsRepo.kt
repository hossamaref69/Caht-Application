package com.example.cahtapplication.data.repositories.rooms_repo

import com.example.cahtapplication.model.Room
import com.example.cahtapplication.model.RoomMessage
import kotlinx.coroutines.flow.Flow


interface RoomsRepo {

    suspend fun createRoom(title: String, category: String, description: String)

    suspend fun getAllRooms(): List<Room>

    suspend fun sendMessage(message: String, roomId: String)

    suspend fun startListeningToMessagesList(roomId: String): Flow<List<RoomMessage>>

}