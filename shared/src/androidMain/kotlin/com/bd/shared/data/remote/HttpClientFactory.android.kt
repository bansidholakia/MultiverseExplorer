package com.bd.shared.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp

actual fun platformHttpClient(
    config: HttpClientConfig<*>.() -> Unit
): HttpClient {

    return HttpClient(OkHttp) {

        config(this)
    }
}