package com.khilman.ecommerceudacoding.activities.intro

import com.github.paolorotolo.appintro.model.SliderPage

interface IntroView {
    fun showSlider(sliderData: MutableList<SliderPage>)
    fun showMessage(message: String)
    fun navigateToLogin()
}