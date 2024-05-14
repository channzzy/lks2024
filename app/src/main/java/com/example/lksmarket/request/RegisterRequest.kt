package com.example.lksmarket.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("password_confirmation")
	val passwordConfirmation: String? = null,
)
