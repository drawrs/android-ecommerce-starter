package com.khilman.ecommerceudacoding.network.model.product_detail_response

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class ProductPhotosItem(

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("file_name")
	val fileName: String? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("is_cover")
	val isCover: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null
)