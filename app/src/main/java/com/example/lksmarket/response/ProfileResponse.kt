package com.example.lksmarket.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

//	@field:SerializedName("is_admin")
//	val isAdmin: Int? = null,

	@field:SerializedName("image")
	val image: String? = null,

//	@field:SerializedName("address")
//	val address: String? = null,

//	@field:SerializedName("updated_at")
//	val updatedAt: String? = null,

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

//	@field:SerializedName("created_at")
//	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("username")
	val username: String? = null
)
