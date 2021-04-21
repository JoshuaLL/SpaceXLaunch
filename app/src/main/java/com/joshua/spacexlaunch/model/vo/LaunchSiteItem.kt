package com.joshua.spacexlaunch.model.vo

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchSiteItem(
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("site_name")
    val siteName: String,
    @SerializedName("site_name_long")
    val siteNameLong: String
)
