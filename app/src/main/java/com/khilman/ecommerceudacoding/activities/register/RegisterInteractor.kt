package com.khilman.ecommerceudacoding.activities.register

import com.khilman.ecommerceudacoding.network.model.register_response.Data
import com.khilman.ecommerceudacoding.network.model.register_response.ResponseRegister
import com.khilman.www.formchecklistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback

class RegisterInteractor {

    interface OnRegisterFinished {
        fun onRegisterSuccess(userProfile: Data?, message: String?)
        fun onRegisterError(message: String)
    }

    fun attempUserRegister(first_name: String? = null,
                           last_name: String? = null,
                           email: String? = null,
                           password: String? = null,
                           password_confirm: String?,
                           listener: OnRegisterFinished){

        //todo: validate the registeration data
        if(this.validateRegisterationData(first_name, last_name, email, password, password_confirm, listener)){
            first_name
            last_name
            email
            password
            password_confirm
            //todo: send data to api server
            InitRetrofit().getInstance()
                    .requestRegister(firstName = first_name,
                    lastName = last_name,
                    email = email,
                    password = password)
                    .enqueue(object : Callback<ResponseRegister> {
                        override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                            //todo: handle connection problem
                            t.printStackTrace()
                            listener.onRegisterError("Connection error : ${t.message}")
                        }

                        override fun onResponse(call: Call<ResponseRegister>, response: retrofit2.Response<ResponseRegister>) {
                            if (response.isSuccessful){
                                response.toString()
                                //todo: handle good response
                                // is authentication success ?
                                if (response.body()?.isSuccess!!){
                                    val userProfile = response.body()?.data
                                    val message = response.body()?.message
                                    listener.onRegisterSuccess(userProfile, message)
                                } else {
                                    listener.onRegisterError(response.body()?.message.toString())
                                }
                            } else {
                                //todo: handle bad response
                                listener.onRegisterError("Error : ${response.code()} ${response.message()}")
                            }
                        }
                    })
        }
    }

    private fun validateRegisterationData(first_name: String?, last_name: String?,
                                          email: String?,
                                          password: String?,
                                          confirm_password: String?,
                                          listener: OnRegisterFinished): Boolean {
        if (first_name!!.isEmpty() || last_name!!.isEmpty() || email!!.isEmpty() || password!!.isEmpty() || confirm_password!!.isEmpty()){
            listener.onRegisterError("Input can't empty !")
            return false
        }

        if (password != confirm_password){
            listener.onRegisterError("Confirmation password doesn't match !")
            return false
        }

        return true
    }
}