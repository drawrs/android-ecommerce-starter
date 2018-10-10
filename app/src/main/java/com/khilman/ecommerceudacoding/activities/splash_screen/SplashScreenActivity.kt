package com.khilman.ecommerceudacoding.activities.splash_screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.intro.IntroActivity
import com.khilman.ecommerceudacoding.activities.login.LoginActivity
import com.khilman.ecommerceudacoding.activities.register.RegisterActivity
import com.khilman.ecommerceudacoding.utils.MyConstants
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity(), SplashScreenView {

    private lateinit var presenter: SplashScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val pref = getSharedPreferences(MyConstants.PREF_NAME, 0)
        presenter = SplashScreenPresenter(this, pref)

        //todo: start the timer
        presenter.startTimer(2000)
    }

    override fun navigateToIntroView() {
        startActivity<IntroActivity>()
    }

    override fun navigateToLogin() {
        startActivity<LoginActivity>()
    }

    override fun navigateToRegister() {
        startActivity<RegisterActivity>()
    }
}
