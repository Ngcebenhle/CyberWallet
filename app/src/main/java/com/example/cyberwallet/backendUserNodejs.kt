package com.example.cyberwallet

data class backendUserNodejs(
    val email: String,
    val name: String,
    val type: String,
    val password: String
)

data class backendUserNodejsResponse(
    val Success: Boolean,
    val token: String,
    val refresh_token: String,

)

data class backendUserNodejsLogInResponse(
    val token: String,
    val refresh_token: String,

    )

data class backendUserNodejslogin(
    val email: String,
    val password: String,

    )

