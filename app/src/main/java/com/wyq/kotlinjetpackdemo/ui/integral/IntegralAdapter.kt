package com.wyq.kotlinjetpackdemo.ui.integral

import com.wyq.kotlinjetpackdemo.R
import com.wyq.kotlinjetpackdemo.databinding.ItemIntegralBinding
import com.zs.base_library.common.adapter.BaseDiffAdapter
import com.zs.base_library.common.adapter.DefaultDiff

/**
 * des 积分适配器
 * @author zs
 * @date 2020/9/10
 */
class IntegralAdapter :BaseDiffAdapter<IntegralListBean, ItemIntegralBinding>(Diff()) {


    override val itemLayoutId: Int = R.layout.item_integral
    override fun bindData(
        holder: BaseDiffViewHolder,
        position: Int,
        itemData: IntegralListBean,
        binding: ItemIntegralBinding
    ) {
        binding.dataBean = itemData
    }

    class Diff : DefaultDiff<IntegralListBean>() {
        override fun areItemsTheSame(
            oldItem: IntegralListBean,
            newItem: IntegralListBean
        ): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(
            oldItem: IntegralListBean,
            newItem: IntegralListBean
        ): Boolean {
            return true
        }
    }
}