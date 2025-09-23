package uz.shoxrux.core.handler

import android.util.Log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

inline fun <T, R> safeApiCall(
    crossinline apiCall: suspend () -> T,
    crossinline mapper: (T) -> R
): Flow<NetworkResult<R>> = flow {
    try {
        val response = apiCall()
        emit(NetworkResult.Success(mapper(response)))
    } catch (e: HttpException) {
        Log.e("safeApiCall", "HTTP ${e.code()} ${e.message()}")
        emit(NetworkResult.Error(AppError.Server(e.message())))
    } catch (e: IOException) {
        Log.e("safeApiCall", "Network error: ${e.message}")
        emit(NetworkResult.Error(AppError.Server(e.message)))
    } catch (e: Throwable) {
        if (e is CancellationException) throw e
        emit(NetworkResult.Error(AppError.Unknown(e)))
    } catch (e: Exception) {
        Log.e("safeApiCall", "Unknown error: ${e.message}", e)
        emit(NetworkResult.Error(AppError.Unknown()))
    }
}