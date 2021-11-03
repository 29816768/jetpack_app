package com.wyq.kotlinjetpackdemo.ui.integral

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zs.base_library.base.BaseViewModel

/**
 * des 文章vm
 * @date 2020/7/8
 * @author zs
 */
class IntegralVM : BaseViewModel() {
    private val repo by lazy { IntegralRepo() }

    /**
     * 收藏的的文章
     */
    private val _integralLiveData = MutableLiveData<MutableList<IntegralListBean>>()
    val integralLiveData:LiveData<MutableList<IntegralListBean>> = _integralLiveData

    /**
     * 获取收藏列表
     */
    fun getIntegral() {
        launch {
            _integralLiveData.value = repo.getIntegral()
            handleList(integralLiveData)
        }
    }

    /**
     * 获取收藏列表
     */
    fun loadMore() {
        launch {
            val list =  integralLiveData.value
            list?.addAll(repo.loadMore())
            _integralLiveData.value = list
            handleList(integralLiveData)
        }
    }
}