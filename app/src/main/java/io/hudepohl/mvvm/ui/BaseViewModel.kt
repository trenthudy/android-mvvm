package io.hudepohl.mvvm.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.atomic.AtomicBoolean


abstract class BaseViewModel(app: Application) : AndroidViewModel(app), Observable {

    private var initialized: AtomicBoolean = AtomicBoolean(false)
    protected val disposables: CompositeDisposable = CompositeDisposable()

    fun onResume() = if (initialized.compareAndSet(false, true)) setup() else {}

    abstract fun setup()

    @CallSuper
    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    // databinding helpers
    private val registry: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) = synchronized(this) { registry.add(callback) }
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) = synchronized(this) { registry.remove(callback) }
    fun notifyChange() = synchronized(this) { registry.notifyCallbacks(this, 0, null) }
    fun notifyPropertyChanged(fieldId: Int) = synchronized(this) { registry.notifyCallbacks(this, fieldId, null) }

    // rx helpers
    // TODO: Add RxJava helper functions...
}