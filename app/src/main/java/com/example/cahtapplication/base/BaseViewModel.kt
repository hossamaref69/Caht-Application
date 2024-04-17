package com.example.cahtapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cahtapplication.model.ViewMessage

open class BaseViewModel: ViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var viewMessageLiveData = MutableLiveData<ViewMessage>()
}