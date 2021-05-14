package com.peninsula.mysunflower.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.ShapeAppearancePathProvider
import com.peninsula.mysunflower.R

class MaskedCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialCardViewStyle
) :
    MaterialCardView(context, attrs, defStyleAttr) {
    private val pathProvider = ShapeAppearancePathProvider()
    private val path: Path = Path()
    private val shapeAppearance = ShapeAppearanceModel.builder(
        context, attrs, defStyleAttr, R.style.Widget_MaterialComponents_CardView
    ).build()

    private val rectF = RectF(0f, 0f, 0f, 0f)

    override fun onDraw(canvas: Canvas) {
        //剪切当前画布
        canvas.clipPath(path)
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rectF.right = w.toFloat()
        rectF.bottom = h.toFloat()
        pathProvider.calculatePath(shapeAppearance, 1f, rectF, path)

        super.onSizeChanged(w, h, oldw, oldh)
    }

}