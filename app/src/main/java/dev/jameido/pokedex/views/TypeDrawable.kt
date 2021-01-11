package dev.jameido.pokedex.views

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

/**
 * Created by Jameido on 03/01/2021.
 */
class TypeDrawable : Drawable {
    private val topPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bottomPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val topPath = Path()
    private val bottomPath = Path()
    private var cornerRadius = 0F

    constructor(@ColorInt topColor: Int, @ColorInt bottomColor: Int) : super() {
        topPaint.color = topColor
        bottomPaint.color = bottomColor
    }

    constructor(@ColorInt color: Int) : this(color, color)

    override fun onBoundsChange(bounds: Rect) {
        rebuildPaths(bounds.left.toFloat(), bounds.top.toFloat(), bounds.width().toFloat(), bounds.height().toFloat(), cornerRadius)
        super.onBoundsChange(bounds)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(topPath, topPaint)
        canvas.drawPath(bottomPath, bottomPaint)
    }

    override fun setAlpha(alpha: Int) {
        topPaint.alpha = alpha
        bottomPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        topPaint.colorFilter = colorFilter
        bottomPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }

    fun setCornerRadius(radius: Float) {
        cornerRadius = radius
        rebuildPaths(bounds.left.toFloat(), bounds.top.toFloat(), bounds.width().toFloat(), bounds.height().toFloat(), cornerRadius)
        invalidateSelf()
    }

    fun setColors(@ColorInt topColor: Int, @ColorInt bottomColor: Int) {
        topPaint.color = topColor
        bottomPaint.color = bottomColor
        invalidateSelf()
    }

    fun setTopColor(@ColorInt topColor: Int) {
        topPaint.color = topColor
        invalidateSelf()
    }

    fun setBottomColor(@ColorInt bottomColor: Int) {
        bottomPaint.color = bottomColor
        invalidateSelf()
    }

    private fun rebuildPaths(left: Float, top: Float, width: Float, height: Float, radius: Float) {
        topPath.reset()
        topPath.moveTo(left, top + radius)
        topPath.rQuadTo(0F, -radius, radius, -radius)
        topPath.rLineTo(width - 2 * radius, 0F)
        topPath.rQuadTo(radius, 0F, radius, radius)
        topPath.rLineTo(0F, height / 2 - radius + 1) //The +1 is necessary for rounding numbers
        topPath.rLineTo(-width, 0F)
        topPath.close()

        bottomPath.reset()
        bottomPath.moveTo(left, top + height / 2)
        bottomPath.rLineTo(width, 0F)
        bottomPath.rLineTo(0F, height / 2 - radius)
        bottomPath.rQuadTo(0F, radius, -radius, radius)
        bottomPath.rLineTo(-(width - 2 * radius), 0F)
        bottomPath.rQuadTo(-radius, 0F, -radius, -radius)
        bottomPath.close()
    }

}