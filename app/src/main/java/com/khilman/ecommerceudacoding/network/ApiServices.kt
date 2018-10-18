package com.khilman.www.formchecklistapp.network

import com.khilman.ecommerceudacoding.network.model.delete_shop_cart_response.ResponseDeleteFromCart
import com.khilman.ecommerceudacoding.network.model.detail_order_response.ResponseDetailOrder
import com.khilman.ecommerceudacoding.network.model.edit_shipping_address_respone.ResponseUpdateShippingAddress
import com.khilman.ecommerceudacoding.network.model.home_categories_response.ResponseHomeCategories
import com.khilman.ecommerceudacoding.network.model.home_products_response.ResponseHomeProducts
import com.khilman.ecommerceudacoding.network.model.home_promotions_response.ResponseHomePromotions
import com.khilman.ecommerceudacoding.network.model.insert_shopping_cart_response.ResponseInsertShoppingCart
import com.khilman.ecommerceudacoding.network.model.login_response.ResponseLogin
import com.khilman.ecommerceudacoding.network.model.order_history_response.ResponseOrderHistory
import com.khilman.ecommerceudacoding.network.model.order_response.ResponseOrder
import com.khilman.ecommerceudacoding.network.model.product_detail_response.ResponseProductDetail
import com.khilman.ecommerceudacoding.network.model.profile_response.ResponseProfile
import com.khilman.ecommerceudacoding.network.model.register_response.ResponseRegister
import com.khilman.ecommerceudacoding.network.model.response_update_qty_cart.ResponseUpdateQtyCart
import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.ResponseShoppingCart
import com.khilman.ecommerceudacoding.network.model.upload_paymentproof_response.ResponseUploadPaymentProof
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    //todo: get detail of product
    @GET("get-product")
    fun requestGetDetailProduct(
            @Query("product_id") productId: String
    ): Call<ResponseProductDetail>

    //todo: insert shopping cart
    @FormUrlEncoded
    @POST("insert-cart-item")
    fun requestInsertShoppingCart(
            @Field("user_id") userId: String?,
            @Field("product_id") productId: String?,
            @Field("qty") qtyProduct: String?
    ): Call<ResponseInsertShoppingCart>

    //todo: get detail profile of user
    @GET("get-profile")
    fun requestProfile(
            @Query("user_id") userId: String?
    ): Call<ResponseProfile>


    //todo: Update github
    @FormUrlEncoded
    @POST("update-profile")
    fun requestUpdateProfile(
            @Field("user_id") userId: String?,
            @Field("first_name") firstName: String?,
            @Field("last_name") lastName: String?
    ): Call<ResponseProfile>


    //todo: update foto profile
    @Multipart
    @POST("update-photo-profile")
    fun requestUpdatePhotoProfile(
            @Part image: MultipartBody.Part,
            @Part("user_id") userId: RequestBody
    ): Call<ResponseProfile>

    //todo: update shipping address
    @FormUrlEncoded
    @POST("update-shipping-address")
    fun requestUpdateShippingAddress(
            @Field("address_id") address_id: String?,
            @Field("title") title: String?,
            @Field("city") city: String?,
            @Field("province") province: String?,
            @Field("zip_code") zip_code: String?,
            @Field("address") address: String?,
            @Field("is_main_address") is_main_address: String?
    ): Call<ResponseUpdateShippingAddress>


    //todo: shopping cart
    @GET("get-shopping-carts")
    fun requestGetShoppingCarts(
            @Query("user_id") userId: String?
    ): Call<ResponseShoppingCart>

    //todo: delete item from cart
    @GET("delete-cart-item")
    fun requestDeleteCartItem(
            @Query("cart_id") cartId: String?
    ): Call<ResponseDeleteFromCart>

    //todo: update qty product order
    @FormUrlEncoded
    @POST("update-cart-item-qty")
    fun requestUpdateQtyCartItem(
            @Field("cart_id") cartId: String?,
            @Field("new_qty") newQty: String?
    ): Call<ResponseUpdateQtyCart>

    //todo: response insert order
    @GET("insert-order")
    fun requestInsertOrder(
            @Query("user_id") userId: String?
    ): Call<ResponseOrder>

    //todo: get detail order
    @GET("get-order-detail")
    fun requestOrderDetail(
            @Query("order_id") orderId: String?
    ): Call<ResponseDetailOrder>

    //todo: upload payment proof
    @Multipart
    @POST("update-payment-proof")
    fun requestUploadPaymentProof(
            @Part image: MultipartBody.Part,
            @Part("order_id") orderId: RequestBody
    ): Call<ResponseUploadPaymentProof>

    //todo: get products by category
    @FormUrlEncoded
    @POST("get-home-products")
    fun requestGetProductByCategory(
            @Field("category_id") category_id: String
    ): Call<ResponseHomeProducts>

    //todo: History order
    @GET("get-orders")
    fun requestOrderHistory(
            @Query("user_id") userId: String?
    ): Call<ResponseOrderHistory>
}