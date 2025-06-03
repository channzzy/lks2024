package com.example.lksmarket.response

data class ProductResponse(
	val products: List<ProductsItem?>? = null
)

data class ProductsItem(
	val price: Double? = null,
	val rating: Any? = null,
	val id: Int? = null,
	val title: String? = null,
	val thumbnail: String? = null
)

