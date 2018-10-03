package io.hudepohl.mvvm.ui.main.tabs.beatles

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.hudepohl.mvvm.R
import io.hudepohl.mvvm.data.beatles.model.BeatleAlbum
import io.hudepohl.mvvm.ui.BaseFragment
import io.hudepohl.mvvm.util.RecyclerViewAdapter

class BeatleAlbumFragment : BaseFragment() {

    private lateinit var viewModel: BeatleAlbumViewModel
    private lateinit var binding: BeatleAlbumFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = getViewModel(BeatleAlbumViewModel::class)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beatle_album, container, false)
        binding.vm = viewModel
        binding.albumList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AlbumAdapter()
        }
        binding.executePendingBindings()

        return binding.root
    }

    override fun onResume() {
        viewModel.onResume()
        super.onResume()
    }

    private inner class AlbumAdapter : RecyclerViewAdapter<BeatleAlbum, AlbumViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
            val inflater = LayoutInflater.from(activity)
            val binding = DataBindingUtil.inflate<BeatleAlbumItemBinding>(inflater, R.layout.item_album, parent, false)
            return AlbumViewHolder(binding)
        }

        override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
            val album = getItemAtPosition(position)
            holder.bind(album)
        }
    }

    private inner class AlbumViewHolder(
        val binding: BeatleAlbumItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: BeatleAlbum) {
            binding.album = album
            binding.executePendingBindings()
        }
    }
}