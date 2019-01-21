package com.hugo.mytest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.hugo.mytest.*
import com.hugo.mytest.adapter.MainAdapter
import com.hugo.mytest.bean.Datas

import com.hugo.mytest.databinding.ActivityMainBinding
import com.hugo.mytest.utils.ClickCallBack
import com.hugo.mytest.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    lateinit var clickCallBack: ClickCallBack


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.setVariable(BR.viewModel,viewModel)
        lifecycle.addObserver(viewModel)
        observeViewModel()
        adapter = MainAdapter(clickCallBack)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun observeViewModel() {
        viewModel.itemLiveData.observe(this, Observer {
            if (it!=null ){
                adapter.setNewData(it)
            }
        })

        clickCallBack = object : ClickCallBack {
            override fun onClick(item: Any) {
                var data =  item as Datas
                Toast.makeText(this@MainActivity,item.title,Toast.LENGTH_SHORT).show()
                gotoProductDetailsAcitivty(data)
            }
        }
    }

    fun gotoProductDetailsAcitivty(item:Datas){
        val intent  =Intent(this,ProductDetailsAcitivty::class.java)
        intent.putExtra(ProductDetailsAcitivty.INPUT_DATA,item)
        startActivity(intent)
    }
}
