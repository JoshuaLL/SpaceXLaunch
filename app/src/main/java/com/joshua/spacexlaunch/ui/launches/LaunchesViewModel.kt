package com.joshua.spacexlaunch.ui.launches

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
import org.koin.core.component.inject

class LaunchesViewModel : BaseViewModel() {

    private val apiRepository : ApiRepository by inject()

    val launchesData: Flow<PagingData<LaunchesState>> = launchesPagingData()
    .catch { e->
        if (e.message.isValid()) LaunchesState.RecoverableError(Exception(e.message))
        else LaunchesState.NonRecoverableError(Exception("Un-traceable"))
    }
    .map { pagingData -> pagingData.map { LaunchesState.Launches(it) } }
        .map {
            it.insertSeparators { before, after ->
                if (after == null) {
                    return@insertSeparators LaunchesState.EndFooterItem
                } else {
                    null
                }
            }
        }
    .distinctUntilChanged()

    private fun launchesPagingData(): Flow<PagingData<LaunchItem>> =
        Pager(PagingConfig(PER_LIMIT)) {
                LaunchesDataSource(apiRepository)
        }.flow
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