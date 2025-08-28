package uz.shoxrux.core.ui.handler

sealed class AppError {
    object NoInternet : AppError()
    data class Server(val message: String?) : AppError()
    object Timeout : AppError()
    object Unknown : AppError()

    fun message(): String = when (this) {
        NoInternet -> "Нет подключения к интернету"
        is Server -> message ?: "Ошибка сервера"
        Timeout -> "Время ожидания истекло"
        Unknown -> "Неизвестная ошибка"
    }
}