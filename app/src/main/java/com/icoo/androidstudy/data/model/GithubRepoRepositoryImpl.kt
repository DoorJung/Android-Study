package com.icoo.androidstudy.data.model

import com.icoo.androidstudy.data.remote.GithubAPI
import io.reactivex.Observable

class GithubRepoRepositoryImpl(val api: GithubAPI): GithubRepoRepository {
    override fun getRepos(owner: String): Observable<ArrayList<GithubRepo>> = api
        .getRepos(owner)
        .map { it }
}