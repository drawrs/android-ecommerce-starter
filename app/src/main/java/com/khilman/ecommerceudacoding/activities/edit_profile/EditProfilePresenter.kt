package com.khilman.ecommerceudacoding.activities.edit_profile

import android.content.SharedPreferences
import com.khilman.ecommerceudacoding.network.model.profile_response.Data
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.www.formchecklistapp.network.InitRetrofit
import java.io.File

class EditProfilePresenter(var view: EditProfileView?,
                           val pref: SharedPreferences,
                           var interactor: EditProfileInteractor):
        EditProfileInteractor.OnUpdateProfileFinished,
        EditProfileInteractor.OnUploadPhotoProfileFinished {


    fun updateUserProfile(userId: String, userFirstName: String, userLastName: String){
        view?.showProgress()
        interactor.setNewUserProfileData(userId,
                userFirstName,
                userLastName,
                this)
    }
    fun updatePhotoProfile(file: File?, fileName: String, userId: String){
        interactor.updatePhotoProfile(file, fileName, userId, this)
    }
    override fun onError(errorMessage: String) {
        view?.apply {
            showMessage(errorMessage)
            hideProgress()
        }
    }

    override fun onSuccess(profileData: Data?) {
        //todo: set new data to shared preferences
        val editor = pref.edit()

        editor.putString(MyConstants.PREF_FIRST_NAME, profileData?.firstName)
        editor.putString(MyConstants.PREF_LAST_NAME, profileData?.lastName)

        editor.commit()

        // todo : notice user data has been updated
        view?.apply {
            showMessage("Successfully Updated !")
            hideProgress()
        }
    }

    override fun onUploadSuccess(profile: Data?) {
        val photoProfileUrl = "${InitRetrofit.API_BASE_PHOTO_PROFILE}${profile?.photo}"
        view?.apply {
            showMessage("sukses")
            showUpdatePhotoProfile(photoProfileUrl)
        }
    }

    override fun onUploadError(message: String) {
        view?.apply {
            showMessage(message)
        }
    }
}
