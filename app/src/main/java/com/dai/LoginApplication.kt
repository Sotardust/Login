package com.dai

import android.app.Application
import com.mob.MobSDK

/**
 * Created by dai on 2017/8/29.
 */
class LoginApplication : Application() {

    private var instance: LoginApplication? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        MobSDK.init(this, "1cbe3c3ed61d0", "a866979838af5e8633e87cc9542d43ec")
    }

    fun getInstance() = instance
}