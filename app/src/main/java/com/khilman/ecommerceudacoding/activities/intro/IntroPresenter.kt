package com.khilman.ecommerceudacoding.activities.intro

import android.content.SharedPreferences
import com.github.paolorotolo.appintro.model.SliderPage
import com.khilman.ecommerceudacoding.utils.MyConstants

class IntroPresenter(val view: IntroView, val interactor: IntroInteractor): IntroInteractor.OnGetIntroSliderDataFinished {

    fun getSliderData() {
        interactor.getIntroSliderData(this)
    }
    fun setIntroHasViewed(pref: SharedPreferences){
        pref.edit().putBoolean(MyConstants.PREF_IS_INTRO_VIEWED, true).commit()
        view?.navigateToLogin()
    }
    override fun onSuccessGetData(sliderData: MutableList<SliderPage>) {
        view?.showSlider(sliderData)
    }

    override fun onErrorGetData(message: String) {
        view?.showMessage(message)
    }
}