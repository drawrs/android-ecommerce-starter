package com.khilman.ecommerceudacoding.activities.login

import com.khilman.ecommerceudacoding.network.model.login_response.Data

interface LoginView {
    fun onLoginSuccess(userProfile: Data?)
    fun navigateToHome()
    fun hideProgress()
    fun showProgress()
    fun showMessage(message: String?)
}