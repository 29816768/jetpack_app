package com.wyq.kotlinjetpackdemo.ui.collect

import com.wyq.kotlinjetpackdemo.bean.ArticleListBean
import com.wyq.kotlinjetpackdemo.http.ApiService
import com.wyq.kotlinjetpackdemo.http.RetrofitManager
import com.zs.base_library.base.BaseRepository

/**
 * des 收藏的文章
 * @date 2020/7/8
 * @author zs
 */
class CollectListRepo : BaseRepository() {

    private var page = 0


    /**
     * 请求第一页
     */
    suspend fun getCollectArticle() = withIO {
        page = 0
        RetrofitManager.getApiService(ApiService::class.java)
            .getCollectData(page)
            .data()
            .let {
                ArticleListBean.transByCollect(it.datas?: mutableListOf())
            }
    }

    /**
     * 请求下一页
     */
    suspend fun loadMoreCollectArticle() = withIO {
        page++
        RetrofitManager.getApiService(ApiService::class.java)
            .getCollectData(page)
            .data()
            .let {
                ArticleListBean.transByCollect(it.datas?: mutableListOf())
            }
    }

    /**
     * 取消收藏
     */
    suspend fun unCollect(id:Int) = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .unCollect(id)
            //如果data可能为空,可通过此方式通过反射生成对象,避免空判断
            .data(Any::class.java)
    }
}