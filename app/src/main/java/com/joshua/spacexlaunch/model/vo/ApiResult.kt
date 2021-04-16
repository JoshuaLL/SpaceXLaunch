package com.joshua.spacexlaunch.model.vo

sealed class ApiResult {
    object Loading : ApiResult()
    object Loaded : ApiResult()
    data class Success<T>(val data: T) : ApiResult()
    data class RecoverableError(val exception: Exception) : ApiResult()
    data class NonRecoverableError(val exception: Exception) : ApiResult()

}