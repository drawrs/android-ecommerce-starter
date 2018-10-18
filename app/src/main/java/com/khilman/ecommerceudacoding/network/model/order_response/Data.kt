package com.khilman.ecommerceudacoding.network.model.order_response

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Data(

	@field:SerializedName("order_code")
	val orderCode: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("shipping_address")
	val shippingAddress: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)