package io.hudepohl.mvvm.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import android.support.annotation.CallSuper
import io.hudepohl.mvvm.app.BaseApplication
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.atomic.AtomicBoolean


abstract class BaseViewModel(app: Application) : AndroidViewModel(app), Observable {

    private var initialized: AtomicBoolean = AtomicBoolean(false)
    private val registry: PropertyChangeRegistry = PropertyChangeRegistry()
    private val disposables: CompositeDisposable = CompositeDisposable()
    val errorMessages: MutableLiveData<String> = MutableLiveData()

    fun onResume() = if (initialized.compareAndSet(false, true)) setup() else {}

    protected abstract fun setup()

    protected fun getApp() = getApplication<BaseApplication>()

    @CallSuper
    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) = synchronized(this) { registry.add(callback) }
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) = synchronized(this) { registry.remove(callback) }
    fun notifyChange() = synchronized(this) { registry.notifyCallbacks(this, 0, null) }
    fun notifyPropertyChanged(fieldId: Int) = synchronized(this) { registry.notifyCallbacks(this, fieldId, null) }

    protected fun <T> Single<T>.listenOnViewModel(
            onSuccess: (T) -> Unit
    ): Disposable = this.subscribe(onSuccess).also { disposables.add(it) }
    protected fun <T> Single<T>.listenOnViewModel(
            onSuccess: (T) -> Unit,
            onError: (Throwable) -> Unit
    ): Disposable = this.subscribe(onSuccess, onError).also { disposables.add(it) }
}