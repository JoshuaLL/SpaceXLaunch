package com.joshua.spacexlaunch.model.repository

import android.content.SharedPreferences
import com.joshua.spacexlaunch.PREFS_KEY_REFRESH_INTERVAL
import timber.log.Timber

abstract class BaseRepository(private val prefs: SharedPreferences) {

    abstract val lastRefreshDataKey: String

    fun checkIfDataRefreshNeeded(lastRefreshKey: String): Boolean {
        val currentTimeMillis: Long = System.currentTimeMillis()
        val lastRefreshTime = prefs.getLong(lastRefreshKey, 0)
        Timber.d("Last refresh time: $lastRefreshTime")
        // Get refresh interval set in app settings (in hours) and multiply to get value in ms
        val refreshIntervalHours =
            prefs.getString(PREFS_KEY_REFRESH_INTERVAL, "3")?.toInt() ?: 3
        val refreshIntervalMillis = refreshIntervalHours * NUMBER_OF_MILLISECONDS_IN_HOUR
        Timber.d("Refresh Interval: $refreshIntervalMillis")

        return currentTimeMillis - lastRefreshTime > refreshIntervalMillis
    }

    fun saveRefreshTime(lastRefreshDataKey: String) {
        with(prefs.edit()) {
            putLong(lastRefreshDataKey, java.lang.System.currentTimeMillis())
            apply()
        }
    }

    companion object {
        const val NUMBER_OF_MILLISECONDS_IN_HOUR = 360000
    }
}