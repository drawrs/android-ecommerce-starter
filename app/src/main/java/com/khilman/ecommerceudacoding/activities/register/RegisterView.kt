package com.khilman.ecommerceudacoding.activities.register

import com.khilman.ecommerceudacoding.network.model.register_response.Data

interface RegisterView {
    fun onRegisterSuccess(userProfile: Data?, message: String?)
    fun onRegisterFailed(message: String?)
    fun showProgress()
    fun hideProgress()
}