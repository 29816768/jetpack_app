package com.wyq.kotlinjetpackdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wyq.kotlinjetpackdemo.ui.play.collect.CollectAudioBean
import com.wyq.kotlinjetpackdemo.ui.play.collect.CollectAudioDao
import com.wyq.kotlinjetpackdemo.ui.play.history.HistoryAudioBean
import com.wyq.kotlinjetpackdemo.ui.play.history.HistoryAudioDao
import com.zs.base_library.BaseApp

/** Author: wangyongqi
 * Date: 2021/11/1 14:47
 * Description:
 */
@Database(entities = [HistoryAudioBean::class, CollectAudioBean::class],
    version = 1,
    exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    /**
     * 获取HistoryAudioDao
     */
    abstract fun historyDao(): HistoryAudioDao

    /**
     * 获取CollectAudioBean
     */
    abstract fun collectDao(): CollectAudioDao

    companion object {
        private var instance: AppDataBase? = null

        fun getInstance(): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(BaseApp.getContext()).also { instance = it }
            }
        }

        private fun buildDataBase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "jet-database")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
        }

    }


}