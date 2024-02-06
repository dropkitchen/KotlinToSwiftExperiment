package io.github.imjn.kotlinflowtoswiftasync

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform