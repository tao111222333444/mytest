package com.hugo.mytest.utils

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

/**
 * @author  作者：hugo
 * @date    时间：2019/1/5.
 * 版本：v1.0
 * 描述：
 */
class MyItemDecoration( var lineColor:Drawable,var spacing:Int = 1 ,var isLeftShow:Boolean  = true) : RecyclerView.ItemDecoration() {


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount  = parent.childCount

        repeat(childCount){
            //横向分割线
            val child = parent.getChildAt(it)
            val params:RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams

            val left = child.left - params.leftMargin
            val right = child.right - params.rightMargin
            val top = child.bottom - params.bottomMargin
            val bottom = top - lineColor.intrinsicHeight
            lineColor.setBounds(left,top,right, bottom)
            lineColor.draw(c)


        }

        repeat(childCount){
            //竖线
            val child = parent.getChildAt(it)
            val params:RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right - params.rightMargin
            val right = left -lineColor.intrinsicWidth
            val top = child.top - params.topMargin-spacing
            val bottom = child.bottom + params.bottomMargin+spacing
            lineColor.setBounds(left,top,right, bottom)
            lineColor.draw(c)

            if(isLeftShow) {
                lineColor.setBounds(0, top, child.left, bottom)
                lineColor.draw(c)
            }

        }

        //顶部横线
        if (parent.getChildAt(0) != null && isLeftShow) {
            lineColor.setBounds(0, 0, parent.getChildAt(0).right, spacing)
            lineColor.draw(c)
        }

    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val childCount = parent.childCount

        if (isLeftShow){
            outRect.left = spacing
            if (position == 0 ){
                outRect.top = spacing
            }

        }
        outRect.right = spacing
        if (position != 0 ){
            outRect.top = spacing/2
        }

        if (position == childCount-1){
            outRect.bottom  = spacing
        }else{
            outRect.bottom = spacing/2
        }
    }



}