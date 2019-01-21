package com.hugo.mytest.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.hugo.mytest.bean.Data
import com.hugo.mytest.bean.Datas

/**
 * @author  作者：hugo
 * @date    时间：2019/1/5.
 * 版本：v1.0
 * 描述：
 */
class MainItmeViewModel :ViewModel(){
    val data1: MutableLiveData<Datas> by lazy {
        MutableLiveData<Datas>()
    }

    val itme :ObservableField<Datas> = ObservableField()


    fun setItem(item:Datas){
        this.itme.set(item)
    }
}