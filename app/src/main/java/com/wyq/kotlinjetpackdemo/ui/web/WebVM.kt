package com.wyq.kotlinjetpackdemo.ui.web

import androidx.databinding.ObservableField
import com.zs.base_library.base.BaseViewModel

/** Author: wangyongqi
 * Date: 2021/11/3 09:54
 * Description:
 */
class WebVM :BaseViewModel(){

    /**
     * webView 进度
     */
    val progress = ObservableField<Int>()


    /**
     * 最大 进度
     */
    val maxProgress = ObservableField<Int>()

    /**
     * progress是否隐藏
     */
    val isVisible = ObservableField<Boolean>()
}