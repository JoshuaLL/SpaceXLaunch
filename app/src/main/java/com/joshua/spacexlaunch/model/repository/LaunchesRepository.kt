package com.joshua.spacexlaunch.model.repository

import android.content.SharedPreferences
import com.joshua.spacexlaunch.KEY_LAUNCHES_LAST_REFRESH
import com.joshua.spacexlaunch.extensions.isValid
import com.joshua.spacexlaunch.model.db.LaunchesDao
import com.joshua.spacexlaunch.model.remote.ApiRepository
import com.joshua.spacexlaunch.model.remote.ApiResult
import com.joshua.spacexlaunch.model.vo.LaunchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import timber.log.Timber

class LaunchesRepository(
    private val dao: LaunchesDao,
    private val apiRepository: ApiRepository,
    sharedPrefs: SharedPreferences
) : BaseRepository(sharedPrefs) {

    override val lastRefreshDataKey: String = KEY_LAUNCHES_LAST_REFRESH

    suspend fun refreshData(forceRefresh: Boolean = false) {
        if (!forceRefresh) {
            val isRefreshNeeded = withContext(Dispatchers.IO) {
                checkIfDataRefreshNeeded(lastRefreshDataKey)
            }
            if (!isRefreshNeeded) {
                Timber.d("No data refresh needed")
                return
            }
        }
        Timber.d("Refreshing data")
    }

//    private suspend fun fetchLaunchesAndSaveToDb() {
//        Timber.d("fetchLaunchesAndSaveToDb called")
//        flow<ApiResult<List<LaunchItem>>> {
//            try {
//                val result = apiRepository.getAllLaunches()
//                Timber.i("GetLaunches result=$result")
//                emit(ApiResult.Success(result))
//            } catch (e: Exception) {
//                Timber.i("GetLaunches Exception=$e")
//                throw e
//            }
//        }
//            .flowOn(Dispatchers.IO)
//            .catch { e ->
//                if (e.message.isValid()) {
//                    emit(ApiResult.RecoverableError(e))
//                } else {
//                    emit(ApiResult.NonRecoverableError(e))
//                }
//            }
//            .collect {
//                when (it) {
//                    is ApiResult.Loaded -> {}
//                    is ApiResult.Loading -> {}
//                    is ApiResult.NonRecoverableError-> {
//                        Timber.i("GetLaunches Exception=${it.throwable}")
//                    }
//                    is ApiResult.RecoverableError ->
//                        Timber.i("GetLaunches Exception=${it.throwable}")
//                    is ApiResult.Success ->{
//
//                    }
//                }
//
//            }
//
//    }

}
