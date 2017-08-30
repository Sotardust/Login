package com.dai.login

import android.content.Context
import android.text.TextUtils
import android.view.View
import cn.smssdk.SMSSDK
import com.dai.R
import com.dai.util.Observer
import com.dai.util.OkHttpUtil
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
        LoginUI(context, root, this, this).findViews();
    }

    /**
     * 登录
     */
    override fun login(code: String) {
        val url = "";
        val json = JSONObject()
        json.put("code", code);
        println("code = $code")
        OkHttpUtil().getSinglePostRequest(url, json)
                .subscribe(object : Observer<String>() {
                    override fun onSuccess(t: String) {
                        super.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        println("e.toString() = ${e.toString()}")
                        context.toast(context.getString(R.string.login_failure))
                    }
                })
    }

    /**
     * 获取验证码
     */
    override fun obtainCode(number: String) {
        SMSSDK.getVerificationCode("86", number);
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
}

