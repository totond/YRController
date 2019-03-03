package com.yanzhikai.controllerandroid

import android.content.Context
import android.content.Intent

object ActivityJumpUtil {
    fun jumpToActivity(context: Context, target: Class<*>) {
        val intent = Intent(context, target)
        context.startActivity(intent)
    }
}
