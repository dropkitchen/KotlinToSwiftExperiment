package io.github.imjn.kotlinflowtoswiftasync

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.*

actual open class CommonFlow<T> actual constructor(
    private val flow: Flow<T>
): Flow<T> by flow {

    fun subscribe(
        coroutineScope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        onCollect: (T) -> Unit
    ): DisposableHandle {
        val job = coroutineScope.launch(dispatcher) {
            flow.collect(onCollect)
        }
        return DisposableHandle { job.cancel() }
    }

    fun subscribe(
        onCollect: (T) -> Unit
    ): DisposableHandle {
        return subscribe(
            coroutineScope = GlobalScope,
            dispatcher = Dispatchers.Main,
            onCollect = onCollect
        )
    }
}