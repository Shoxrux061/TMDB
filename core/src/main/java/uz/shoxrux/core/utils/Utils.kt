package uz.shoxrux.core.utils

import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import retrofit2.Response
import uz.shoxrux.core.handler.AppError
import uz.shoxrux.core.handler.NetworkResult
import java.io.IOException

suspend inline fun <T, R> safeApiCall(
    crossinline apiCall: suspend () -> Response<T>,
    crossinline mapper: (T) -> R
): NetworkResult<R> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()

            body?.let { safeBody ->
                NetworkResult.Success(mapper(safeBody!!))
            }
                ?: NetworkResult.Error(AppError.Validation)
        } else {
            NetworkResult.Error(AppError.Server(response.message()))
        }
    } catch (e: IOException) {
        NetworkResult.Error(AppError.Server(e.message))
    } catch (e: HttpException) {
        NetworkResult.Error(AppError.Server(e.message()))
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        NetworkResult.Error(AppError.Unknown(e))
    }
}

inline fun <T> NetworkResult<T>.onResult(
    onSuccess: (T) -> Unit,
    onError: (AppError) -> Unit
) {
    when (this) {
        is NetworkResult.Success -> onSuccess(data)
        is NetworkResult.Error -> onError(error)
    }
}