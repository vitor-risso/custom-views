package com.example.customviews.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customviews.R
import kotlin.math.min

class DonutChartView : View {

    private var viewWidth = 0
    private var viewHeight = 0

    private var bgColor = Color.LTGRAY
    private var arcColor = Color.YELLOW
    private var holeColor = Color.WHITE

    private var currentAngle = 0f

    val paint = Paint()

    constructor(context: Context?) : super(context){
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(attrs)
    }

    fun init(attrs: AttributeSet?) {
        val attrArray = context.obtainStyledAttributes(attrs, R.styleable.DonutChartView)

        try {
            bgColor = attrArray.getColor(R.styleable.DonutChartView_bgColor, bgColor)
            arcColor = attrArray.getColor(R.styleable.DonutChartView_arcColor, arcColor)
            holeColor = attrArray.getColor(R.styleable.DonutChartView_holeColor, holeColor)
        } finally {
            attrArray.recycle()
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(viewWidth, viewHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        canvas?.let{
//            it.drawFilter
//        }

        canvas?.apply {

            paint.color = bgColor
            drawCircle(
                viewWidth / 2f,
                viewHeight / 2f,
                min(viewHeight / 2, viewWidth / 2).toFloat(),
                paint
            )

            paint.color = arcColor
            drawArc(
                0f,
                0f,
                viewWidth.toFloat(),
                viewHeight.toFloat(),
                0f,
                currentAngle.toFloat(),
                true,
                paint
            )

            paint.color = holeColor
            drawCircle(
                viewWidth / 2f,
                viewHeight / 2f,
                min(viewHeight / 4, viewWidth / 4).toFloat(),
                paint
            )
        }
    }

    fun updateAngels(angle: Float){
        currentAngle = angle
        invalidate()

    }

}