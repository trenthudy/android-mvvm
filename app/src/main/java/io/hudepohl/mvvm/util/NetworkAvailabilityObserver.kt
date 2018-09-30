package io.hudepohl.mvvm.util

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import io.reactivex.Observable


class NetworkAvailabilityObserver(val app: Application, val connectivityManager: ConnectivityManager) {

    fun getNetworkAvailability(): Observable<NetworkAvailability> =
            Observable.create { emitter ->
                val receiver: BroadcastReceiver = object : BroadcastReceiver() {
                    override fun onReceive(context: Context, intent: Intent) {
                        if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)) {
                            emitter.onNext(NetworkAvailability.NOT_AVAILABLE)
                        } else {
                            val network = connectivityManager.activeNetworkInfo
                            if (network != null && network.isConnected) {
                                emitter.onNext(NetworkAvailability.AVAILABLE)
                            } else {
                                emitter.onNext(NetworkAvailability.NOT_AVAILABLE)
                            }
                        }
                    }
                }

                emitter.setCancellable { app.unregisterReceiver(receiver) }
                app.registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            }
}