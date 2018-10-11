package com.khilman.ecommerceudacoding.activities.login

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.home.HomeActivity
import com.khilman.ecommerceudacoding.activities.register.RegisterActivity
import com.khilman.ecommerceudacoding.network.model.login_response.Data
import com.khilman.ecommerceudacoding.utils.MyHelpers
import com.khilman.ecommerceudacoding.utils.SessionManager
import com.khilman.ecommerceudacoding.utils.hide
import com.khilman.ecommerceudacoding.utils.show
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var presenter: LoginPresenter
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        pref = SessionManager(this).getInstance()
        presenter = LoginPresenter(this, LoginInteractor(), pref)

        initEvent()
    }

    private fun initEvent() {
        viewRoot.onClick {
            MyHelpers().hideSoftKeyboard(this@LoginActivity)
        }
        btnDoSignIn.onClick {
            presenter.attempUserLogin(etEmailLogin.text.toString(),
                    etPasswordLogin.text.toString())
        }
        tvDoSignUp.onClick {
            startActivity<RegisterActivity>()
        }
    }

    override fun onLoginSuccess(userProfile: Data?) {
        presenter.saveLoginStateToCache(cbRememberMe.isChecked)
        presenter.saveUserProfileToCache(userProfile)
    }
    override fun navigateToHome() {
        startActivity<HomeActivity>()
    }

    override fun hideProgress() {
        progressBar.hide()
    }

    override fun showProgress() {
        progressBar.show()
    }

    override fun showMessage(message: String?) {
        toast(message!!)
    }
}
