package uz.shoxrux.core.ui.handler

sealed class AppError {
    object NoInternet : AppError()
    data class Server(val message: String?) : AppError()
    object Timeout : AppError()
    data class Unknown(val throwable: Throwable? = null) : AppError()
}