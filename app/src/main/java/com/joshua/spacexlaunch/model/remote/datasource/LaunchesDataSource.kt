package com.joshua.spacexlaunch.model.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joshua.spacexlaunch.model.remote.ApiRepository
import com.joshua.spacexlaunch.model.vo.LaunchItem
import timber.log.Timber

class LaunchesDataSource(private val apiRepository : ApiRepository): PagingSource<Int, LaunchItem>() {

    companion object {
        const val PER_LIMIT = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LaunchItem> {
        return try{
            val nextPage = params.key ?: 1
            val result = apiRepository.getAllLaunches(nextPage, PER_LIMIT)
            Timber.i("GetLaunches result docs =${result?.docs}")

            LoadResult.Page(
                data = result.docs!!,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage < result.totalPages!!)
                    result.page?.plus(1) else null
            )
        }catch (e:Exception){
            Timber.i("GetLaunches Exception=$e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LaunchItem>): Int? {
        return state.anchorPosition
    }

}

