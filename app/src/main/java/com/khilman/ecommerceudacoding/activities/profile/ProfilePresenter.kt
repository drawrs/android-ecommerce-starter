package com.khilman.ecommerceudacoding.activities.profile

import android.content.SharedPreferences
import com.khilman.ecommerceudacoding.network.model.profile_response.Data
import com.khilman.ecommerceudacoding.utils.MyConstants

class ProfilePresenter (var view: ProfileView?,
                        val pref: SharedPreferences,
                        var interactor: ProfileInteractor) : ProfileInteractor.OnGetProfileFinishedListener {

    var userId: String
    var userEmail: String
    var userFirstName: String
    var userLastName: String

    init {
        userId = pref?.getString(MyConstants.PREF_USER_ID, null)
        userEmail = pref?.getString(MyConstants.PREF_EMAIL, null)
        userFirstName = pref?.getString(MyConstants.PREF_FIRST_NAME, null)
        userLastName = pref?.getString(MyConstants.PREF_LAST_NAME, null)
    }
    fun showUserProfile(){
        view?.showProgress()
        interactor?.getUserProfile(userId, this)
    }

    fun updateUserProfile(){
        view?.navigateToEditProfile(userId,
                userEmail,
                userFirstName,
                userLastName)
    }

    override fun onError(errorMessage: String) {
        view?.apply {
            showMessage(errorMessage)
            hideProgress()
        }
    }

    override fun onSuccess(profileData: Data?) {
        view?.apply {
            setProfileData(profileData)
            hideProgress()
        }
    }
}