package com.dai

import android.app.Application
import android.content.Context
import com.dai.util.OkHttpUtil
import com.mob.MobSDK

/**
 * Created by dai on 2017/8/29.
 */
class LoginApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        OkHttpUtil().setContext(applicationContext)
        MobSDK.init(this, "1cbe3c3ed61d0", "a866979838af5e8633e87cc9542d43ec")
    }
}