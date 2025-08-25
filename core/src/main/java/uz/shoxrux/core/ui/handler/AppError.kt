package uz.shoxrux.core.ui.handler

sealed class AppError {
    object NoInternet : AppError()
    data class Server(val message: String?) : AppError()
    object Timeout : AppError()
    object Unknown : AppError()
}