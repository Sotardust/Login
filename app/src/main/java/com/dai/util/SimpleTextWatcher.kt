package com.dai.util

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by dai on 2017/8/29.
 */
abstract class SimpleTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}