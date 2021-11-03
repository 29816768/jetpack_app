package com.wyq.kotlinjetpackdemo.ui.play

import android.os.Bundle
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.wyq.kotlinjetpackdemo.PlayViewModel
import com.wyq.kotlinjetpackdemo.R
import com.wyq.kotlinjetpackdemo.BR
import com.wyq.kotlinjetpackdemo.db.AppDataBase
import com.wyq.kotlinjetpackdemo.play.PlayList
import com.wyq.kotlinjetpackdemo.play.PlayerManager
import com.wyq.kotlinjetpackdemo.ui.PlayBindAdapter
import com.wyq.kotlinjetpackdemo.ui.play.collect.CollectAudioBean
import com.zs.base_library.base.BaseVmFragment
import com.zs.base_library.base.DataBindingConfig
import com.zs.base_library.common.setNoRepeatClick
import com.zs.base_library.common.stringForTime
import com.zs.base_library.utils.StatusUtils
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * des 播放,View通过dataBinding绑定
 */
class PlayerFragment : BaseVmFragment() {

    private var playVM: PlayViewModel? = null
    private val playListFragment = PlayListFragment()

    override fun init(savedInstanceState: Bundle?) {
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //一定要将动画置空，生命周期跟随fragment，否则会引发一致性问题
        PlayBindAdapter.anim = null
    }

    override fun initView() {
        setMarginTop()
        initSeek()
    }

    /**
     * 设置顶部信息marginTop,适配状态栏高度
     */
    private fun setMarginTop() {
        val params = ivBack.layoutParams as ConstraintLayout.LayoutParams
        params.topMargin = StatusUtils.getStatusBarHeight(mContext)
        ivBack.layoutParams = params
    }

    /**
     * 初始化seekBar
     */
    private fun initSeek() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tvStartTime.text = stringForTime(seekBar.progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            /**
             * 拖动放开
             */
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                PlayerManager.instance.seekTo(seekBar.progress)
            }
        })
    }

    override fun initViewModel() {
        playVM = getActivityViewModel(PlayViewModel::class.java)
    }

    override fun getLayoutId() = R.layout.fragment_player

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_player, playVM)
            .addBindingParam(BR.vm, playVM)
    }

    override fun onClick() {
        setNoRepeatClick(ivBack, ivCollect,ivMode, ivPrevious, ivPlay, ivNext, ivList) {
            when (it.id) {
                //返回
                R.id.ivBack -> {
                    nav().navigateUp()
                }
                //收藏
                R.id.ivCollect -> {
                    collect()
                }
                //切换模式
                R.id.ivMode -> {
                    PlayerManager.instance.switchPlayMode()
                }
                //上一首
                R.id.ivPrevious -> {
                    PlayerManager.instance.previous()
                }
                //播放/暂停
                R.id.ivPlay -> {
                    PlayerManager.instance.controlPlay()
                }
                //下一首
                R.id.ivNext -> {
                    PlayerManager.instance.next()
                }
                //播放列表
                R.id.ivList -> {
                    playListFragment.show(mActivity.supportFragmentManager, "")
                }
            }
        }
    }

    /**
     * 收藏/取消收藏
     */
    private fun collect() {
        val audio = PlayerManager.instance.getCurrentAudioBean() ?: return
        lifecycleScope.launch {
            var collect: Boolean
            withContext(Dispatchers.IO) {
                //在收藏表查询
                AppDataBase.getInstance()
                    .collectDao()
                    .findAudioById(audio.id)
                    .apply {
                        //未被收藏
                        collect = if (this == null) {
                            //做收藏操作
                            AppDataBase.getInstance()
                                .collectDao()
                                .insertAudio(CollectAudioBean.audio2Collect(audio))
                            PlayList.instance.collect(audio)
                            true
                        }
                        //已收藏
                        else {
                            //做取消收藏操作
                            AppDataBase.getInstance()
                                .collectDao()
                                .deleteAudio(this)
                            PlayList.instance.unCollect(audio)
                            false
                        }
                    }
            }
            playVM?.collect?.set(collect)
        }
    }
}
