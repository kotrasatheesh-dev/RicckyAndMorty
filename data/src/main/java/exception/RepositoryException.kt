package exception

sealed class RepositoryException(override val message: String) : Exception(message) {
    class NetworkException(message: String = "Network error occurred") : RepositoryException(message)
    class ApiException(message: String = "API error occurred") : RepositoryException(message)
    class UnknownException(message: String = "An unknown error occurred") : RepositoryException(message)
}
