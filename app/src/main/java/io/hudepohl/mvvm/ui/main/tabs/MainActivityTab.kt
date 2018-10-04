package io.hudepohl.mvvm.ui.main.tabs

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import io.hudepohl.mvvm.R
import io.hudepohl.mvvm.ui.BaseFragment
import io.hudepohl.mvvm.ui.main.tabs.beatles.BeatleAlbumFragment

enum class MainActivityTab(
    @IdRes val menuItemId: Int,
    @StringRes val title: Int,
    @LayoutRes val layout: Int,
    val instance: () -> BaseFragment
) {

    BEATLES(
        R.id.navigation_item_beatles,
        R.string.navigation_tab_beatles,
        R.layout.content_beatle_album,
        { BeatleAlbumFragment() }),

    GITHUB(
        R.id.navigation_item_github,
        R.string.navigation_tab_github,
        R.layout.content_github_repo,
        { GitHubRepoFragment() }
    )
}