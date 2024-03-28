package network.data

data class LoginSuccess(
    val code: Int,
    val message: String,
    val token: String,
    val username: String
)