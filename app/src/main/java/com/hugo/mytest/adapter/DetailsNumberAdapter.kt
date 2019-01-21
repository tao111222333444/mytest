package com.hugo.mytest.adapter

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayMap
import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.calculateDiff
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hugo.mytest.BR
import com.hugo.mytest.R
import com.hugo.mytest.bean.SkuVoList
import com.hugo.mytest.databinding.ItemDetailsBinding
import com.hugo.mytest.utils.ClickCallBack

/**
 * @author  作者：hugo
 * @date    时间：2019/1/7.
 * 版本：v1.0
 * 描述：
 */
class DetailsNumberAdapter(var clickCallBack: ClickCallBack,var map : ObservableArrayMap<String, String>) :RecyclerView.Adapter<DetailsNumberAdapter.ColorViewHolder>(){

    val list:MutableList<SkuVoList> by lazy { mutableListOf<SkuVoList>() }


    fun setNewData(newItems: SkuVoList){
        this.list.clear()
        this.list.add(newItems)

    }


    fun addData(newItems: List<SkuVoList>){
        val aa =this.list.size-1
        for (it in aa downTo 1){
            this.list.removeAt(it)
        }
        notifyItemRangeRemoved(1,aa)
        this.list.addAll(newItems)
        notifyItemRangeChanged(1,this.list.size)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ColorViewHolder {
        val itemBinding = DataBindingUtil.inflate<ItemDetailsBinding>(LayoutInflater.from(p0.context), R.layout.item_details,p0,false)
        itemBinding.onClick = clickCallBack
        itemBinding.map = map
        return ColorViewHolder(itemBinding)
    }

    override fun getItemCount()= list.size

    override fun onBindViewHolder(p0: ColorViewHolder, p1: Int) {
        p0.item.setVariable(BR.skuVoList,list[p1])
    }

    class ColorViewHolder(var item: ItemDetailsBinding) : RecyclerView.ViewHolder(item.root)
}