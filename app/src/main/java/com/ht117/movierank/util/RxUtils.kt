package com.ht117.movierank.util


import io.reactivex.disposables.Disposable

/**
 * @author Quang Pham
 */
object RxUtils {
    fun unsubscribe(subscription: Disposable?) {
        if (subscription != null && !subscription.isDisposed) {
            subscription.dispose()
        } // else subscription doesn't exist or already unsubscribed
    }

    fun unsubscribe(vararg subscriptions: Disposable) {
        for (subscription in subscriptions) {
            unsubscribe(subscription)
        }
    }
}
