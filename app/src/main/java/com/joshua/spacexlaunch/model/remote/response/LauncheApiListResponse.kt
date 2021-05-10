package com.joshua.spacexlaunch.model.remote.response

import com.google.gson.annotations.SerializedName
import com.joshua.spacexlaunch.model.vo.LaunchItem
import kotlinx.serialization.Serializable

@Serializable
data class LaunchItemListResponse (
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("totalDocs")
    val totalDocs: Int,
    @SerializedName("docs")
    val docs: List<LaunchItem>,
    @SerializedName("page")
    val page: Int
)