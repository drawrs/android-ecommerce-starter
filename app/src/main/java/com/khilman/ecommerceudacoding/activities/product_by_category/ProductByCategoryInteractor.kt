package com.khilman.ecommerceudacoding.activities.product_by_category

import com.khilman.ecommerceudacoding.network.model.home_products_response.DataItemProduct
import com.khilman.ecommerceudacoding.network.model.home_products_response.ResponseHomeProducts
import com.khilman.www.formchecklistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductByCategoryInteractor {
    interface OnGetProductFinished {
        fun onError(message: String)
        fun onSuccess(data: List<DataItemProduct?>)
    }

    fun getProductByCategory(categoryId: String, listener: OnGetProductFinished){
        InitRetrofit().getInstance().requestGetProductByCategory(categoryId)
                .enqueue(object : Callback<ResponseHomeProducts> {
                    override fun onFailure(call: Call<ResponseHomeProducts>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseHomeProducts>, response: Response<ResponseHomeProducts>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                listener.onSuccess(response.body()?.data!!)
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