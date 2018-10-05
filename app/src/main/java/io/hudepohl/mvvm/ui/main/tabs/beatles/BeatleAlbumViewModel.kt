package io.hudepohl.mvvm.ui.main.tabs.beatles

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import io.hudepohl.mvvm.BR
import io.hudepohl.mvvm.R
import io.hudepohl.mvvm.data.beatles.BeatlesService
import io.hudepohl.mvvm.data.beatles.model.BeatleAlbum
import io.hudepohl.mvvm.ui.BaseViewModel
import io.hudepohl.mvvm.util.NetworkAvailability
import io.hudepohl.mvvm.util.NetworkAvailabilityObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BeatleAlbumViewModel @Inject constructor(
    app: Application,
    private val beatlesService: BeatlesService,
    private val network: NetworkAvailabilityObserver
) : BaseViewModel(app) {

    private val albumData = MutableLiveData<List<BeatleAlbum>>()
    private val selectedAlbum = MutableLiveData<BeatleAlbum>()

    sealed class MobileNavEvent {
        class AlbumClick
    }
    val mobileNavEvents = MutableLiveData<MobileNavEvent>()

    override fun setup() {

        network
            .getNetworkAvailability()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .listenOnViewModel { networkUpdate ->
                when (networkUpdate) {
                    NetworkAvailability.AVAILABLE -> {
                        if (albumData.value == null) retrieveBeatleAlbums()
                    }
                    else -> {
                        errorMessages.value = getApp().getString(R.string.error_offline)
                    }
                }
            }
    }

    private fun retrieveBeatleAlbums() {

        beatlesService
            .getBeatleAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .listenOnViewModel(
                { albumList ->
                    updateAlbumData(albumList)
                },
                { _ ->
                    errorMessages.value = getApp().getString(R.string.error_generic)
                })
    }

    private fun updateAlbumData(albums: List<BeatleAlbum>) {
        albumData.value = albums
        notifyPropertyChanged(BR.albums)
    }

    fun albumClickMobile(album: BeatleAlbum) {

    }

    fun albumClickTablet(album: BeatleAlbum) {

    }

    @Bindable
    fun getAlbums() = when (albumData.value) {
        null -> emptyList()
        else -> albumData.value
    }

    @Bindable
    fun getSelectedAlbum() = selectedAlbum.value

    @Bindable
    fun isAlbumSelected(): Boolean = selectedAlbum.value != null
}