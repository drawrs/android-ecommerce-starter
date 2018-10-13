package com.khilman.ecommerceudacoding.activities.register

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.login.LoginActivity
import com.khilman.ecommerceudacoding.network.model.register_response.Data
import com.khilman.ecommerceudacoding.utils.MyHelpers
import com.khilman.ecommerceudacoding.utils.hide
import com.khilman.ecommerceudacoding.utils.show
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity(), RegisterView {


    private lateinit var presenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisterPresenter(this, RegisterInteractor())
        initEvent()
    }

    private fun initEvent() {
        viewRoot.onClick {
            MyHelpers().hideSoftKeyboard(this@RegisterActivity)
        }
        btnDoSignUp.onClick {
            presenter.registerUser(etEmail.text.toString(),
                    etPassword.text.toString(),
                    etPasswordConfirm.text.toString(),
                    etFirstName.text.toString(),
                    etLastName.text.toString())
        }
        tvDoSignIn.onClick {
            startActivity<LoginActivity>()
        }
    }

    override fun onRegisterSuccess(userProfile: Data?, message: String?) {
        toast(message!!)
        startActivity<LoginActivity>()
        finish()
    }

    override fun onRegisterFailed(message: String?) {
        toast(message!!)
    }
    override fun showProgress() {
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
    }
}
