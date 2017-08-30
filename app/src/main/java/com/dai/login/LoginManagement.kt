package com.dai.login

import android.content.Context
import android.content.Intent
import android.support.annotation.StringRes
import android.text.TextUtils
import android.view.View
import cn.smssdk.SMSSDK
import com.dai.R
import com.dai.main.MainActivity
import com.dai.util.Observer
import com.dai.util.OkHttpUtil
import com.dai.util.TokenStorage
import com.dai.util.toast
import io.reactivex.disposables.Disposable
import org.json.JSONObject
import java.util.regex.Pattern

/**
 * 登录相关功能管理
 */
class LoginManagement(val context: Context, val root: View) : LoginListener {

    /**
     * 初始化
     */
    fun init() {
        LoginUI(context, root, this, this).findViews()
    }

    /**
     * 登录
     */
    override fun login(code: String) {

//        val url = ""
//        val json = JSONObject()
//        json.put("code", code)
//        OkHttpUtil().getSinglePostRequest(url,json)
//                .subscribe(object : Observer<String>() {
//                    override fun onSuccess(t: String) {
//                        super.onSuccess(t)
//                    }
//
//                    override fun onError(e: Throwable) {
//                        super.onError(e)
//                    }
//                })
        //服务器返回登录成功mock数据
        //parseData(R.string.mock_login_failure)
        parseData(R.string.mock_login_successful)
    }

    /**
     * 获取验证码
     */
    override fun obtainCode(number: String) {
        SMSSDK.getVerificationCode("86", number)
    }

    /**
     * 验证手机号
     */
    fun verifyNumber(string: String): Boolean {
        val p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$")
        val m = p.matcher(string)
        return m.matches()
    }

    /**
     *  判断输入框内容是否为空
     */
    fun judgeText(string: String, num: Int): Boolean {
        if (TextUtils.isEmpty(string)) {
            val message = if (num == 1) context.getString(R.string.phone_number_cannot_be_empty) else context.getString(R.string.verification_code_cannot_be_empty)
            context.toast(message)
            return true
        }
        return false
    }

    /**
     * 解析并处理服务器返回数据
     */
    fun parseData(@StringRes resId: Int) {
        val value = context.getString(resId)
        val json = JSONObject(value)
        val success = json.getInt("success")
        when (success) {
            0 -> {
                val error = json.getString("error")
                context.toast(error)
            }
            else -> {
                val token = json.getString("token")
                TokenStorage(context).setToken(token!!)
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}

