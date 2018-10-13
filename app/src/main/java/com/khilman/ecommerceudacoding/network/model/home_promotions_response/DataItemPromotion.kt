package com.khilman.ecommerceudacoding.network.model.home_promotions_response

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class DataItemPromotion(

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("product_code")
	val productCode: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("show_in_slider")
	val showInSlider: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("qty")
	val qty: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("product_photos")
	val productPhotos: List<ProductPhotosItem?>? = null,

	@field:SerializedName("product_cover")
	val productCover: String? = null
)