package com.wyq.kotlinjetpackdemo.ui.main.tab

import com.wyq.kotlinjetpackdemo.bean.ArticleListBean
import com.wyq.kotlinjetpackdemo.constants.Constants
import com.wyq.kotlinjetpackdemo.http.ApiService
import com.wyq.kotlinjetpackdemo.http.RetrofitManager
import com.zs.base_library.base.BaseRepository

/**
 * des 每个tab下文章的数据层
 * @date 2020/7/8
 * @author zs
 */
class ArticleRepo : BaseRepository() {

    private var page = 1

    /**
     * 请求第一页
     */
    suspend fun getArticles(
        type: Int,
        tabId: Int
    ) = withIO {
        page = 1
        getList(page,type, tabId)
    }

    /**
     * 请求第一页
     */
    suspend fun loadMoreArticles(
        type: Int,
        tabId: Int
    ) = withIO {
        page++
        getList(page,type, tabId)
    }

    private suspend fun getList(
        page: Int,
        type: Int,
        tabId: Int
    ) : MutableList<ArticleListBean>{
        //项目
        return if (type == Constants.PROJECT_TYPE) {
            RetrofitManager.getApiService(ApiService::class.java)
                .getProjectList(page, tabId)
                .data()
                .let {
                    ArticleListBean.trans(it.datas?: mutableListOf())
                }

        }
        //公号
        else {
            RetrofitManager.getApiService(ApiService::class.java)
                .getAccountList(tabId, page)
                .data()
                .let {
                    ArticleListBean.trans(it.datas?: mutableListOf())
                }
        }
    }

}