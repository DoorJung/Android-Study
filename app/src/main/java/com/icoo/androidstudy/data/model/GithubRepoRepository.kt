package com.icoo.androidstudy.data.model

import io.reactivex.Observable

interface GithubRepoRepository{
    fun getRepos(owner: String): Observable<ArrayList<GithubRepo>>
}