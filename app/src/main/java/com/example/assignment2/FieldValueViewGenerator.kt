package com.example.assignment2

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class FieldValueViewGenerator (val ctx : Context, val labelName: String)
{
    fun generate() : LinearLayout {
        val layoutObj = LinearLayout(ctx)
        val lParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutObj.layoutParams = lParams
        layoutObj.orientation = LinearLayout.HORIZONTAL

        val lblView = TextView(ctx)
        lblView.text = labelName
        val lbParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        lbParams.weight = 1.0F
        layoutObj.addView(lblView, lbParams)

        val valView = TextView(ctx)
        valView.text = "Initial"
        valView.setBackgroundColor(Color.GREEN)
        val vParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        vParams.weight = 1.0F
        layoutObj.addView(valView, vParams)

        return layoutObj

        }
    }
