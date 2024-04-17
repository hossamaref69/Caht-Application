package com.example.cahtapplication.data.repositories.auth_repo

import android.util.Log
import com.example.cahtapplication.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthRepoImpl: AuthRepo {

    override suspend fun register(userName: String, email: String, password: String): User {
        val auth = Firebase.auth.createUserWithEmailAndPassword(email, password)
            .await()
        val user = User(id = auth.user!!.uid, email = email, userName = userName)
        Firebase.firestore.collection(User.COLLECTION_NAME).document(user.id!!).set(user)
        return user
    }

    override suspend fun login(email: String, password: String): User {
        val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
        val docRef =
            Firebase.firestore.collection(User.COLLECTION_NAME).document(authResult.user!!.uid)
        val snapshot = docRef.get().await()
        return snapshot.toObject(User::class.java)!!
    }
}
