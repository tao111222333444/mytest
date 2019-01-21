package com.hugo.mytest.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hugo.mytest.utils.ClickCallBack
import com.hugo.mytest.R
import com.hugo.mytest.databinding.ItmeMainBinding
import com.hugo.mytest.viewModel.MainItmeViewModel

/**
 * @author  作者：hugo
 * @date    时间：2019/1/5.
 * 版本：v1.0
 * 描述：
 */
class MainAdapter(var clickCallBack: ClickCallBack) :RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

     val list:MutableList<MainItmeViewModel> by lazy { arrayListOf<MainItmeViewModel>() }

    fun setNewData(newItems: List<MainItmeViewModel>){
        this.list.clear()
        this.list.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainViewHolder {
        val itemBinding = DataBindingUtil.inflate<ItmeMainBinding>(LayoutInflater.from(p0.context), R.layout.itme_main,p0,false)
        itemBinding.onClick = clickCallBack
        return MainViewHolder(itemBinding)
    }

    override fun getItemCount()= list.size



    override fun onBindViewHolder(p0: MainViewHolder, p1: Int) {
        p0.item.viewModel= list[p1]
    }

    class MainViewHolder(var item: ItmeMainBinding) : RecyclerView.ViewHolder(item.root)
}