package uz.shoxrux.core.handler

sealed class AppError {
    object NoInternet : AppError()
    data class Server(val message: String?) : AppError()
    object Timeout : AppError()
    data class Unknown(val throwable: Throwable? = null) : AppError()
    object Validation : AppError()
}