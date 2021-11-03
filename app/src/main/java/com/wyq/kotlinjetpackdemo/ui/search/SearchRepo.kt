package com.wyq.kotlinjetpackdemo.ui.search

import com.wyq.kotlinjetpackdemo.bean.ArticleListBean
import com.wyq.kotlinjetpackdemo.http.ApiService
import com.wyq.kotlinjetpackdemo.http.RetrofitManager
import com.zs.base_library.base.BaseRepository


/**
 * @author zs
 * @data 2020/7/11
 */
class SearchRepo: BaseRepository() {

    /**
     * 页码
     */
    private var page = 0

    /**
     * 搜索
     */
    suspend fun search(keyWord: String) = withIO {
        page = 0
        RetrofitManager.getApiService(ApiService::class.java)
            .search(page,keyWord)
            .data()
            .let {
                ArticleListBean.trans(it.datas?: mutableListOf())
            }
    }

    suspend fun loadMore(keyWord: String) = withIO {
        page++
        RetrofitManager.getApiService(ApiService::class.java)
            .search(page,keyWord)
            .data()
            .let {
                ArticleListBean.trans(it.datas?: mutableListOf())
            }
    }

}