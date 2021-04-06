package com.joshua.spacexlaunch.ui.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LaunchesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Launches Fragment"
    }
    val text: LiveData<String> = _text
}