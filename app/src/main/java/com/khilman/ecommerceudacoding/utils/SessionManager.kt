package com.khilman.ecommerceudacoding.utils

import android.content.Context
import android.content.SharedPreferences
import com.khilman.ecommerceudacoding.activities.intro.IntroActivity

class SessionManager(val context: Context) {

    fun getInstance(): SharedPreferences {
        val pref = context.getSharedPreferences(MyConstants.PREF_NAME, 0)
        return pref
    }
}