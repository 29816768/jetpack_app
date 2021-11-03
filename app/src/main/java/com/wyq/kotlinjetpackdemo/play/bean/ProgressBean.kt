package com.wyq.kotlinjetpackdemo.play.bean

/**
 * 进度模型
 */
data class ProgressBean(
    /**
     * 当前时间
     */
    var currentDuration: Int = 0,

    /**
     * 总时间
     */
    var totalDuration: Int = 0
)