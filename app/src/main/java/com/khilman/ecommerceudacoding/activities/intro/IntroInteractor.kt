package com.khilman.ecommerceudacoding.activities.intro

import android.graphics.Color
import com.github.paolorotolo.appintro.model.SliderPage
import com.khilman.ecommerceudacoding.R

class IntroInteractor {

    interface OnGetIntroSliderDataFinished {
        fun onSuccessGetData(sliderData: MutableList<SliderPage>)
        fun onErrorGetData(message: String)
    }

    fun getIntroSliderData(listener: OnGetIntroSliderDataFinished) {
        val sliderData = mutableListOf<SliderPage>()

        val slider1 = SliderPage()
        slider1.title = "Discover"
        slider1.description = "Explore worlds top brands and boutiques"
        slider1.imageDrawable = R.drawable.discover_intro
        slider1.bgColor = Color.parseColor("#FCBA12")

        val slider2 = SliderPage()
        slider2.title = "Big Deals"
        slider2.description = "Get the best deals every day"
        slider2.imageDrawable = R.drawable.big_deals_intro
        slider2.bgColor = Color.parseColor("#448D76")

        val slider3 = SliderPage()
        slider3.title = "Enjoy your shopping"
        slider3.description = "Get hight quality products for the best prices"
        slider3.imageDrawable = R.drawable.shopping_intro
        slider3.bgColor = Color.parseColor("#AE0D7A")

        sliderData.add(slider1)
        sliderData.add(slider2)
        sliderData.add(slider3)

        if (sliderData.size > 0){
            listener.onSuccessGetData(sliderData)
        } else {
            listener.onErrorGetData("Data slider kosong !")
        }
    }
}