package com.near.common.domain.utils

import kotlinx.coroutines.flow.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response

@Throws(ServerException::class)
fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw HttpException(this)
    return body()!!
}

suspend fun <R> networkWithCache(
    networkCall: suspend () -> R,
    loadFromLocal: suspend () -> R?,
    shouldFetch: (R?) -> Boolean,
    saveResult: suspend (R) -> Unit
): R {
    val cache = loadFromLocal()
    return if (cache == null || shouldFetch(cache)) {
        networkCall().also { saveResult(it) }
    } else {
        cache
    }
}

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: suspend () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(Result.Loading)
        try {
            saveFetchResult(fetch())
            query().map { Result.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Result.Failure(Exception(throwable)) }
        }
    } else {
        query().map { Result.Success(it) }
    }

    emitAll(flow)
}

fun readServerError(exception: HttpException): ServerException {
    val errorString = exception.response()?.errorBody()?.string()
        ?: return ServerException(null, exception.code())

    val errorBodyObject = JSONObject(errorString)
    val errorData = errorBodyObject.getJSONObject("data")
    var errorMessage: String? = null

    if (!errorData.isNull("additionalInfo")) {
        val additionalInfo = errorData.get("additionalInfo")
        errorMessage = when (additionalInfo) {
            is JSONArray -> {
                additionalInfo.getJSONObject(0).getString("message")
            }
            is JSONObject -> {
                if (additionalInfo.has("message")) additionalInfo.getString("message") else null
            }
            else -> null
        }
    }
    if (errorMessage == null) {
        errorMessage = when {
            errorData.get("message") is String -> {
                errorData.getString("message")
            }
            errorData.get("message") is JSONObject -> {
                errorData.getJSONObject("message").getString("fa")
            }
            else -> null
        }
    }

    return ServerException(errorMessage, exception.code())
}
