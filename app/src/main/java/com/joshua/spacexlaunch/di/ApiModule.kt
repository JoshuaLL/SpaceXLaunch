package com.joshua.spacexlaunch.di

import com.fasterxml.jackson.databind.SerializationFeature
import com.joshua.spacexlaunch.API_TIME_OUT
import com.joshua.spacexlaunch.model.remote.ApiRepository
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import org.koin.dsl.module
import timber.log.Timber
import java.text.DateFormat

val ApiModule = module {
    single { okHttpKtor }
    single { ApiRepository(get()) }
}

private val okHttpKtor = HttpClient(Android) {

    engine {
        connectTimeout = API_TIME_OUT
        socketTimeout = API_TIME_OUT
    }

    io.ktor.client.features.observer.ResponseObserver { response ->
        Timber.v("Ktor response => $response")
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }

    install(JsonFeature) {
        serializer = JacksonSerializer{
            enable(SerializationFeature.INDENT_OUTPUT)
            dateFormat = DateFormat.getDateInstance()
        }
    }

    install(JsonFeature) {
        serializer = GsonSerializer{
            setPrettyPrinting()
            disableHtmlEscaping()
        }
    }

    install(ResponseObserver) {
        onResponse { response ->
            Timber.v("response Ktor => $response")
        }
    }


    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Timber.v("Logger Ktor => $message")
            }

        }
        level = LogLevel.ALL
    }

}
