package com.khilman.ecommerceudacoding.activities.product_detail

import com.khilman.ecommerceudacoding.network.model.insert_shopping_cart_response.ResponseInsertShoppingCart
import com.khilman.www.formchecklistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailInteractor {
    interface OnInsertProductFinished {
        fun onInsertSuccess(message: String)
        fun onInsertError(message: String)
    }

    fun insertShoppingCart(userId: String, productId: String, qty: String, listener: OnInsertProductFinished){
        InitRetrofit().getInstance().requestInsertShoppingCart(userId, productId, qty)
                .enqueue(object : Callback<ResponseInsertShoppingCart> {
                    override fun onFailure(call: Call<ResponseInsertShoppingCart>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onInsertError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseInsertShoppingCart>, response: Response<ResponseInsertShoppingCart>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                //val shoppingCartData = response.body()?.data
                                listener.onInsertSuccess(response.body()?.message!!)
                            } else {
                                listener.onInsertError(response.body()?.message!!)
                            }
                        } else {

                        }
                    }

                })
    }
}