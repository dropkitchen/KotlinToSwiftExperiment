package io.github.imjn.kotlinflowtoswiftasync
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatFactKtorClient(
    private val httpClient: HttpClient
): CatFactClient {
    override fun getCatFactWithCommonFlow(): CommonFlow<CatFact> = flow {
        val result: HttpResponse = httpClient.get("https://catfact.ninja/fact")
        val catFact: CatFact = result.body<CatFact>()
        emit(catFact)
    }.toCommonFlow()

    override fun getCatFactWithFlow(): Flow<CatFact> = flow {
        val result: HttpResponse = httpClient.get("https://catfact.ninja/fact")
        val catFact: CatFact = result.body<CatFact>()
        emit(catFact)
    }

    override suspend fun getCatFactWithSuspend(): CatFact {
        val result: HttpResponse = httpClient.get("https://catfact.ninja/fact")
        return result.body<CatFact>()
    }
}

interface CatFactClient {
    fun getCatFactWithCommonFlow(): CommonFlow<CatFact>
    fun getCatFactWithFlow(): Flow<CatFact>
    suspend fun getCatFactWithSuspend(): CatFact
}