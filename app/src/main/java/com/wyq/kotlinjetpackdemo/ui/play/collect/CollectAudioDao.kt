package com.wyq.kotlinjetpackdemo.ui.play.collect

import androidx.room.*
import java.util.*

/** Author: wangyongqi
 * Date: 2021/11/1 15:05
 * Description:用于音频数据库增删改查
 */
@Dao
interface CollectAudioDao {

    /**
     * 增加一条音频
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAudio(audioBean: CollectAudioBean)

    /**
     * 增加多条音频,除了List之外，也可以使用数组
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAudios(audioBean: MutableList<CollectAudioBean>)

    /**
     * 删除一条音频
     */
    @Delete
    fun deleteAudio(audioBean: CollectAudioBean)

    /**
     * 删除多条音频
     */
    @Delete
    fun deleteAudios(audioBean: MutableList<CollectAudioBean>)

    /**
     * 更新一条音频
     */
    @Update
    fun updateAudio(audioBean: CollectAudioBean)

    /**
     * 更新多条音频
     */
    @Update
    fun updateAudios(audioBean: MutableList<CollectAudioBean>)


    /**
     * 查询一个
     */
    @Query("SELECT * FROM collect_audio WHERE id=:id")
    fun findAudioById(id: Long): CollectAudioBean?


    /**
     * 返回所有的数据,结果为LiveData
     */
    @Query("SELECT * FROM collect_audio")
    fun getAllAudios(): MutableList<CollectAudioBean>?

}