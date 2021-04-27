package com.joshua.spacexlaunch.model.remote

sealed class ApiResult<T> {
    class Loading<T> : ApiResult<T>()
    class Loaded<T> : ApiResult<T>()
    data class Success<T>(val result: T?) : ApiResult<T>()
    data class RecoverableError<T>(val throwable: Throwable) : ApiResult<T>()
    data class NonRecoverableError<T>(val throwable: Throwable) : ApiResult<T>()

}