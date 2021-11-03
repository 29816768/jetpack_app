package com.wyq.kotlinjetpackdemo.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.wyq.kotlinjetpackdemo.R

/**
 * des 文章列表dataBinding适配器
 * @date 2020/6/28
 * @author zs
 */
object ArticleBindAdapter {

    /**
     * 加载图片,做高斯模糊处理
     */
    @BindingAdapter(value = ["articleCollect"])
    @JvmStatic
    fun imgPlayBlur(view: ImageView, collect: Boolean) {
        if (collect) {
            view.setImageResource(R.mipmap._collect)
        } else {
            view.setImageResource(R.mipmap.un_collect)
        }
    }

}