package com.icoo.androidstudy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.icoo.androidstudy.R
import com.icoo.androidstudy.data.remote.GithubAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //CompositeDisposable 클래스를 이용하면 생성된 모든 Observable 을 안드로이드 라이프사이클에 맞춰 한번에 모두 해제할 수 있다.
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        act_main_bt_search.setOnClickListener {
            getRepos(act_main_et_name.text.toString())
        }
    }

    //onDestroy 라이프사이클에 맞춰 한번에 모두 해제함
    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun getRepos(owner: String) {
        compositeDisposable.add(
            GithubAPI
                .create()
                .getRepos(owner)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //구독할 때 수행할 작업
                .doOnSubscribe { act_main_pb.visibility = View.VISIBLE }
                //스트림이 종료될 때 수행할 작업
                .doOnTerminate { act_main_pb.visibility = View.GONE }
                .subscribe({
                    //API를 통해 액세스 토큰을 정상적으로 받았을 때 처리할 작업을 구현합니다.
                    //작업 중 오류가 발생하면 이 블록은 호출되지 않습니다.
                    act_main_rv_repos?.apply {
                        adapter = RepoRecyclerViewAdapter(this@MainActivity, it)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }, {
                    //에러 블록
                    //네트워크 오류나 데이터 처리 오류 등
                    //작업이 정상적으로 완료되지 않았을때 호출
                })
        )
    }
}
