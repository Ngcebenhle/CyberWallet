package com.example.cyberwallet

import java.util.Date

data class ApiUser(
    val _id : String,
    val email: String,
    val verification_token_opt: String,
    val verification_token_opt_timeout: Date,
    val phone: String,
    val password : String,
    val  name : String,
    val type: String,
    val status : String

    )
