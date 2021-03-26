package com.example.customviews.ui.custom

import android.content.Context
import android.content.SharedPreferences
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customviews.R

class ValueSelectionView : ConstraintLayout {

    private lateinit var txtValue: TextView
    private lateinit var btnLess: ImageButton
    private lateinit var btnPlus: ImageButton

    private lateinit var mPreferences: SharedPreferences

    var cnt = 0
    var maxValue = 100

    private var valueChangedListener: (value: Int, maxValue: Int) -> Unit = { _, _ -> }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        txtValue = findViewById(R.id.txtValue)
        btnLess = findViewById(R.id.btnLess)
        btnPlus = findViewById(R.id.btnPlus)

        cnt = mPreferences.getInt("CNT", 0)

        Log.d("Vitor", cnt.toString())

        btnLess.setOnClickListener {
            if (cnt > 0) {
                txtValue.text = (--cnt).toString()
                with(mPreferences.edit()){
                    putInt("CNT", cnt)
                    apply()
                }
                valueChangedListener(cnt, maxValue)
            }
        }
        txtValue.text = cnt.toString()

        btnPlus.setOnClickListener {
            (if (cnt < maxValue) {
                ++cnt
                txtValue.text = cnt.toString()
                with(mPreferences.edit()){
                    putInt("CNT", cnt)
                    apply()
                }
                valueChangedListener(cnt, maxValue)
            })
        }
    }

    private fun init(attrs: AttributeSet?) {
        inflate(context, R.layout.view_value_selection, this)

        if (context != null) {
            mPreferences = context.getSharedPreferences("ola",Context.MODE_PRIVATE)
        }

        val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.ValueSelectionView)

        try {
            maxValue = attrsArray.getInt(R.styleable.ValueSelectionView_maxValue, 100)
        } finally {
            attrsArray.recycle()
        }

    }

    fun setOnValueChangeListener(listener: (Int, Int) -> Unit) {
        valueChangedListener = listener
    }

    fun clearValue(){
        cnt = 0
        txtValue.text = cnt.toString()
        valueChangedListener(cnt, maxValue)
    }

}