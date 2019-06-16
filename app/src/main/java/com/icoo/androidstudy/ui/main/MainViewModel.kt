package com.icoo.androidstudy.ui.main

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.icoo.androidstudy.base.BaseViewModel
import com.icoo.androidstudy.data.model.GithubRepo
import com.icoo.androidstudy.data.model.GithubRepoRepository
import com.icoo.androidstudy.data.remote.GithubAPI
import com.icoo.androidstudy.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val repository: GithubRepoRepository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val _isProgress = MutableLiveData<Int>().apply { postValue(View.GONE) }
    val isProgress: LiveData<Int> get() = _isProgress

    private val _repos = MutableLiveData<ArrayList<GithubRepo>>()
    val repos: LiveData<ArrayList<GithubRepo>> get() = _repos

    private var name: String = ""
        get() = if (field.isEmpty()) "MVVM" else field

    fun getRepos() {
        addDisposable(
            repository.getRepos(name)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.mainThread())
                .doOnSubscribe { showProgress() }
                .doOnTerminate { hideProgress() }
                .subscribe({
                    //API를 통해 액세스 토큰을 정상적으로 받았을 때 처리할 작업을 구현합니다.
                    //작업 중 오류가 발생하면 이 블록은 호출되지 않습니다.
                    it.run {
                        _repos.postValue(this)
                    }
                }, {
                    //에러 블록
                    //네트워크 오류나 데이터 처리 오류 등
                    //작업이 정상적으로 완료되지 않았을때 호출
                    Log.d(TAG, it.message)
                })
        )
    }

    fun onQueryChange(query: CharSequence) {
        this.name = query.toString()
    }

    private fun showProgress() {
        _isProgress.value = View.VISIBLE
    }

    private fun hideProgress() {
        _isProgress.value = View.INVISIBLE
    }

    companion object {
        private val TAG = "MainViewModel"
    }
}