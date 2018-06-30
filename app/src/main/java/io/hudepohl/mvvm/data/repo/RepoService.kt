package io.hudepohl.mvvm.data.repo

import io.hudepohl.mvvm.data.repo.model.Repo
import io.reactivex.Observable

class RepoService(private val api: RepoAPI) {

    fun fetchReposByUsername(username: String): Observable<List<Repo>> =
            api.getUserRepos(username)
                    .toObservable()
                    .map { res ->
                        when {
                            res.isSuccessful -> res.body() ?: emptyList()
                            else -> {
                                // TODO: Implement more powerful error handling.
                                emptyList()
                            }
                        }
                    }
}