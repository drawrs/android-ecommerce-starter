package com.khilman.www.formchecklistapp.network

import com.khilman.ecommerceudacoding.network.model.home_categories_response.ResponseHomeCategories
import com.khilman.ecommerceudacoding.network.model.home_products_response.ResponseHomeProducts
import com.khilman.ecommerceudacoding.network.model.home_promotions_response.ResponseHomePromotions
import com.khilman.ecommerceudacoding.network.model.login_response.ResponseLogin
import com.khilman.ecommerceudacoding.network.model.register_response.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    //todo: request login
    @FormUrlEncoded
    @POST("login")
    fun requestLogin(
            @Field("email") email: String?,
            @Field("password") password: String?
    ): Call<ResponseLogin>


    //todo: request register
    @FormUrlEncoded
    @POST("register")
    fun requestRegister(
            @Field("first_name") firstName: String?,
            @Field("last_name") lastName: String?,
            @Field("email") email: String?,
            @Field("password") password: String?
    ): Call<ResponseRegister>

    @GET("get-promotion-products")
    fun requestPromotions(): Call<ResponseHomePromotions>

    @GET("get-home-products")
    fun requestHomeProducts(
            @Query("q") keyword: String? = null
    ): Call<ResponseHomeProducts>

    @GET("get-categories")
    fun requestCategories(): Call<ResponseHomeCategories>


}