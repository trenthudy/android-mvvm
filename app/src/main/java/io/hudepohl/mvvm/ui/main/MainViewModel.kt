package io.hudepohl.mvvm.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import io.hudepohl.mvvm.R
import io.hudepohl.mvvm.data.beatles.BeatlesService
import io.hudepohl.mvvm.data.beatles.model.BeatleAlbum
import io.hudepohl.mvvm.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel @Inject constructor(
        app: Application,
        private val beatlesService: BeatlesService
) : BaseViewModel(app) {

    val albumData = MutableLiveData<List<BeatleAlbum>>()

    override fun setup() {

        beatlesService
                .getBeatleAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .listenOnViewModel(
                        { albums ->
                            albumData.value = albums
                        },
                        { error ->
                            errorMessages.value = if (error.message != null) {
                                error.message
                            } else {
                                getApp().getString(R.string.error_generic)
                            }
                        })
    }
}