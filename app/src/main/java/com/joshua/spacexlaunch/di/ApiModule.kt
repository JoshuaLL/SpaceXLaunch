package com.joshua.spacexlaunch.di

import com.joshua.spacexlaunch.model.repository.ApiRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.koin.dsl.module

val ApiModule = module {
    single { okHttpKtor }
    single { ApiRepository(get()) }
}

private val okHttpKtor = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}
