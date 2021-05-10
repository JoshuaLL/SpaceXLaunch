package com.joshua.spacexlaunch.model.vo

import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.joshua.spacexlaunch.TABLE_LAUNCHES
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class LaunchItem(
    val id: String,
    val flight_number: Int,
    val name: String?,
    val details: String?,
    val date_utc: String?,
    val date_unix: Long?,
    val links: Links?,
    val success: Boolean?
)

@Serializable
data class Links(
    val patch: Patch?
)

@Serializable
data class Patch(
    val small: String?
)

//@Serializable
//@Entity(tableName = TABLE_LAUNCHES)
//data class LaunchItem(
//    @PrimaryKey
//    @SerializedName("flight_number")
//    @ColumnInfo(name = "flight_number")
//    val flight_number: Int,
//
//    @SerializedName("mission_name")
//    @ColumnInfo(name = "mission_name")
//    val missionName: String?,
//
//    @SerializedName("mission_id")
//    @TypeConverters(JsonArrayToStringConverter::class)
//    @ColumnInfo(name = "mission_id")
//    val missionId: MutableList<String>?,
//
//    @SerializedName("launch_date_unix")
//    @ColumnInfo(name = "launch_date_unix")
//    val launchDateUnix: Long?,
//
//    @SerializedName("is_tentative")
//    @ColumnInfo(name = "is_tentative")
//    val isTentative: Boolean,
//
//    @SerializedName("launch_site")
//    @TypeConverters(LaunchSiteConverter::class)
//    @ColumnInfo(name = "launch_site")
//    val launch_site: LaunchSiteItem,
//
//    @SerializedName("rocket")
//    @TypeConverters(RocketConverter::class)
//    @ColumnInfo(name = "rocket")
//    val rocket: RocketItem,
//
//    @SerializedName("launch_success")
//    @ColumnInfo(name = "launch_success")
//    val launchSuccess: Boolean,
//
//    @SerializedName("links")
//    @TypeConverters(LaunchLinksConverter::class)
//    @ColumnInfo(name = "links")
//    val links: Links,
//
//    @SerializedName("details")
//    @ColumnInfo(name = "details")
//    val details: String?
//)
//
//@Serializable
//data class Links(
//        @SerializedName("mission_patch")
//    val missionPatch: String?,
//        @SerializedName("mission_patch_small")
//    val missionPatchSmall: String?,
//        @SerializedName("reddit_campaign")
//    val redditCampaign: String?,
//        @SerializedName("reddit_launch")
//    val redditLaunch: String?,
//        @SerializedName("reddit_recovery")
//    val redditRecovery: String?,
//        @SerializedName("reddit_media")
//    val redditMedia: String?,
//        @SerializedName("presskit")
//    val presskit: String?,
//        @SerializedName("article_link")
//    val articleLink: String?,
//        @SerializedName("wikipedia")
//    val wikipedia: String?,
//        @SerializedName("video_link")
//    val videoLink: String?
//) {
//
//    @Ignore
//    fun getLinksWithNamesAsList(): List<Pair<String, String?>> = listOf(
//        Pair("Reddit Campaign", redditCampaign),
//        Pair("Reddit Launch", redditLaunch),
//        Pair("Reddit Recovery", redditRecovery),
//        Pair("Reddit Media", redditMedia),
//        Pair("Presskit", presskit),
//        Pair("Article", articleLink),
//        Pair("Wikipedia", wikipedia),
//        Pair("Video", videoLink)
//    )
//}
//
//class JsonArrayToStringConverter {
//
//    private val gson = Gson()
//
//    @TypeConverter
//    fun jsonArrayToString(missionIds: MutableList<String>?): String? {
//        return gson.toJson(missionIds)
//    }
//
//    @TypeConverter
//    fun stringToJsonArray(data: String?): MutableList<String>? {
//        if (data == null) {
//            return Collections.emptyList()
//        }
//
//        val listType = object : TypeToken<MutableList<String>>() {
//        }.type
//
//        return gson.fromJson(data, listType)
//    }
//}
//
