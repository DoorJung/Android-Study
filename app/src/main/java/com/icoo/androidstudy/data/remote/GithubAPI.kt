package com.icoo.androidstudy.data.remote

import com.icoo.androidstudy.data.model.GithubRepo
import io.reactivex.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface GithubAPI {
    @GET("users/{owner}/repos")
    fun getRepos(
        @Path("owner") owner: String
    ):  Observable<ArrayList<GithubRepo>>

    companion object {
        fun create(): GithubAPI {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubAPI::class.java)
        }
    }
}