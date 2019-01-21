package com.hugo.mytest.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.hugo.mytest.bean.JsonRootBean
import com.hugo.mytest.utils.Utils

/**
 * @author  作者：hugo
 * @date    时间：2019/1/5.
 * 版本：v1.0
 * 描述：
 */
class MainViewModel(application:Application) :AndroidViewModel(application), LifecycleObserver {
     val  itemLiveData:MutableLiveData<List<MainItmeViewModel>> by lazy { MutableLiveData<List<MainItmeViewModel>>() }
    init{
        val json = Utils.getJson(application)
        val data = Gson().fromJson<JsonRootBean>(json)
        val list = data.data?.datas?.size?.let { it ->
            MutableList<MainItmeViewModel>(size = it){
                val item = MainItmeViewModel()
                item .data1 .postValue( data.data?.datas?.get(it))
                return@MutableList item
        } }
        itemLiveData .postValue( list)
    }


}