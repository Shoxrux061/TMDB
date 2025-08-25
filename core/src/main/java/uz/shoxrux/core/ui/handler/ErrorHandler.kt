package uz.shoxrux.core.ui.handler

import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.io.IOException

class ErrorHandler {
    fun parse(throwable: Throwable): AppError {
        return when (throwable) {
            is IOException -> AppError.NoInternet
            is HttpException -> AppError.Server(throwable.message())
            is TimeoutCancellationException -> AppError.Timeout
            else -> AppError.Unknown
        }
    }
}