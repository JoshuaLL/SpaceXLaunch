package com.joshua.spacexlaunch.model.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joshua.spacexlaunch.model.remote.ApiRepository
import com.joshua.spacexlaunch.model.vo.LaunchItem
import com.joshua.spacexlaunch.ui.launches.LaunchesState
import timber.log.Timber

class LaunchesDataSource(private val apiRepository : ApiRepository): PagingSource<Int, LaunchItem>() {

    companion object {
        const val PAGE_SIZE = 20
        const val PER_LIMIT = 10
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LaunchItem> {
        try{
            val nextPage = params.key ?: 1
            val result = apiRepository.getAllLaunches(nextPage)
            Timber.i("GetLaunches result=$result")

            LoadResult.Page(
                data = result.docs!!,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage < result.totalPages!!)
                    result.page?.plus(1) else null
        }catch (e:Exception){
            Timber.i("GetLaunches Exception=$e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LaunchItem>): Int? {
        return state.anchorPosition
    }

}