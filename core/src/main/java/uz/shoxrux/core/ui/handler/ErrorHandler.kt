package uz.shoxrux.core.ui.handler

import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.io.IOException

class ErrorHandler {

    fun parse(throwable: Throwable): String {
        return when (throwable) {
            is IOException -> "Нет соединения с интернетом"
            is HttpException -> "Ошибка сервера: ${throwable.message}"
            is TimeoutCancellationException -> "Время ожидания истекло"
            else -> "Неизвестная ошибка"
        }
    }

}