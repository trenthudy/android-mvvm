package io.hudepohl.mvvm.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

    protected val disposables by lazy { CompositeDisposable() }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}