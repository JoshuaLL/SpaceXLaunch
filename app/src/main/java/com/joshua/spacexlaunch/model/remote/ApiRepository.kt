package com.joshua.spacexlaunch.model.remote

import com.joshua.spacexlaunch.SPACE_X_BASE_URL
import com.joshua.spacexlaunch.model.remote.response.LaunchesApiListResponse
import com.joshua.spacexlaunch.model.vo.LaunchItem
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

class ApiRepository( private val client: HttpClient) {

    @KtorExperimentalAPI
    suspend fun getAllLaunches(limit:Int =5, offset:Int =0) = client.get<LaunchesApiListResponse>("$SPACE_X_BASE_URL/v4/launches/query") {
        contentType(ContentType.Application.Json)
        body = parameter("limit", limit)
        body = parameter("offset", offset)
    }


}