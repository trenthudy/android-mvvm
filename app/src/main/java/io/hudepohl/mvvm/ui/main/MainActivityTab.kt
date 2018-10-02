package io.hudepohl.mvvm.ui.main

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import io.hudepohl.mvvm.R
import io.hudepohl.mvvm.ui.BaseFragment
import io.hudepohl.mvvm.ui.main.beatles.BeatleAlbumFragment

enum class MainActivityTab(
    @IdRes val menuItemId: Int,
    @StringRes val title: Int,
    val instance: () -> BaseFragment
) {

    BEATLES(
        R.id.navigation_item_beatles,
        R.string.navigation_tab_beatles,
        { BeatleAlbumFragment() }
    )
}