package uz.shoxrux.core.ui.handler

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

inline fun <T, R> safeApiCall(
    crossinline apiCall: suspend () -> T,
    crossinline mapper: (T) -> R
): Flow<NetworkResult<R>> = flow {
    try {
        val response = apiCall()
        emit(NetworkResult.Success(mapper(response)))
    } catch (e: Exception) {
        emit(NetworkResult.Error(ErrorHandler.parse(e)))
    }
}