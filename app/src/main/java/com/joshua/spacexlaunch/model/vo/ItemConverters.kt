package com.joshua.spacexlaunch.model.vo

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LaunchSiteConverter {

    private val gson = Gson()
    private val type = object : TypeToken<LaunchSiteItem>() {}.type

    @TypeConverter
    fun launchSiteToString(nextLaunchSite: LaunchSiteItem): String? = gson.toJson(nextLaunchSite, type)

    @TypeConverter
    fun stringToLaunchSite(nextLaunchSiteString: String): LaunchSiteItem? = gson.fromJson(nextLaunchSiteString, type)
}

class RocketConverter {

    private val gson = Gson()
    private val type = object : TypeToken<RocketItem>() {}.type

    @TypeConverter
    fun rocketToString(rocket: RocketItem): String = gson.toJson(rocket, type)

    @TypeConverter
    fun stringToRocket(rocketString: String): RocketItem = gson.fromJson(rocketString, type)
}

class LaunchLinksConverter {

    private val gson = Gson()
    private val type = object : TypeToken<LaunchItem.Links>() {}.type

    @TypeConverter
    fun launchSiteToString(links: LaunchItem.Links): String? = gson.toJson(links, type)

    @TypeConverter
    fun stringToLaunchSite(linksString: String): LaunchItem.Links? = gson.fromJson(linksString, type)
}
