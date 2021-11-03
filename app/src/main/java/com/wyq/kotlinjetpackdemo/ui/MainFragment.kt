package com.wyq.kotlinjetpackdemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wyq.kotlinjetpackdemo.PlayViewModel
import com.wyq.kotlinjetpackdemo.R
import com.wyq.kotlinjetpackdemo.BR
import com.wyq.kotlinjetpackdemo.constants.Constants
import com.wyq.kotlinjetpackdemo.ui.main.home.HomeFragment
import com.wyq.kotlinjetpackdemo.ui.main.mine.MineFragment
import com.wyq.kotlinjetpackdemo.ui.main.square.SquareFragment
import com.wyq.kotlinjetpackdemo.ui.main.tab.TabFragment
import com.zs.base_library.base.BaseVmFragment
import com.zs.base_library.base.DataBindingConfig
import com.zs.base_library.common.doSelected
import com.zs.base_library.common.initFragment
import kotlinx.android.synthetic.main.fragment_main.*

/** Author: wangyongqi
 * Date: 2021/11/1 11:35
 * Description:主页面
 */
class MainFragment : BaseVmFragment() {

    private val fragmentList = arrayListOf<Fragment>()

    /**
     * 首页
     */
    private val homeFragment by lazy { HomeFragment() }

    /**
     * 项目
     */
    private val projectFragment by lazy {
        TabFragment().apply {
            arguments = Bundle().apply {
                putInt("type", Constants.PROJECT_TYPE)
            }
        }
    }


    /**
     * 广场
     */
    private val squareFragment by lazy { SquareFragment() }

    /**
     * 公众号
     */
    private val publicNumberFragment by lazy {
        TabFragment().apply {
            arguments = Bundle().apply {
                putInt("type", Constants.ACCOUNT_TYPE)
            }
        }
    }

    /**
     * 我的
     */
    private val mineFragment by lazy { MineFragment() }

    private var playViewModel: PlayViewModel? = null

    init {
        fragmentList.apply {
            add(homeFragment)
            add(projectFragment)
            add(squareFragment)
            add(publicNumberFragment)
            add(mineFragment)
        }
    }

    override fun initViewModel() {
        playViewModel = getActivityViewModel(PlayViewModel::class.java)
    }

    override fun init(savedInstanceState: Bundle?) {
        vpHome.initFragment(childFragmentManager, fragmentList).run {
            //全部缓存,避免切换回重新加载
            offscreenPageLimit = fragmentList.size
        }

        vpHome.doSelected {
            btnNav.menu.getItem(it).isChecked = true
        }
        //初始化底部导航栏
        btnNav.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> vpHome.setCurrentItem(0, false)
                    R.id.menu_project -> vpHome.setCurrentItem(1, false)
                    R.id.menu_square -> vpHome.setCurrentItem(2, false)
                    R.id.menu_official_account -> vpHome.setCurrentItem(3, false)
                    R.id.menu_mine -> vpHome.setCurrentItem(4, false)
                }
                // 这里注意返回true,否则点击失效
                true
            }
        }

    }

    override fun getLayoutId() = R.layout.fragment_main

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_main,playViewModel)
            .addBindingParam(BR.vm,playViewModel)
    }
}