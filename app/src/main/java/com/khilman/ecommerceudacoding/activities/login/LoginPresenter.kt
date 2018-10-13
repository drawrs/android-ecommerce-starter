package com.khilman.ecommerceudacoding.activities.login

import android.content.SharedPreferences
import com.khilman.ecommerceudacoding.network.model.login_response.Data

class LoginPresenter(val view: LoginView,
                     val interactor: LoginInteractor,
                     val pref: SharedPreferences): LoginInteractor.OnLoginFinished {

    fun attempUserLogin(email: String, password: String){
        view.showProgress()
        interactor.attempUserLogin(email, password, this)
    }

    override fun onLoginError(message: String) {
        view?.apply {
            hideProgress()
            showMessage(message)
        }
    }

    override fun onLoginSuccess(userProfile: Data?) {
        view.hideProgress()
        view.onLoginSuccess(userProfile)
        view.navigateToHome()
    }

    fun saveLoginStateToCache(isRememberMe: Boolean) {
        //todo: save data to session
        interactor.saveLoginStateToCache(pref, isRememberMe)
    }

    fun saveUserProfileToCache(userProfile: Data?) {
        if (userProfile != null){
            interactor.saveUserProfileToCache(pref,
                    userProfile?.id.toString(),
                    userProfile?.email.toString(),
                    userProfile?.firstName.toString(),
                    userProfile?.lastName.toString())
        }
    }

}