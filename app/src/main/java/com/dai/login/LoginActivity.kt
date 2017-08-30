package com.dai.login

import android.os.Bundle
import android.view.LayoutInflater
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.dai.BaseActivity
import com.dai.R
import com.dai.util.toast


class LoginActivity : BaseActivity() {

    private var eventHandler: EventHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LayoutInflater.from(this).inflate(R.layout.activity_login, null)
        setContentView(root)
        eventHandler = object : EventHandler() {
            override fun afterEvent(event: Int, result: Int, data: Any) {
                if (data is Throwable) {
                    val msg = data.message
                    applicationContext.toast(msg as String)
                }
            }
        }
        SMSSDK.registerEventHandler(eventHandler)

        val manager = LoginManagement(applicationContext, root)
        manager.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        SMSSDK.unregisterEventHandler(eventHandler)
    }
}
