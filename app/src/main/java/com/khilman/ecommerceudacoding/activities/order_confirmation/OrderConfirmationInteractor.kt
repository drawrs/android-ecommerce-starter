package com.khilman.ecommerceudacoding.activities.order_confirmation

import com.khilman.ecommerceudacoding.network.model.order_response.Data
import com.khilman.ecommerceudacoding.network.model.order_response.ResponseOrder
import com.khilman.ecommerceudacoding.network.model.profile_response.ResponseProfile
import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.ResponseShoppingCart
import com.khilman.www.formchecklistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderConfirmationInteractor {

    interface OnGetCartProductFinished {
        fun onSuccess(shoppingCartResponse: ResponseShoppingCart?)
        fun onError(message: String)
    }

    interface OnOrderFinished {
        fun onOrderSuccess(message: Data?)
        fun onOrderError(message: String)
    }
    interface OnGetProfileFinishedListener {
        fun onError(errorMessage: String)
        fun onSuccess(profileData: com.khilman.ecommerceudacoding.network.model.profile_response.Data?)
    }

    fun getCartProductList(userId: String?, listener: OnGetCartProductFinished){
        InitRetrofit().getInstance().requestGetShoppingCarts(userId = userId)
                .enqueue(object : Callback<ResponseShoppingCart> {
                    override fun onFailure(call: Call<ResponseShoppingCart>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseShoppingCart>, response: Response<ResponseShoppingCart>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                listener.onSuccess(response.body())
                            } else {
                                listener.onError(response.body()?.message!!)
                            }
                        } else {
                            listener.onError("Failed : ${response.code()} ${response.message()}")
                        }
                    }
                })
    }

    fun insertDataOrder(userId: String?, listener: OrderConfirmationInteractor.OnOrderFinished){

        userId
        InitRetrofit().getInstance().requestInsertOrder(userId)
                .enqueue(object : Callback<ResponseOrder> {
                    override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onOrderError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                val orderData = response.body()?.data
                                listener.onOrderSuccess(orderData)
                            } else {
                                listener.onOrderError(response.body()?.message!!)
                            }
                        } else {
                            listener.onOrderError("Failed : ${response.code()} ${response.message()}")
                        }
                    }

                })
    }

    fun getUserProfile(userId: String?, listener: OrderConfirmationInteractor.OnGetProfileFinishedListener) {
        //todo : get user data from network
        InitRetrofit().getInstance().requestProfile(userId)
                .enqueue(object : Callback<ResponseProfile> {
                    override fun onFailure(call: Call<ResponseProfile>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onError("Error : ${t.message}")
                    }
                    override fun onResponse(call: Call<ResponseProfile>, response: Response<ResponseProfile>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                val profileData = response.body()?.data
                                listener.onSuccess(profileData)
                            } else {
                                listener.onError(response.body()?.message!!)
                            }
                        } else {
                            listener.onError("Failed : ${response.code()} ${response.message()}")
                        }
                    }
                })
    }
}