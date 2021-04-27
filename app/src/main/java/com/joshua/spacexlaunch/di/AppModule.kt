package com.joshua.spacexlaunch.di

import android.content.Context
import androidx.preference.PreferenceManager
import org.koin.dsl.module


val AppModule = module {
    single { provideSharedPreferences(get()) }

}

private fun provideSharedPreferences(context: Context) =
    PreferenceManager.getDefaultSharedPreferences(context)


