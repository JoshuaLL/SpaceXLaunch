package com.joshua.spacexlaunch.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.joshua.spacexlaunch.DB_NAME
import com.joshua.spacexlaunch.model.db.SpaceXDB
import com.joshua.spacexlaunch.model.db.LaunchesDao
import com.joshua.spacexlaunch.model.remote.ApiRepository
import com.joshua.spacexlaunch.model.repository.LaunchesRepository

import org.koin.dsl.module

val DBModule = module {

}

