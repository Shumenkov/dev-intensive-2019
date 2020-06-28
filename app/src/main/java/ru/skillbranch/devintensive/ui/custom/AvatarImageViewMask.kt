package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView

class AvatarImageViewMask @JvmOverloads constructor(
    context:Context,
    attrs:AttributeSet? = null,
    defStyleAttrs: Int = 0
): ImageView(context, attrs,defStyleAttrs)
{
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d("M_", "onAttachedToWindow")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d("M_", "onMeasure")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d("M_", "onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("M_", "onDraw")
    }
}