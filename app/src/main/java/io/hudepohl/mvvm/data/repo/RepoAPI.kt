package io.hudepohl.mvvm.data.repo

import io.hudepohl.mvvm.data.repo.model.Repo
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoAPI {

    @GET("/users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Single<Response<List<Repo>>>
}