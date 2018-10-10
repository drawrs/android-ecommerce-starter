package com.khilman.ecommerceudacoding.activities.intro

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.model.SliderPage
import com.khilman.ecommerceudacoding.activities.login.LoginActivity
import com.khilman.ecommerceudacoding.utils.SessionManager
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class IntroActivity : AppIntro(), IntroView {

    private lateinit var presenter: IntroPresenter
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = SessionManager(this).getInstance()

        presenter = IntroPresenter(this, IntroInteractor())
        presenter.getSliderData()
    }

    override fun showSlider(sliderData: MutableList<SliderPage>) {
        sliderData.forEachIndexed { index, sliderPage ->
            addSlide(AppIntroFragment.newInstance(sliderPage))
        }
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        presenter.setIntroHasViewed(pref)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        presenter.setIntroHasViewed(pref)
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun navigateToLogin() {
        startActivity<LoginActivity>()
    }

}
