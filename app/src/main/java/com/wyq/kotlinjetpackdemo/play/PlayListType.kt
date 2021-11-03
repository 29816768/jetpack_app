package com.wyq.kotlinjetpackdemo.play

/**
 * des 播放列表类型，当前版本只有本地播放
 */
class PlayListType {

    companion object{

        /**
         * 本地播放列表-默认
         */
        const val LOCAL_PLAY_LIST = 10

        /**
         * 收藏播放列表
         */
        const val COLLECT_PLAY_LIST = 100

        /**
         * 历史播放列表
         */
        const val HISTORY_PLAY_LIST = 1000
    }
}