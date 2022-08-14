package com.near.common.domain.utils

import com.near.common.domain.usecase.R
import com.near.common.domain.utils.Result.*
import kotlinx.coroutines.flow.MutableStateFlow

sealed class Result<out R> {

    data class Success<out T>(val data: T, val error: Exception? = null) : Result<T>()
    data class Failure(val error: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception=$error]"
            Loading -> "Loading"
        }
    }

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isFailure() = this is Failure
}

fun <R> Success<R>.bindError(suspicious: Result<R>): Success<R> {
    return this.copy(
        this.data,
        (suspicious as? Success<R>)?.error ?: (suspicious as? Failure)?.error
    )
}

fun <R, T> Result<R>.mapWith(mapper: (R) -> (T)): Result<T> {
    return when {
        this.isSuccess() -> {
            Success(mapper((this as Success).data))
        }
        this.isLoading() -> {
            Loading
        }
        else -> {
            Failure((this as Failure).error)
        }
    }
}

inline fun <R> Result<R>.onLoading(action: () -> Unit): Result<R> {
    if (this is Loading) {
        action()
    }
    return this
}

inline fun <T> Result<T>.onFailure(onFailure: (Throwable) -> Unit) {
    if (this is Failure) onFailure(error)
}

inline fun <R> Result<R>.onSuccess(action: (R) -> Unit): Result<R> {
    if (this is Success) {
        action(data)
    }
    return this
}

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Success<T>)?.data ?: fallback
}

inline fun <T> Result<T>.withResult(
    onSuccess: (T) -> Unit,
    onFailure: (Throwable) -> Unit,
    onLoading: (Boolean) -> Unit = {}
) {
    when (this) {
        Loading -> onLoading(true)
        is Failure -> {
            onLoading(false)
            onFailure(error)
        }
        is Success -> {
            onLoading(false)
            onSuccess(data)
        }
    }
}

inline fun <reified T> Result<T>.updateOnSuccess(stateFlow: MutableStateFlow<T>) {
    if (this is Success) {
        stateFlow.value = data
    }
}

val <T> Result<T>.data: T?
    get() = (this as? Success)?.data

val Result<*>.succeeded
    get() = this is Success && data != null

inline fun <T:Any, R> T?.ifNullReturn(
    onNullReturnValue: R,
    crossinline whenIsNotNull: (T) -> R
): R = if (this != null) whenIsNotNull(this) else onNullReturnValue

