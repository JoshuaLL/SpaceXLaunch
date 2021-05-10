package com.joshua.spacexlaunch.ui.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.joshua.spacexlaunch.extensions.isValid
import com.joshua.spacexlaunch.model.remote.ApiRepository
import com.joshua.spacexlaunch.model.remote.datasource.LaunchesDataSource
import com.joshua.spacexlaunch.model.remote.datasource.LaunchesDataSource.Companion.PER_LIMIT
import com.joshua.spacexlaunch.model.vo.LaunchItem
import com.joshua.spacexlaunch.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class LaunchesViewModel : BaseViewModel() {

    private val apiRepository : ApiRepository by inject()

    private val _launchesState= MutableLiveData<LaunchesState>(LaunchesState.Loaded)
      val launchesState: LiveData<LaunchesState> = _launchesState

    val launchesData: Flow<PagingData<LaunchesState>> = launchesPagingData()
    .catch { e->
        if (e.message.isValid()) LaunchesState.RecoverableError(Exception(e.message))
        else LaunchesState.NonRecoverableError(Exception("Un-traceable"))
    }
    .map { pagingData -> pagingData.map { LaunchesState.Launches(it) } }
        .map {
            it.insertSeparators { before, after ->
                if (after == null) {
                    //End of the list
                    return@insertSeparators LaunchesState.EndFooterItem
                } else {
                    null
                }
            }
        }
    .distinctUntilChanged()

    fun allLaunches(){
        viewModelScope.launch {
//            _launchesState.value = LaunchesState.Launches(launchesPagingData())
//            getAllLaunches()
        }
    }

    private fun launchesPagingData(): Flow<PagingData<LaunchItem>> =
        Pager(PagingConfig(PER_LIMIT)) {
                LaunchesDataSource(apiRepository)
        }.flow



//    private suspend fun getPagingData() {
//        Pager(
//            config = PagingConfig(
//                pageSize = PAGE_SIZE,
//                prefetchDistance = PAGE_SIZE.div(PER_LIMIT)
//            ),
//            pagingSourceFactory = { LaunchesDataSource(apiRepository) }
//        ).flow.catch {e->
//            _allLaunches.value =
//                if (e.message.isValid()) LaunchesState.RecoverableError(Exception(e.message))
//                else LaunchesState.NonRecoverableError(Exception("Un-traceable"))
//        }.onStart {
//            _allLaunches.value = LaunchesState.Loading
//        }.collect{
//            _allLaunches.value = LaunchesState.Success(it)
//        }
//    }

//    @OptIn(KtorExperimentalAPI::class)
//    private suspend fun getAllLaunches(){
//        Timber.i("GetLaunches")
//        flow<LaunchesState> {
//            try{
//                val result = apiRepository.getAllLaunches(1, 10)
//                Timber.i("GetLaunches result=$result")
//                emit(LaunchesState.Success(result.docs))
//            }catch (e:Exception){
//                Timber.i("GetLaunches Exception=$e")
//                throw e
//            }
//        }.flowOn(Dispatchers.IO)
//            .onStart { emit(LaunchesState.Loading) }
//            .catch { e ->
//                if (e.message.isValid()) {
//                    emit(LaunchesState.RecoverableError(Exception(e.message)))
//                } else {
//                    emit(LaunchesState.NonRecoverableError(Exception("Un-traceable")))
//                }
//            }.collect {
//                _launchesState.value =it
//            }
//
//    }
}

sealed class LaunchesState {
    object Loading : LaunchesState()
    object Loaded : LaunchesState()
    object EndFooterItem : LaunchesState()
    data class Launches(val data: LaunchItem) : LaunchesState()
//    data class Success(val data: List<LaunchItem>) : LaunchesState()
    data class RecoverableError(val exception: Exception) : LaunchesState()
    data class NonRecoverableError(val exception: Exception) : LaunchesState()

}