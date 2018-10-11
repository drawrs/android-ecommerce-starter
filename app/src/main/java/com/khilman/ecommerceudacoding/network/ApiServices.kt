package com.khilman.www.formchecklistapp.network

import com.khilman.ecommerceudacoding.network.model.login_response.ResponseLogin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServices {

    //todo: request login
    @FormUrlEncoded
    @POST("login")
    fun requestLogin(
            @Field("email") email: String?,
            @Field("password") password: String?
    ): Call<ResponseLogin>

}