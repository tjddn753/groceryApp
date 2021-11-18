package com.example.projectone

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.example.projectone.databinding.ActivitySpashRocketAnimationBinding


class SplashRocketAnimation : AppCompatActivity() {
    lateinit var binding:ActivitySpashRocketAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpashRocketAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val valueAnimator = ValueAnimator.ofFloat(0f, -2100f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            binding.rocketImageview.translationY = value
           // binding.rocketImageview.rotation = value
        }

        val rotationAnimator = ObjectAnimator.ofFloat(binding.rocketImageview,"rotation",0f,360f)

        val animationSet = AnimatorSet()
        animationSet.play(valueAnimator).with(rotationAnimator)
     //   valueAnimator.interpolator = LinearInterpolator()
        animationSet.interpolator = AccelerateInterpolator()
        animationSet.duration = 5000
        animationSet.start()
        animationSet.doOnEnd { val intent= Intent(this@SplashRocketAnimation,MainActivity::class.java)
            startActivity(intent) }




    }

  /*  companion object {
        const val DEFAULT_ANIMATION_VAL = 500L
    }*/
}