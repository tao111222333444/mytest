package com.hugo.mytest.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hugo.mytest.BR
import com.hugo.mytest.R
import com.hugo.mytest.bean.ColourSkuVoList
import com.hugo.mytest.databinding.ItemDetailsColorBinding
import com.hugo.mytest.utils.ClickCallBack

/**
 * @author  作者：hugo
 * @date    时间：2019/1/6.
 * 版本：v1.0
 * 描述：
 */
class DetailsColorAdapter(var clickCallBack: ClickCallBack) :RecyclerView.Adapter<DetailsColorAdapter.ColorViewHolder>(){

    val list:MutableList<ColourSkuVoList> by lazy { arrayListOf<ColourSkuVoList>() }

    fun setNewData(newItems:ColourSkuVoList){
        this.list.clear()
        this.list.add(newItems)
    }

    fun addData(newItems: List<ColourSkuVoList>){
        this.list.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ColorViewHolder {
        val itemBinding = DataBindingUtil.inflate<ItemDetailsColorBinding>(LayoutInflater.from(p0.context), R.layout.item_details_color,p0,false)
        itemBinding.onClick = clickCallBack
        return ColorViewHolder(itemBinding)
    }

    override fun getItemCount()= list.size

    override fun onBindViewHolder(p0: ColorViewHolder, p1: Int) {
        p0.item.setVariable(BR.viewModel,list[p1])
    }

    class ColorViewHolder(var item: ItemDetailsColorBinding) : RecyclerView.ViewHolder(item.root)
}