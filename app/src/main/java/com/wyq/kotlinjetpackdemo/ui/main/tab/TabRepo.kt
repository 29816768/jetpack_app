package com.wyq.kotlinjetpackdemo.ui.main.tab

import com.wyq.kotlinjetpackdemo.constants.Constants
import com.zs.base_library.base.BaseRepository
import com.wyq.kotlinjetpackdemo.http.RetrofitManager
import com.wyq.kotlinjetpackdemo.http.ApiService


/**
 * des tab
 * @date 2020/7/7
 * @author zs
 */
class TabRepo : BaseRepository() {


    suspend fun getTab(type: Int) = withIO {
        if (type == Constants.PROJECT_TYPE) {
            RetrofitManager.getApiService(ApiService::class.java)
                .getProjectTabList()
                .data()
        } else {
            RetrofitManager.getApiService(ApiService::class.java)
                .getAccountTabList()
                .data()
        }
    }
}
