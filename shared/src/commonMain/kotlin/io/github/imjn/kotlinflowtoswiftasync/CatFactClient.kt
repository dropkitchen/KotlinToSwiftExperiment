package io.github.imjn.kotlinflowtoswiftasync
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.Url

class CatFactKtorClient(
    private val httpClient: HttpClient
): CatFactClient {
    override suspend fun getCatFact(): CatFact {
        val result = httpClient.get("https://catfact.ninja/fact")
        return result.body<CatFact>()
    }
}

interface CatFactClient {
    suspend fun getCatFact(): CatFact
}