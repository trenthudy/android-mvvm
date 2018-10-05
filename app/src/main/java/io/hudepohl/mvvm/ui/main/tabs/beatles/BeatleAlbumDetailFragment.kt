package io.hudepohl.mvvm.ui.main.tabs.beatles

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.hudepohl.mvvm.R
import io.hudepohl.mvvm.ui.BaseFragment

class BeatleAlbumDetailFragment : BaseFragment() {

    private lateinit var viewModel: BeatleAlbumViewModel
    private lateinit var binding: BeatleAlbumDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = getViewModel(BeatleAlbumViewModel::class, ViewModelScope.FRAGMENT)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beatle_album_detail, container, false)
        binding.vm = viewModel

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        viewModel.onResume()
        super.onResume()
    }
}