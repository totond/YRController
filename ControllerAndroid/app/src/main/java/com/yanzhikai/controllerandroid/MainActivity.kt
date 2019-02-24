package com.yanzhikai.controllerandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_control.setOnClickListener { jumpToControl() }
    }

    private fun jumpToControl(){
        ActivityJumpUtil.jumpToActivity(this,ControllerActivity::class.java)
    }

}
