package com.example.lksmarket.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

//	@field:SerializedName("data")
//	val data: String? = null,

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

//	@field:SerializedName("message")
//	val message: String? = null,
//
//	@field:SerializedName("status")
//	val status: Int? = null
)
