package com.joshua.spacexlaunch.model.remote

import com.google.gson.annotations.SerializedName
import com.joshua.spacexlaunch.SPACE_X_BASE_URL
import com.joshua.spacexlaunch.model.remote.response.LaunchItemListResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.Serializable

class ApiRepository(private val client: HttpClient) {

    companion object {
        const val DESC = -1
        const val FALCON_9_ID = "5e9d0d95eda69973a809d1ec"
    }

    @KtorExperimentalAPI
    suspend fun getAllLaunches(page: Int, limit: Int) =
        client.post<LaunchItemListResponse>("$SPACE_X_BASE_URL/launches/query") {
            contentType(ContentType.Application.Json)
            body = RequestData(
                QueryData(
                    null
                ),
                OptionsData(
                    SortData(),
                    page,
                    limit
                )
            )
        }

}

@Serializable
data class RequestData(
    val query :QueryData,
    val options: OptionsData
)

@Serializable
data class OptionsData(
    val sort: SortData,
    val page:Int,
    val limit:Int
)

@Serializable
data class SortData(
    val date_unix:String = ApiRepository.DESC.toString(),
)

@Serializable
data class QueryData(
    @SerializedName("\$and")
    val and:AndData?
)

@Serializable
data class AndData(
    val rocket:Rocket?,
    val upcoming:Upcoming?
)

@Serializable
class Rocket(
    @SerializedName("\$eq")
    val eq: String
)

@Serializable
class Upcoming(
    @SerializedName("\$eq")
    val eq: Boolean
)
