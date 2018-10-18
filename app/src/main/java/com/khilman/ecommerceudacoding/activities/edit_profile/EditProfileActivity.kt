package com.khilman.ecommerceudacoding.activities.edit_profile

import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.squareup.picasso.Picasso
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import java.io.File

class EditProfileActivity : AppCompatActivity(), EditProfileView, IPickResult {

    private lateinit var presenter: EditProfilePresenter
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        supportActionBar?.title = "Edit Profile"
        //todo: init component
        pref = this.getSharedPreferences(MyConstants.PREF_NAME, 0)
        presenter = EditProfilePresenter(this, pref, EditProfileInteractor())

        //todo: get data from intent
        val userId = intent.getStringExtra(MyConstants.PREF_USER_ID)
        initDataFromIntent()

        //todo: event
        btnUpdateProfile.onClick {
            presenter.updateUserProfile(userId,
                    etFirstName.text.toString(),
                    etLastName.text.toString())
        }
        profile_image.onClick {
            actionSelectOrTakeFile()
        }

    }

    // Untuk menyimpan foto
    private var uri: Uri? = null
    var filePath: String? = null
    private var file: File? = null
    lateinit var fileName: String

    override fun onPickResult(result: PickResult?) {
        // photo
        uri = result?.getUri()
        Log.d("logUriContent", uri.toString())
        filePath = getRealPathFromURIPath(uri)
        // TODO: Create File
        file = File(filePath)
        // TODO: Get File Name
        fileName = filePath?.substring(filePath?.lastIndexOf("/")!! +1).toString()
        //fileName?.let { tvDocumentName.text = it }

        presenter.updatePhotoProfile(file, fileName, intent.getStringExtra(MyConstants.PREF_USER_ID))
    }

    private fun getRealPathFromURIPath(contentURI: Uri?): String? {
        var cursor = this.contentResolver.query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI?.getPath()
        } else {
            cursor.moveToFirst()
            var idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }

    override fun showUpdatePhotoProfile(url: String) {
        Picasso.with(this).load(url).into(profile_image)
    }

    private fun initDataFromIntent() {
        etEmail.setText(intent.getStringExtra(MyConstants.PREF_EMAIL))
        etFirstName.setText(intent.getStringExtra(MyConstants.PREF_FIRST_NAME))
        etLastName.setText(intent.getStringExtra(MyConstants.PREF_LAST_NAME))
        this.showUpdatePhotoProfile(intent.getStringExtra(MyConstants.PREF_PHOTO_PROFILE))
        // toast("hahahha" + intent.getStringExtra(Const().PREF_PHOTO_PROFILE))
        //toast()

    }
    private fun actionSelectOrTakeFile() {
        PickImageDialog.build(PickSetup()).show(this@EditProfileActivity)
    }
    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        btnUpdateProfile.isEnabled = false
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        btnUpdateProfile.isEnabled = true
    }

    override fun showMessage(message: String) {
        toast(message)
    }
}
