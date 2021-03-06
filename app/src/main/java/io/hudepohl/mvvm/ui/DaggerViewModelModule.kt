package io.hudepohl.mvvm.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.hudepohl.mvvm.ui.main.MainViewModel
import io.hudepohl.mvvm.ui.main.tabs.beatles.BeatleAlbumViewModel

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindDaggerViewModelFactory(daggerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BeatleAlbumViewModel::class)
    abstract fun bindBeatleAlbumViewModel(beatleAlbumViewModel: BeatleAlbumViewModel): ViewModel
}