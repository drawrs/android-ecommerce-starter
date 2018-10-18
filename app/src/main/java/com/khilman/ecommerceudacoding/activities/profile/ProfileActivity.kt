package com.khilman.ecommerceudacoding.activities.profile

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.edit_profile.EditProfileActivity
import com.khilman.ecommerceudacoding.adapters.AdapterShippingAddress
import com.khilman.ecommerceudacoding.network.model.profile_response.Data
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.SessionManager
import com.khilman.ecommerceudacoding.utils.hide
import com.khilman.ecommerceudacoding.utils.show
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ProfileActivity : AppCompatActivity(), ProfileView  {

    private lateinit var presenter: ProfilePresenter
    private lateinit var pref: SharedPreferences
    private var photoProfileUrl: String = "no_image.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.title = "Profile"
        //todo: init component
        pref = SessionManager(this).getInstance()
        presenter = ProfilePresenter(this, pref, ProfileInteractor())

        //todo: show user profile
        presenter.showUserProfile()
    }
    override fun showProgress() {
        progressBar.show()
        containerUserProfie.hide()
    }

    override fun hideProgress() {
        progressBar.hide()
        containerUserProfie.show()
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun setProfileData(profileData: Data?) {
        if (profileData?.photo != null){
            photoProfileUrl = "${InitRetrofit.API_BASE_PHOTO_PROFILE}${profileData?.photo}"
            Picasso.with(this).load(photoProfileUrl).into(profile_image)
        }

        tvUserFullName.text =  "${profileData?.firstName} ${profileData?.lastName}"
        tvUserEmail.text = "${profileData?.email}"

        //todo: show shipping address
        rvShippingAddress.layoutManager = LinearLayoutManager(this)
        rvShippingAddress.adapter = AdapterShippingAddress(this, profileData?.shippingAddress)


        //todo: event
        btnEditProfile.onClick {
            presenter.updateUserProfile()
        }
    }

    override fun navigateToEditProfile(userId: String, userEmail: String, userFirstName: String, userLastName: String) {
        startActivity<EditProfileActivity>(MyConstants.PREF_USER_ID to userId,
                MyConstants.PREF_EMAIL to userEmail,
                MyConstants.PREF_FIRST_NAME to userFirstName,
                MyConstants.PREF_LAST_NAME to userLastName,
                MyConstants.PREF_PHOTO_PROFILE to photoProfileUrl)
    }
}
