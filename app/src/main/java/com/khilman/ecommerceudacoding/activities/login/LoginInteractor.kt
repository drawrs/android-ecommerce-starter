package com.khilman.ecommerceudacoding.activities.login

import android.content.SharedPreferences
import com.khilman.ecommerceudacoding.network.model.login_response.Data
import com.khilman.ecommerceudacoding.network.model.login_response.ResponseLogin
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.www.formchecklistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback

class LoginInteractor {

    interface OnLoginFinished {
        fun onLoginError(message: String)
        fun onLoginSuccess(userProfile: Data?)
    }

    fun attempUserLogin(email: String? = null, password: String? = null, listener: OnLoginFinished){
        //Send data to retrofit
        InitRetrofit().getInstance().requestLogin(email = email, password = password)
                .enqueue(object : Callback<ResponseLogin> {
                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        t.printStackTrace()
                        //todo: handle connection problem
                        listener.onLoginError("Connection error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseLogin>, response: retrofit2.Response<ResponseLogin>) {
                        if (response.isSuccessful){
                            //todo: handle good response
                            // is authentication success ?
                            if (response.body()?.isSuccess!!){
                                val userProfile = response.body()?.data
                                listener.onLoginSuccess(userProfile)
                            } else {
                                listener.onLoginError(response.body()?.message!!)
                            }
                        } else {
                            //todo: handle bad response
                            listener.onLoginError("Error : ${response.code()} ${response.message()}")
                        }
                        //todo: hide the progress bar
                    }
                })
    }

    fun saveLoginStateToCache(pref: SharedPreferences?, isRememberLogin: Boolean) {
        //todo: save state login to SharedPreferences
        pref?.edit()?.putBoolean(MyConstants.PREF_IS_LOGGINED, isRememberLogin)?.commit()
    }

    fun saveUserProfileToCache(pref: SharedPreferences?, userId: String, email: String, firstName: String, lastName: String) {
        //todo: user profile to sharedpreferences
        val prefEdit = pref?.edit()

        prefEdit?.putString(MyConstants.PREF_EMAIL, email)
        prefEdit?.putString(MyConstants.PREF_USER_ID, userId)
        prefEdit?.putString(MyConstants.PREF_FIRST_NAME, firstName)
        prefEdit?.putString(MyConstants.PREF_LAST_NAME, lastName)

        prefEdit?.apply()
    }
}