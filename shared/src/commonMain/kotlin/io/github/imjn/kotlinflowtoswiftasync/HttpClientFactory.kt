package io.github.imjn.kotlinflowtoswiftasync
import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun create(): HttpClient
}