package com.khilman.ecommerceudacoding.network.model.detail_order_response

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Data(

	@field:SerializedName("order_code")
	val orderCode: String? = null,

	@field:SerializedName("order_status")
	val orderStatus: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("total_price")
	val totalPrice: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("payment_proof")
	val paymentProof: String? = null,

	@field:SerializedName("shipping_address")
	val shippingAddress: String? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
)