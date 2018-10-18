package com.khilman.ecommerceudacoding.activities.edit_shipping_address

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.SessionManager
import com.khilman.ecommerceudacoding.utils.hide
import com.khilman.ecommerceudacoding.utils.show
import kotlinx.android.synthetic.main.activity_edit_shipping_address.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class EditShippingAddressActivity : AppCompatActivity(), EditShippingAddressView {


    private var shippingAddressId: String? = null
    //private var userId: String? = null

    private lateinit var pref: SharedPreferences
    private lateinit var presenter: EditShippingAddressPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_shipping_address)

        //todo: init component
        pref = SessionManager(this).getInstance()
        presenter = EditShippingAddressPresenter(this, EditShippingAddressInteractor())

        //todo: get data from intent
        shippingAddressId = intent.getStringExtra(MyConstants.ADDRESS_ID)
        val isMainAddress = intent.getStringExtra(MyConstants.ADDRESS_IS_MAIN_ADDRESS)

        if (isMainAddress.equals("1")){
            cbIsMainShippingAddress.isChecked = true
        } else {
            cbIsMainShippingAddress.isChecked = false
        }

        //todo: set data
        etAddressTitle.setText(intent.getStringExtra(MyConstants.ADDRESS_TITLE))
        etCity.setText(intent.getStringExtra(MyConstants.ADDRESS_CITY))
        etProvince.setText(intent.getStringExtra(MyConstants.ADDRESS_PROVINCE))
        etZipCode.setText(intent.getStringExtra(MyConstants.ADDRESS_ZIP_CODE))
        etFullAddress.setText(intent.getStringExtra(MyConstants.ADDRESS_FULL_ADDRESS))

        Log.d("isChecked", cbIsMainShippingAddress.isChecked.toString())

//        //todo: update
        btnUpdateShippingAddress.onClick {

            val maiShippingAddress = if (cbIsMainShippingAddress.isChecked) "1" else "0"
            Log.d("isChecked", cbIsMainShippingAddress.isChecked.toString())
            Log.d("isChecked", maiShippingAddress)
//
//            //todo: trigger function in presenter
            presenter.updateShippingAdrress(
                    shippingAddressId,
                    etAddressTitle.text.toString(),
                    etCity.text.toString(),
                    etProvince.text.toString(),
                    etZipCode.text.toString(),
                    etFullAddress.text.toString(),
                    maiShippingAddress)
        }
    }

    override fun showProgress() {
        progressBar.show()
        btnUpdateShippingAddress.isEnabled = false
    }

    override fun hideProgress() {
        progressBar.hide()
        btnUpdateShippingAddress.isEnabled = true
    }

    override fun showMessage(message: String) {
        toast(message)
    }
}
