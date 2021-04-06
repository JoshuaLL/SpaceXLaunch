package com.joshua.spacexlaunch.model.db

import androidx.room.*
import com.joshua.spacexlaunch.model.vo.LaunchItem
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaunches(launches: List<LaunchItem>)

    @Query("SELECT * FROM launches_table")
    fun getAllLaunchesFlow(): Flow<List<LaunchItem>>

    @Query("SELECT COUNT(flight_number) FROM launches_table")
    fun getNumberOfLaunchesFlow(): Flow<Int>

    @Query("SELECT * FROM launches_table WHERE launch_date_unix " +
            "BETWEEN :startDateUnix AND :endDateUnix")
    fun getLaunchesBetweenDatesFlow(startDateUnix: Long, endDateUnix: Long): Flow<List<LaunchItem>>

    @Query("SELECT * FROM launches_table WHERE launch_date_unix > :startDateUnix")
    fun getLaunchesLaterThanDate(startDateUnix: Long): List<LaunchItem>

    @Query("DELETE FROM launches_table")
    fun deleteLaunchesData()

    @Transaction
    fun replaceAllLaunches(launches: List<LaunchItem>) {
        deleteLaunchesData()
        insertLaunches(launches)
    }
}
