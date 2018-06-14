package br.com.ms.moipchallenge.modules

fun <T : Any> measureTime(method: () -> T): Pair<Long, T> {
    val startTime = System.currentTimeMillis()
    val methodReturn = method()
    val timeTaken = System.currentTimeMillis() - startTime
    return timeTaken to methodReturn
}