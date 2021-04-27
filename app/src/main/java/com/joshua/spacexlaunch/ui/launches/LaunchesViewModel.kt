package com.joshua.spacexlaunch.ui.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.joshua.spacexlaunch.extensions.isValid
import com.joshua.spacexlaunch.model.remote.ApiRepository
import com.joshua.spacexlaunch.model.vo.LaunchItem
import com.joshua.spacexlaunch.ui.base.BaseViewModel
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import timber.log.Timber

class LaunchesViewModel : BaseViewModel() {

    private val apiRepository : ApiRepository by inject()

    private val _allLaunches= MutableLiveData<LaunchesState>(LaunchesState.Loaded)
    val allLaunches: LiveData<LaunchesState> = _allLaunches

    init{
        viewModelScope.launch {
            getAllLaunches()
        }
    }

    @OptIn(KtorExperimentalAPI::class)
    private suspend fun getAllLaunches(){
        Timber.i("GetLaunches")
        flow<LaunchesState> {
            try{
                val result = apiRepository.getAllLaunches()
                Timber.i("GetLaunches result=$result")
                emit(LaunchesState.Success(result))
            }catch (e:Exception){
                Timber.i("GetLaunches Exception=$e")
                throw e
            }
        }.flowOn(Dispatchers.IO)
            .onStart { emit(LaunchesState.Loading) }
            .catch { e ->
                if (e.message.isValid()) {
                    emit(LaunchesState.RecoverableError(Exception(e.message)))
                } else {
                    emit(LaunchesState.NonRecoverableError(Exception("Un-traceable")))
                }
            }.collect {
                _allLaunches.value =it
            }

    }
}

sealed class LaunchesState {
    object Loading : LaunchesState()
    object Loaded : LaunchesState()
    data class Success(val list: List<LaunchItem>) : LaunchesState()
    data class RecoverableError(val exception: Exception) : LaunchesState()
    data class NonRecoverableError(val exception: Exception) : LaunchesState()
}