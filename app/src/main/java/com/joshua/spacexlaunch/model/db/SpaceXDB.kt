package com.joshua.spacexlaunch.model.db

import androidx.room.*
import com.joshua.spacexlaunch.model.vo.*

@Database(entities = [LaunchItem::class], version = 1, exportSchema = false)
@TypeConverters(
    value = [
        RocketConverter::class,
        LaunchSiteConverter::class,
        JsonArrayToStringConverter::class,
        LaunchLinksConverter::class
    ]
)
abstract class SpaceXDB : RoomDatabase() {
    abstract fun launches(): LaunchesDao
}


