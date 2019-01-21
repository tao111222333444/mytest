package com.hugo.mytest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.hugo.mytest.BR
import com.hugo.mytest.R
import com.hugo.mytest.adapter.DetailsColorAdapter
import com.hugo.mytest.adapter.DetailsNumberAdapter
import com.hugo.mytest.bean.ColourSkuVoList
import com.hugo.mytest.bean.Datas
import com.hugo.mytest.bean.SkuVoList
import com.hugo.mytest.databinding.ActivityDetailsBinding
import com.hugo.mytest.utils.ClickCallBack
import com.hugo.mytest.utils.MyItemDecoration
import com.hugo.mytest.utils.Utils
import com.hugo.mytest.viewModel.DetailsViewModel

/**
 * @author  作者：hugo
 * @date    时间：2019/1/6.
 * 版本：v1.0
 * 描述：
 */
class ProductDetailsAcitivty : AppCompatActivity() {
    companion object {
        val INPUT_DATA = "datas"
    }

    lateinit var binding: ActivityDetailsBinding
     val viewModel: DetailsViewModel by lazy { ViewModelProviders.of(this).get(DetailsViewModel::class.java)  }

    lateinit var colorAdapter: DetailsColorAdapter
    lateinit var clickCallBack: ClickCallBack

    lateinit var numberData: DetailsNumberAdapter
    lateinit var clickCallBack1: ClickCallBack
    lateinit var  allData:SkuVoList

    lateinit var clickCallBack3: ClickCallBack

    val string:StringBuilder by lazy { StringBuilder() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)


        val datas: Datas = intent.getSerializableExtra(INPUT_DATA) as Datas
        observeViewModel()
        viewModel.data1.postValue(datas)
        colorAdapter =  DetailsColorAdapter(clickCallBack)
        binding.recyclerViewColor.adapter = colorAdapter
        binding.recyclerViewColor.addItemDecoration(MyItemDecoration(resources.getDrawable(R.drawable.bg_line)
                , Utils.dp2px(this,1),false))
        binding.recyclerViewColor.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewColor.itemAnimator = DefaultItemAnimator()


        numberData =  DetailsNumberAdapter(clickCallBack1,viewModel.numberArray)
        binding.recyclerViewDetails.adapter = numberData
        binding.recyclerViewDetails.addItemDecoration(MyItemDecoration(resources.getDrawable(R.drawable.bg_line)
                , Utils.dp2px(this,1),isLeftShow = true))
        binding.recyclerViewDetails.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewDetails.itemAnimator = DefaultItemAnimator()
        binding.recyclerViewDetails.isNestedScrollingEnabled = true
        binding.recyclerViewDetails.setHasFixedSize(false)
        binding.onClick = clickCallBack3
    }

    private fun observeViewModel() {
        viewModel.data1.observe(this, Observer {
                val allData= ColourSkuVoList()
                allData.isSelector = true
                allData.skuVoList = it?.skuMap?.voList
                allData.colour = "全部已选"
                viewModel.SkuVoList.postValue(allData)
                colorAdapter.setNewData(allData)
            it?.skuMap?.colourSkuVoList?.let { it1 -> colorAdapter.addData(it1) }
            binding.setVariable(BR.datas,it)
        })

        clickCallBack = object : ClickCallBack {
            override fun onClick(item: Any) {

                var data =  item as ColourSkuVoList

                if(data.skuVoList == null || data == viewModel.SkuVoList.value){
                    return
                }
                data.isSelector  = true
                viewModel.SkuVoList.value?.isSelector = false
                viewModel.skuVo.value?.isSelector =DetailsViewModel.TextColor.NO_SELECTED
                colorAdapter.notifyItemChanged(colorAdapter.list.indexOf(viewModel.SkuVoList.value))
                colorAdapter.notifyItemChanged(colorAdapter.list.indexOf(data))
                viewModel.SkuVoList.postValue(data)
                Toast.makeText(this@ProductDetailsAcitivty,data.colour, Toast.LENGTH_SHORT).show()

            }
        }

        viewModel.SkuVoList.observe(this, Observer {
            if (numberData.list.size<=0) {
                allData = SkuVoList()
                allData.isTitle = true
                allData.isSelector = DetailsViewModel.TextColor.NORMAL
                numberData.setNewData(allData)
            }
            it?.skuVoList?.let { it1 ->

                    viewModel.skuVo.postValue(it1[0])
                    it1[0].isSelector = DetailsViewModel.TextColor.SELECTED
//                    it1.add(0,allData)
                    numberData.addData(it1)
                 }
        })

        viewModel.skuVo.observe(this, Observer {
            binding.volist = it
            binding.map = viewModel.numberArray
        })

        clickCallBack1= object : ClickCallBack {
            override fun onClick(item: Any) {
                val volist = item as SkuVoList
                if( volist == viewModel.skuVo.value){
                    return
                }
                viewModel.skuVo.value?.isSelector = DetailsViewModel.TextColor.NO_SELECTED
                volist.isSelector = DetailsViewModel.TextColor.SELECTED
                numberData.notifyItemChanged(numberData.list.indexOf(viewModel.skuVo.value))
                numberData.notifyItemChanged(numberData.list.indexOf(volist))
                viewModel.skuVo.postValue(volist)
            }
        }

        clickCallBack3 = object :ClickCallBack{
            override fun onClick(item: Any) {
                var  it = item as String
                var key = viewModel.skuVo.value?.goodsNub
                when(it){
                    "1","2", "3","4" , "5" , "6" , "7" , "8" , "9" ,"0"->{
                       string.clear()
                        val aa = viewModel.numberArray[key]
                        if (aa!= null && aa.toInt() != 0){
                            string.append(aa)
                        }
                        string.append(it)
                        viewModel.numberArray[key] = string.toString()
                    }
                    "C" ->{
                        viewModel.numberArray.remove(key)
                    }
                    "X" ->{
                        val aa = viewModel.numberArray[key]
                        viewModel.numberArray[key] =aa?.substring(0,aa.length-1)
                    }
                }
                binding.map = viewModel.numberArray
                numberData.notifyItemChanged(numberData.list.indexOf(viewModel.skuVo.value))
            }

            }

    }
}