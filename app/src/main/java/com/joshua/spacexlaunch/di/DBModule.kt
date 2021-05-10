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
//    single { provideAppDatabase(get()) }
//    single { provideAppDao(get()) }
//    single { provideDBRepository(get(), get(), get()) }
}

//fun provideAppDatabase(context: Context): SpaceXDB {
//    return Room.databaseBuilder(context, SpaceXDB::class.java, DB_NAME)
//            .allowMainThreadQueries()
//            .fallbackToDestructiveMigration()
//            .build()
//}
//
//fun provideAppDao(db: SpaceXDB)= db.launches()

//fun provideDBRepository(
//    dao: LaunchesDao,
//    apiRepository : ApiRepository,
//    sharedPrefs: SharedPreferences
//) = LaunchesRepository(dao, apiRepository, sharedPrefs)
//
