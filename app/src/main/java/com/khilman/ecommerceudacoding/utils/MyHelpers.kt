package com.khilman.ecommerceudacoding.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MyHelpers {
    fun hideSoftKeyboard(activity: Activity) {
        if (activity.currentFocus != null){
            val inputMethodManager = activity.getSystemService(
                    Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                    activity.currentFocus!!.windowToken, 0)
        }
    }

    fun rupiahFormat(number: Long): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

        return formatRupiah.format(number)
    }

    fun dateFormat(date: String?): String {
        //2018-10-04 17:41:17
        val localeID = Locale("in", "ID")
        val formatIncoming = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localeID)
        val formatOutgoing = SimpleDateFormat("EEE, dd/MM/yyyy")
        val tz = TimeZone.getTimeZone("Asia/Jakarta")

        formatOutgoing.setTimeZone(tz)

        val s = formatOutgoing.format(formatIncoming.parse("2018-10-04 17:41:17"))

        return s
    }
}