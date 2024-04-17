package com.example.cahtapplication.data.repositories.rooms_repo

import com.example.cahtapplication.model.Room
import com.example.cahtapplication.model.RoomMessage
import com.example.cahtapplication.model.UserProvider
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RoomsRepoImpl : RoomsRepo {

    override suspend fun createRoom(title: String, category: String, description: String) {
        val docRef = FirebaseFirestore.getInstance()
            .collection(Room.COLLECTION_NAME).document()
        val room =
            Room(id = docRef.id, title = title, category = category, description = description)
        docRef.set(room).await()
    }

    override suspend fun getAllRooms(): List<Room> {
        val collection = FirebaseFirestore.getInstance().collection(Room.COLLECTION_NAME)
        val querySnapShot = collection.get().await()
        return querySnapShot.toObjects(Room::class.java)
    }

    override suspend fun sendMessage(message: String, roomId: String) {
        val roomDoc = FirebaseFirestore.getInstance().collection(Room.COLLECTION_NAME)
            .document(roomId)
        val messageDoc = roomDoc.collection(RoomMessage.COLLECTION_NAME).document()
        val roomMessage = RoomMessage(
            messageDoc.id,
            senderId = UserProvider.user!!.id!!,
            senderName = UserProvider.user!!.userName!!,
            content = message,
            date = Timestamp.now()
        )
        messageDoc.set(roomMessage).await()
    }

    override suspend fun startListeningToMessagesList(roomId: String):
            Flow<List<RoomMessage>> = flow {
        FirebaseFirestore.getInstance().collection(Room.COLLECTION_NAME).document(roomId)
            .collection(RoomMessage.COLLECTION_NAME).orderBy("date").snapshots().collect {
                emit(it.toObjects(RoomMessage::class.java))
            }
    }

}