package com.khilman.ecommerceudacoding.activities.edit_profile

interface EditProfileView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)
    fun showUpdatePhotoProfile(url: String)
}