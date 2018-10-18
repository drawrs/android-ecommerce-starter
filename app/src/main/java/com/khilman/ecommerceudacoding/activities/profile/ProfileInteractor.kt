package com.khilman.ecommerceudacoding.activities.profile

import com.khilman.ecommerceudacoding.network.model.profile_response.Data
import com.khilman.ecommerceudacoding.network.model.profile_response.ResponseProfile
import com.khilman.ecommerceudacoding.network.model.profile_response.ShippingAddressItem
import com.khilman.www.formchecklistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileInteractor {
    interface OnGetProfileFinishedListener {
        fun onError(errorMessage: String)
        fun onSuccess(profileData: Data?)
    }

    fun getUserProfile(userId: String?, listener: OnGetProfileFinishedListener) {
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