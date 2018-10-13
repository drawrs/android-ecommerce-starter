package com.khilman.ecommerceudacoding.network.model.home_products_response

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class ResponseHomeProducts(

        @field:SerializedName("response_status")
	val responseStatus: Int? = null,

        @field:SerializedName("data")
	val data: List<DataItemProduct?>? = null,

        @field:SerializedName("message")
	val message: String? = null,

        @field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)