package com.dai.util

import android.content.Context
import android.widget.Toast

/**
 * Created by dai on 2017/8/29.
 */

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
