package com.wyq.kotlinjetpackdemo.ui.main.tab

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import com.wyq.kotlinjetpackdemo.R
import com.wyq.kotlinjetpackdemo.common.ArticleAdapter
import com.zs.base_library.base.DataBindingConfig
import com.zs.base_library.base.LazyVmFragment
import com.zs.base_library.common.smartConfig
import com.zs.base_library.common.smartDismiss
import com.wyq.kotlinjetpackdemo.utils.CacheUtil
import com.wyq.kotlinjetpackdemo.BR
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.loadingTip
import kotlinx.android.synthetic.main.fragment_home.smartRefresh

/**
 * des 文章列表fragment
 * @date 2020/7/7
 * @author zs
 */
class ArticleListFragment : LazyVmFragment() {

    private var articleVM: ArticleVM? = null

    /**
     * fragment类型，项目或公号
     */
    private var type = 0

    /**
     * tab的id
     */
    private var tabId = 0

    /**
     * 文章适配器
     */
    private val adapter by lazy { ArticleAdapter(mActivity) }

    override fun initViewModel() {
        articleVM = getFragmentViewModel(ArticleVM::class.java)
    }

    override fun observe() {
        articleVM?.articleLiveData?.observe(this, Observer {
            smartRefresh.smartDismiss()
            loadingTip.dismiss()
            adapter.submitList(it)
        })
        articleVM?.errorLiveData?.observe(this, Observer {
            smartRefresh.smartDismiss()
            if (it.errorCode == -100) {
                //显示网络错误
                loadingTip.showInternetError()
                loadingTip.setReloadListener {
                    articleVM?.getArticleList(type, tabId)
                }
            }
        })
    }

    override fun lazyInit() {
        type = arguments?.getInt("type") ?: 0
        tabId = arguments?.getInt("tabId") ?: 0
        initView()
        loadData()
    }

    override fun initView() {
        //关闭更新动画
        (rvArticleList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        //下拉刷新
        smartRefresh.setOnRefreshListener {
            articleVM?.getArticleList(type, tabId)
        }
        //上拉加载
        smartRefresh.setOnLoadMoreListener {
            articleVM?.loadMoreArticleList(type, tabId)
        }
        smartRefresh.smartConfig()
        adapter.apply {
            rvArticleList.adapter = this
            setOnItemClickListener { i, _ ->
                nav().navigate(
                    R.id.action_main_fragment_to_web_fragment,
                    this@ArticleListFragment.adapter.getBundle(i)
                )
            }
            setOnItemChildClickListener { i, view ->
                when (view.id) {
                    //收藏
                    R.id.ivCollect -> {
                        if (CacheUtil.isLogin()) {
                            this@ArticleListFragment.adapter.currentList[i].apply {
                                //已收藏取消收藏
                                if (collect) {
                                    articleVM?.unCollect(id)
                                } else {
                                    articleVM?.collect(id)
                                }
                            }
                        } else {
                            nav().navigate(R.id.action_main_fragment_to_login_fragment)
                        }
                    }
                }
            }
        }
    }

    override fun loadData() {
        articleVM?.getArticleList(type, tabId)
        loadingTip.loading()
    }

    override fun getLayoutId() = R.layout.fragment_article

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_article, articleVM)
            .addBindingParam(BR.vm, articleVM)
    }
}