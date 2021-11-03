package com.wyq.kotlinjetpackdemo.ui.common

import com.wyq.kotlinjetpackdemo.http.ApiService
import com.zs.base_library.base.BaseRepository
import com.wyq.kotlinjetpackdemo.http.RetrofitManager

/**
 * des
 * author zs
 * date 2021/4/25
 */
class CollectRepo: BaseRepository() {
    /**
     * 收藏
     */
    suspend fun collect(id: Int)  = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .collect(id)
            .data(Any::class.java)
            .let {
                id
            }
    }
    /**
     * 取消收藏
     */
    suspend fun unCollect(id: Int) = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .unCollect(id)
            .data(Any::class.java)
            .let {
                id
            }
    }
}