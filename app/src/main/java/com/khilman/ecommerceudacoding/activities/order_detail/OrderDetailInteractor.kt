package com.khilman.ecommerceudacoding.activities.order_detail

import com.khilman.ecommerceudacoding.network.model.detail_order_response.Data
import com.khilman.ecommerceudacoding.network.model.detail_order_response.ResponseDetailOrder
import com.khilman.ecommerceudacoding.network.model.upload_paymentproof_response.ResponseUploadPaymentProof
import com.khilman.www.formchecklistapp.network.InitRetrofit
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class OrderDetailInteractor {
    interface OnGetDetailsFinished {
        fun onSuccess(oderData: Data?)
        fun onError(message: String)
    }
    interface OnUploadDocumentFinished {
        fun onUploadSuccess(oderData: com.khilman.ecommerceudacoding.network.model.upload_paymentproof_response.Data?)
        fun onUploadError(message: String)
    }
    fun getOrderDetail(orderId: String, listener: OnGetDetailsFinished){
        InitRetrofit().getInstance().requestOrderDetail(orderId)
                .enqueue(object : Callback<ResponseDetailOrder> {
                    override fun onFailure(call: Call<ResponseDetailOrder>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseDetailOrder>, response: Response<ResponseDetailOrder>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                val oderData = response.body()?.data
                                listener.onSuccess(oderData)
                            } else {
                                listener.onError(response.body()?.message!!)
                            }
                        } else {
                            listener.onError("Failed : ${response.code()} ${response.message()}")
                        }
                    }

                })
    }

    fun updatePaymentProof(file: File?, fileName: String, orderId: String, listener: OnUploadDocumentFinished){
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val bodyRequest = MultipartBody.Part.createFormData("file", fileName, reqFile)
        val orderId = RequestBody.create(MediaType.parse("text/plain"), orderId)

        InitRetrofit().getInstance().requestUploadPaymentProof(bodyRequest, orderId)
                .enqueue(object : Callback<ResponseUploadPaymentProof> {
                    override fun onFailure(call: Call<ResponseUploadPaymentProof>, t: Throwable) {
                        t.printStackTrace()
                        listener.onUploadError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseUploadPaymentProof>, response: Response<ResponseUploadPaymentProof>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                val oderData = response.body()?.data
                                listener.onUploadSuccess(oderData)
                            } else {
                                listener.onUploadError(response.body()?.message!!)
                            }
                        } else {
                            listener.onUploadError("Failed : ${response.code()} ${response.message()}")
                        }
                    }

                })
    }
}