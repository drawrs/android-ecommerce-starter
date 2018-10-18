package com.khilman.ecommerceudacoding.activities.profile

import com.khilman.ecommerceudacoding.network.model.profile_response.Data

interface ProfileView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)
    fun setProfileData(profileData: Data?)
    fun navigateToEditProfile(userId: String, userEmail: String, userFirstName: String, userLastName: String)
}