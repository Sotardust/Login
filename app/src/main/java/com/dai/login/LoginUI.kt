package com.dai.login

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.dai.R
import com.dai.util.toast


/**
 * Created by dai on 2017/8/29.
 */
class LoginUI(val context: Context, val root: View, val listener: LoginListener, val manager: LoginManagement) : View.OnClickListener {

    private var number: EditText? = null
    private var obtainCode: TextView? = null
    private var login: TextView? = null
    private var inputCode: EditText? = null

    fun findViews() {
        number = root.findViewById(R.id.number) as EditText?
        inputCode = root.findViewById(R.id.input_code) as EditText?
        obtainCode = root.findViewById(R.id.obtain_code) as TextView?
        login = root.findViewById(R.id.login) as TextView?
        obtainCode?.setOnClickListener(this)
        login?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val numberData = number?.text.toString()
        when (v) {
            obtainCode -> {
                if (manager.judgeText(numberData, 1)) return
                if (manager.verifyNumber(numberData)) {
                    listener.obtainCode(numberData)
                    context.toast(context.getString(R.string.code_has_been_sent))
                } else {
                    context.toast(context.getString(R.string.please_input_correct_number))
                }
            }
            login -> {
                val codeData = inputCode?.text.toString()
                if (manager.judgeText(numberData, 1)) return
                if (manager.judgeText(codeData, 2)) return
                listener.login(codeData)
            }
        }
    }

}