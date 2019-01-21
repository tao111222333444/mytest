package com.hugo.mytest.adapter

import android.content.Context
import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.hugo.mytest.R
import com.hugo.mytest.viewModel.DetailsViewModel.TextColor

/**
 * @author  作者：hugo
 * @date    时间：2019/1/6.
 * 版本：v1.0
 * 描述：
 */
object DetailsAdapter {

    @BindingAdapter("app:background")
    @JvmStatic
    fun mybackground(view: TextView, type:TextColor) {
        var color = getbgColor(type,view.context)
        view.setBackgroundColor(color)
        color = myTextColor(type,view.context)
        view.setTextColor(color)

    }
    @BindingAdapter("app:setImage")
    @JvmStatic
    fun setImage(view: ImageView, url:String?){
        if(url== null){
            return
        }
        Glide.with(view.context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }

    fun myTextColor(textColor: TextColor, context: Context): Int {
        return when (textColor) {
            TextColor.NORMAL -> ContextCompat.getColor(context, R.color.textColor)
            TextColor.NO_SELECTED -> ContextCompat.getColor(context, R.color.textColor)
            TextColor.SELECTED  -> ContextCompat.getColor(context, R.color.white)
        }
    }

    private fun getbgColor(textColor: TextColor, context: Context): Int {
        return when (textColor) {
            TextColor.NORMAL -> ContextCompat.getColor(context, R.color.textColor1)
            TextColor.NO_SELECTED -> ContextCompat.getColor(context, R.color.white)
            TextColor.SELECTED  -> ContextCompat.getColor(context, R.color.btnColor)
        }
    }
}