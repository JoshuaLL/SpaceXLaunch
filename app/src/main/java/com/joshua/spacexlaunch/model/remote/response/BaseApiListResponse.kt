package com.joshua.spacexlaunch.model.remote.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
open class BaseApiListResponse<Item> {
    @SerializedName("totalPages")
    val totalPages: Int? = null
    @SerializedName("totalDocs")
    val totalDocs: Int? = null
    @SerializedName("docs")
    val docs: List<Item>? = null
    @SerializedName("page")
    val page: Int? = null
}