package com.dai.util

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable


/**
 * Created by dai on 2017/8/29.
 */
abstract class Observer<T> :SingleObserver<T>{
    override fun onSuccess(t: T) {
    }

    override fun onError(e: Throwable) {
    }

    override fun onSubscribe(d: Disposable) {
    }
}