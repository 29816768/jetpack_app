package com.wyq.kotlinjetpackdemo.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wyq.kotlinjetpackdemo.bean.ArticleBean
import com.zs.base_library.base.BaseViewModel

/**
 * @date 2020/7/13
 * @author zs
 */
class MyArticleVM :BaseViewModel(){

    private val repo by lazy { MyArticleRepo(viewModelScope,errorLiveData) }

    val myLiveDate = MutableLiveData<MutableList<ArticleBean.DatasBean>>()

    val deleteLiveData = MutableLiveData<Int>()

    fun getMyArticle(isRefresh:Boolean){
        repo.getMyArticle(isRefresh,myLiveDate,emptyLiveDate)
    }

    fun delete(id:Int){
        repo.delete(id,deleteLiveData)
    }
}