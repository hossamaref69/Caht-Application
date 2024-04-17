package com.example.cahtapplication.data.repositories.auth_repo

import com.example.cahtapplication.model.User

interface AuthRepo {

    suspend fun register(userName: String, email: String, password: String): User

    suspend fun login(email: String, password: String): User
}