package com.wyq.kotlinjetpackdemo.ui.set

import androidx.lifecycle.MutableLiveData
import com.wyq.kotlinjetpackdemo.http.ApiService
import com.wyq.kotlinjetpackdemo.http.RetrofitManager
import com.wyq.kotlinjetpackdemo.utils.CacheUtil
import com.zs.base_library.base.BaseRepository
import com.zs.base_library.http.ApiException
import kotlinx.coroutines.CoroutineScope

/**
 * des 设置
 * @date 2020/7/10
 * @author zs
 */
class SetRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {

    /**
     * 退出登陆
     */
    fun logout(logoutLiveData : MutableLiveData<Any>){
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .logout()
                    .data(Any::class.java)
            },
            success = {
                CacheUtil.resetUser()
                logoutLiveData.postValue(it)
            }
        )
    }
}