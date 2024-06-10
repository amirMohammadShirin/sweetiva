package com.sweet.iva.core.common.helper


fun <T> transformResult(provider: (() -> T)): T {
    return try {
        provider.invoke()
    } catch (e: Exception) {
        throw e
    }
}

suspend fun <T> suspendTransformResult(provider: suspend (() -> T)): T {
    return try {
        provider.invoke()
    } catch (e: Exception) {
        throw e
    }
}


fun <T> transformApiResult(provider: (() -> T)): T {
    return try {
        provider.invoke()
    } catch (e: Exception) {
        throw e
    }
}