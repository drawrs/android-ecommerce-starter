package com.khilman.ecommerceudacoding.activities.order_history

import com.khilman.ecommerceudacoding.network.model.order_history_response.DataItem
import com.khilman.ecommerceudacoding.network.model.order_history_response.ResponseOrderHistory
import com.khilman.www.formchecklistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderHistoryInteractor {
    interface OnGetOrderHistoryFinished {
        fun onError(message: String)
        fun onSuccess(orderHistoryData: List<DataItem?>?)
    }

    fun getOrderHistory(userId: String, listener: OrderHistoryInteractor.OnGetOrderHistoryFinished){
        InitRetrofit().getInstance().requestOrderHistory(userId)
                .enqueue(object : Callback<ResponseOrderHistory> {
                    override fun onFailure(call: Call<ResponseOrderHistory>, t: Throwable) {
                        t.printStackTrace()
                        listener.onError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseOrderHistory>, response: Response<ResponseOrderHistory>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                val orderHistoryData = response.body()?.data
                                listener.onSuccess(orderHistoryData)
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