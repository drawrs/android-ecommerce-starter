package com.khilman.ecommerceudacoding.activities.register

import com.khilman.ecommerceudacoding.network.model.register_response.Data

class RegisterPresenter(val view: RegisterView, val interactor: RegisterInteractor):
        RegisterInteractor.OnRegisterFinished {

    fun registerUser(email: String,
                     password: String,
                     password_confirmation: String,
                     firstName: String,
                     lastName: String) {
        view.showProgress()
        //todo: pass data to interactor
        interactor.attempUserRegister(firstName, lastName, email, password, password_confirmation, this)
    }

    override fun onRegisterSuccess(userProfile: Data?, message: String?) {
        view?.hideProgress()
        view?.onRegisterSuccess(userProfile, message)
    }

    override fun onRegisterError(message: String) {
        view?.hideProgress()
        view?.onRegisterFailed(message)
    }
}