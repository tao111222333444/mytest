package com.hugo.mytest.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayMap
import com.hugo.mytest.bean.ColourSkuVoList
import com.hugo.mytest.bean.Datas
import com.hugo.mytest.bean.SkuVoList

/**
 * @author  作者：hugo
 * @date    时间：2019/1/6.
 * 版本：v1.0
 * 描述：
 */
class DetailsViewModel:ViewModel(){


    val data1: MutableLiveData<Datas> by lazy {
        MutableLiveData<Datas>()
    }


    val SkuVoList: MutableLiveData<ColourSkuVoList> by lazy {
        MutableLiveData<ColourSkuVoList>()
    }
    val skuVo  by lazy { MutableLiveData<SkuVoList>()}

    val numberArray : ObservableArrayMap<String, String> by lazy {ObservableArrayMap<String, String>()}

    enum class TextColor {
        NORMAL,
        NO_SELECTED,
        SELECTED
    }
}