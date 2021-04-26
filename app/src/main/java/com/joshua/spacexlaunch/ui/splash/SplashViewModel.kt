package com.joshua.spacexlaunch.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.joshua.spacexlaunch.model.vo.LaunchItem
import com.joshua.spacexlaunch.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel(){

    private val _state = MutableLiveData<SplashState>(SplashState.Loading)
    val state :LiveData<SplashState> = _state

    fun switchHome(){
        viewModelScope.launch {
           delay(3000)
            _state.value = SplashState.Loaded
        }
    }
}

sealed class SplashState {
    object Loading : SplashState()
    object Loaded : SplashState()
    data class Error(val exception: Exception) : SplashState()
}