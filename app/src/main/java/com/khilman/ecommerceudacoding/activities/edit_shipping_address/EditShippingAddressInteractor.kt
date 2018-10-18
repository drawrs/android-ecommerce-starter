package com.khilman.ecommerceudacoding.activities.edit_shipping_address

import com.khilman.ecommerceudacoding.network.model.edit_shipping_address_respone.Data
import com.khilman.ecommerceudacoding.network.model.edit_shipping_address_respone.ResponseUpdateShippingAddress
import com.khilman.www.formchecklistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditShippingAddressInteractor  {

    interface OnUpdateAddressFinished {
        fun onSuccess(addressData: Data?)
        fun onError(message: String)
    }

    fun saveNewShippingAddress(shippingAddressId: String?,
                               addressTitle: String,
                               city: String,
                               province: String,
                               zipCode: String,
                               fullAddress: String,
                               isMainAdrress: String,
                               listener: OnUpdateAddressFinished) {
        //todo: send data to serve api
        InitRetrofit().getInstance().requestUpdateShippingAddress(shippingAddressId,
                addressTitle,
                city,
                province,
                zipCode,
                fullAddress,
                isMainAdrress)
                .enqueue(object : Callback<ResponseUpdateShippingAddress> {
                    override fun onFailure(call: Call<ResponseUpdateShippingAddress>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseUpdateShippingAddress>, response: Response<ResponseUpdateShippingAddress>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                val addressData = response.body()?.data
                                listener.onSuccess(addressData)
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