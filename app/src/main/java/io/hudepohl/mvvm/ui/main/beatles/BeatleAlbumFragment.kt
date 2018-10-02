package io.hudepohl.mvvm.ui.main.beatles

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.hudepohl.mvvm.ui.BaseFragment

class BeatleAlbumFragment : BaseFragment() {

    private lateinit var viewModel: BeatleAlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = getViewModel(BeatleAlbumViewModel::class)
        viewModel.albums.observe(this, Observer { albums ->
            albums?.let {


            }
        })


        return super.onCreateView(inflater, container, savedInstanceState)
    }
}