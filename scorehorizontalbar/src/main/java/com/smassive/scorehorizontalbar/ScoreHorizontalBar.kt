package com.smassive.scorehorizontalbar

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.TextView

class ScoreHorizontalBar : FrameLayout {

  companion object {
    private const val ANIMATION_DURATION = 500L
  }

  private val view: View
  private val argbEvaluator = ArgbEvaluator()
  private val accelerateDecelerateInterpolator = AccelerateDecelerateInterpolator()

  constructor(context: Context) : this(context, null)

  constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    view = LayoutInflater.from(context).inflate(R.layout.shb_view_score_bar, this, false)
    addView(view)
  }

  fun render(score: Float) {
    view.findViewById<TextView>(R.id.shb_score).text = score.toString()
    with(view.findViewById<View>(R.id.shb_score_bar)) {
      when (score * 10) {
        in 0..20 -> {
          animate(this, context.resources.getDimensionPixelSize(R.dimen.shb_score_bar_level1_width),
              ContextCompat.getColor(context, R.color.shb_score_bar_background_level1_color))
        }
        in 20..40 -> {
          animate(this, context.resources.getDimensionPixelSize(R.dimen.shb_score_bar_level2_width),
              ContextCompat.getColor(context, R.color.shb_score_bar_background_level2_color))
        }
        in 40..60 -> {
          animate(this, context.resources.getDimensionPixelSize(R.dimen.shb_score_bar_level3_width),
              ContextCompat.getColor(context, R.color.shb_score_bar_background_level3_color))
        }
        in 60..80 -> {
          animate(this, context.resources.getDimensionPixelSize(R.dimen.shb_score_bar_level4_width),
              ContextCompat.getColor(context, R.color.shb_score_bar_background_level4_color))
        }
        in 80..100 -> {
          background = ContextCompat.getDrawable(context, R.drawable.shb_score_bar_radius_background)
          animate(this, context.resources.getDimensionPixelSize(R.dimen.shb_score_bar_level5_width),
              ContextCompat.getColor(context, R.color.shb_score_bar_background_level5_color))
        }
      }
    }
  }

  private fun animate(view: View, width: Int, color: Int) {
    AnimatorSet()
        .apply {
          duration = ANIMATION_DURATION
          interpolator = accelerateDecelerateInterpolator
          playTogether(widthAnimator(view, width), colorAnimator(view, color))
        }
        .start()
  }

  private fun widthAnimator(view: View, width: Int): ValueAnimator {
    return ValueAnimator.ofInt(0, width)
        .apply {
          addUpdateListener {
            view.layoutParams.width = it.animatedValue as Int
            view.requestLayout()
          }
        }
  }

  private fun colorAnimator(view: View, color: Int): ValueAnimator {
    return ValueAnimator.ofInt(ContextCompat.getColor(context, R.color.shb_score_bar_background_level1_color), color)
        .apply {
          setEvaluator(argbEvaluator)
          addUpdateListener {
            (view.background as GradientDrawable).setColor(it.animatedValue as Int)
          }
        }
  }
}