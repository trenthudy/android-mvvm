package io.hudepohl.mvvm.data.repo

import io.hudepohl.mvvm.data.repo.model.Repo
import io.hudepohl.mvvm.util.Mockable
import io.hudepohl.mvvm.util.validateResponse
import io.reactivex.Single


@Mockable
class RepoService(private val api: RepoAPI) {

    fun fetchReposByUsername(username: String): Single<List<Repo>> =
            api.getUserRepos(username)
                    .flatMap { res -> validateResponse(res) }
}