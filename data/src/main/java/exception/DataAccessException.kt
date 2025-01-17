package exception

sealed class DataAccessException(override val message: String) : Exception(message) {
    class NetworkException(message: String = "Network error occurred") : DataAccessException(message)
    class ApiException(message: String = "API error occurred") : DataAccessException(message)
    class UnknownException(message: String = "An unknown error occurred") : DataAccessException(message)
}
