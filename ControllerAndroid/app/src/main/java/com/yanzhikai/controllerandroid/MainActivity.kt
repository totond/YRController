package com.yanzhikai.controllerandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentIP: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {
        btn_control.setOnClickListener { jumpToControl() }
        btn_connect.setOnClickListener { connect() }
        et_ip.hint = SharedPreferenceUtil.getIpAddress()
        setState(ConnectManager.state)
    }

    fun connect() {
        currentIP = et_ip.text.toString()
        ConnectManager.getInstance().setConnectListener(mConnectListener)
        ConnectManager.getInstance().configure(currentIP, 9998)
        ConnectManager.getInstance().init()
    }

    private fun setState(state: Int) {
        Log.i("yjk", "state: $state Thread: " + Thread.currentThread().name)
        when (state) {
            STATE_CONNECTED -> {
                btn_control.isEnabled = true
                btn_connect.isEnabled = false
                et_ip.isEnabled = false
                tv_state.text = "已连接"
            }
            STATE_DISCONNECT -> {
                btn_control.isEnabled = false
                btn_connect.isEnabled = true
                et_ip.isEnabled = true
                tv_state.text = "未连接"
            }
        }
    }

    private fun jumpToControl() {
        ActivityJumpUtil.jumpToActivity(this, ControllerActivity::class.java)
    }

    private val mConnectListener = object : ConnectManager.ConnectListener {
        override fun onSocketChanged(state: Int) {
            SharedPreferenceUtil.saveIpAddress(currentIP ?: "")
            setState(state)
        }

    }
}
