package com.joshua.spacexlaunch.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.joshua.spacexlaunch.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel(){

    private val _switchNav = MutableLiveData<NavDirections>()
    val switchNav :LiveData<NavDirections> = _switchNav

    fun switchHome(directions: NavDirections){
        viewModelScope.launch {
           delay(1500)
            _switchNav.value = directions
        }
    }
}