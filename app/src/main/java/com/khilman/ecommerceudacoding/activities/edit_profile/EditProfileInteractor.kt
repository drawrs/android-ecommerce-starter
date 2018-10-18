package com.khilman.ecommerceudacoding.activities.edit_profile

import com.khilman.ecommerceudacoding.network.model.profile_response.Data
import com.khilman.ecommerceudacoding.network.model.profile_response.ResponseProfile
import com.khilman.www.formchecklistapp.network.InitRetrofit
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class EditProfileInteractor {

    interface OnUpdateProfileFinished {
        fun onError(errorMessage: String)
        fun onSuccess(profileData: Data?)
    }

    interface OnUploadPhotoProfileFinished {
        fun onUploadSuccess(oderData: Data?)
        fun onUploadError(message: String)
    }

    fun setNewUserProfileData(userId: String,
                              userFirstName: String,
                              userLastName: String,
                              listener: OnUpdateProfileFinished) {
        //todo: send data to server
        InitRetrofit().getInstance().requestUpdateProfile(userId, userFirstName, userLastName)
                .enqueue(object : Callback<ResponseProfile> {
                    override fun onFailure(call: Call<ResponseProfile>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseProfile>, response: retrofit2.Response<ResponseProfile>) {
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

    fun updatePhotoProfile(file: File?, fileName: String, userId: String, listener: EditProfileInteractor.OnUploadPhotoProfileFinished){
        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val bodyRequest = MultipartBody.Part.createFormData("file", fileName, reqFile)
        val userId = RequestBody.create(MediaType.parse("text/plain"), userId)

        InitRetrofit().getInstance().requestUpdatePhotoProfile(bodyRequest, userId)
                .enqueue(object : Callback<ResponseProfile> {
                    override fun onFailure(call: Call<ResponseProfile>, t: Throwable) {
                        t.printStackTrace()
                        listener.onUploadError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseProfile>, response: Response<ResponseProfile>) {
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