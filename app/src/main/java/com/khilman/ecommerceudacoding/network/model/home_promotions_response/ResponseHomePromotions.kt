package com.khilman.ecommerceudacoding.network.model.home_promotions_response

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class ResponseHomePromotions(

        @field:SerializedName("response_status")
	val responseStatus: Int? = null,

        @field:SerializedName("data")
	val data: List<DataItemPromotion?>? = null,

        @field:SerializedName("message")
	val message: String? = null,

        @field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)