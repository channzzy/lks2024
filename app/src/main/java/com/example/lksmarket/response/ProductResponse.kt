package com.example.lksmarket.response

import java.io.Serializable

data class ProductResponse(
	val products: List<ProductsItem?>? = null
)

data class ProductsItem(
	val price: Double = 0.0,
	val rating: Any? = null,
	val id: Int? = null,
	val title: String? = null,
	val thumbnail: String? = null,
	val quantity: Int = 0,
	val total: Double = 0.0
) : Serializable

