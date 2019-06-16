package com.icoo.androidstudy.ui.main

import android.os.Bundle
import com.icoo.androidstudy.BR
import com.icoo.androidstudy.R
import com.icoo.androidstudy.base.BaseActivity
import com.icoo.androidstudy.base.BaseRecyclerViewAdapter
import com.icoo.androidstudy.data.model.GithubRepo
import com.icoo.androidstudy.databinding.ActivityMainBinding
import com.icoo.androidstudy.databinding.ItemRepoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResID: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel

        viewDataBinding.actMainRvRepos.adapter =
            object : BaseRecyclerViewAdapter<GithubRepo, ItemRepoBinding>() {
                override val layoutResID: Int
                    get() = R.layout.item_repo
                override val bindingVariableId: Int
                    get() = BR.repo
                override val listener: OnItemClickListener?
                    get() = null
            }

        viewDataBinding.actMainBtSearch.setOnClickListener {
            viewModel.getRepos()
        }
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
