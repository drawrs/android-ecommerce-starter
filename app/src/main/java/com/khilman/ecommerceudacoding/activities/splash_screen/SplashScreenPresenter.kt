package com.khilman.ecommerceudacoding.activities.splash_screen

import android.content.SharedPreferences
import android.os.Handler
import com.khilman.ecommerceudacoding.utils.MyConstants

class SplashScreenPresenter(val view: SplashScreenView, val pref: SharedPreferences) {


    fun startTimer(interval: Long){
        val handler = Handler()
        handler.postDelayed(Runnable {
            //If user haven't seen the intro
            if (!this.isIntroViewed()){
                view.navigateToIntroView()
            } else {
                if (isUserAuthenticated()){
                    view.navigateToLogin()
                } else {
                    view.navigateToRegister()
                }
            }
        }, interval)
    }
    fun isUserAuthenticated(): Boolean {
        return pref!!.getBoolean(MyConstants.PREF_IS_LOGGINED, false)
    }
    fun isIntroViewed(): Boolean {
        return pref!!.getBoolean(MyConstants.PREF_IS_INTRO_VIEWED, false)
    }
}