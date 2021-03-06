package com.wyq.kotlinjetpackdemo.ui.main.mine

import androidx.lifecycle.MutableLiveData
import com.wyq.kotlinjetpackdemo.constants.Constants
import com.wyq.kotlinjetpackdemo.http.ApiService
import com.wyq.kotlinjetpackdemo.http.RetrofitManager
import com.zs.base_library.base.BaseRepository
import com.zs.base_library.http.ApiException
import com.zs.base_library.utils.PrefUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * des 我的
 * @date 2020/7/10
 * @author zs
 */
class MineRepo(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) :
    BaseRepository(coroutineScope, errorLiveData) {

    fun getInternal(internalLiveData: MutableLiveData<IntegralBean>) {
        launch(
            block = {
                RetrofitManager.getApiService(ApiService::class.java)
                    .getIntegral()
                    .data()
            },
            success = {
                PrefUtils.setObject(Constants.INTEGRAL_INFO, it)
                internalLiveData.postValue(it)
            }
        )
    }

    suspend fun getInternal(): Flow<IntegralBean> {
        return flow {
            emit(
                RetrofitManager.getApiService(ApiService::class.java)
                    .getIntegral()
                    .data()
            )
        }.flowOn(Dispatchers.IO)
    }
}