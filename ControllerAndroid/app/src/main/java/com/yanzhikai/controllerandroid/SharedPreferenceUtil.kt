package com.yanzhikai.controllerandroid

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SharedPreferenceUtil {
    private const val APP_SP_NAME = "controller_sp"

    private const val IP_ADDRESS = "ip_address"

    private fun getAppSP(): SharedPreferences {
        return GobalApplication.sContext.getSharedPreferences(APP_SP_NAME, MODE_PRIVATE)
    }

    fun saveIpAddress(address: String) {
        val preferences = getAppSP()
        val editor = preferences.edit()
        editor.putString(IP_ADDRESS, address)
        editor.apply()
    }

    fun getIpAddress(): String? {
        return getAppSP().getString(IP_ADDRESS, null)
    }
}