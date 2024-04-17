package com.example.cahtapplication.ui.create_rood

sealed class CreateRoomEvents {

    data object RoomCreated: CreateRoomEvents(){}
}