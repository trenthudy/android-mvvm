package io.hudepohl.mvvm.ui.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.hudepohl.mvvm.R
import io.hudepohl.mvvm.ui.BaseFragment

class GitHubRepoFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_github_repo, container, false)
    }
}