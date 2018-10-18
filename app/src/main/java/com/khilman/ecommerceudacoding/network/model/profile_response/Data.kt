package com.khilman.ecommerceudacoding.network.model.profile_response

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class Data(

	@field:SerializedName("selected_shipping_city")
	val selectedShippingCity: String? = null,

	@field:SerializedName("selected_shipping_zip_code")
	val selectedShippingZipCode: String? = null,

	@field:SerializedName("selected_shipping_province")
	val selectedShippingProvince: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("selected_shipping_address")
	val selectedShippingAddress: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("shipping_address")
	val shippingAddress: List<ShippingAddressItem?>? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)