package com.joshua.spacexlaunch.model.repository

import com.joshua.spacexlaunch.SPACE_X_BASE_URL
import com.joshua.spacexlaunch.model.vo.LaunchItem
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

class ApiRepository( private val client: HttpClient) {

    @KtorExperimentalAPI
    suspend fun getAllLaunches() = client.request<List<LaunchItem>>("$SPACE_X_BASE_URL/v3/launches") {
        method = HttpMethod.Get
        headers {}
    }
}