package com.khilman.ecommerceudacoding.network.model.detail_order_response

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class ProductsItem(

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("product_code")
	val productCode: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("show_in_slider")
	val showInSlider: String? = null,

	@field:SerializedName("cover")
	val cover: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("qty_order")
	val qtyOrder: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("product_photos")
	val productPhotos: List<Any?>? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("order_id")
	val orderId: Int? = null,

	@field:SerializedName("product_cover")
	val productCover: Any? = null
)