package com.dai.login

/**
 * 登录界面操作
 */
interface LoginListener {
    fun login(code: String)
    fun obtainCode(number: String)
}
