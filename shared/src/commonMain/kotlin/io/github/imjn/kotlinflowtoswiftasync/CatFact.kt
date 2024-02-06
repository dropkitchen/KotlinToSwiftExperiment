package io.github.imjn.kotlinflowtoswiftasync
import kotlinx.serialization.Serializable

@Serializable
data class CatFact(
    val fact: String,
    val length: Int
)
