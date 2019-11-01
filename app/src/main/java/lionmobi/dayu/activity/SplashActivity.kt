package lionmobi.dayu.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import lionmobi.dayu.myapplication.R

class SplashActivity : BaseActivity(){
    lateinit var mProgressAnim : ValueAnimator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spl)
        startInIt();
    }

    fun startInIt(){
        mProgressAnim = ValueAnimator.ofInt(0, 100);
        mProgressAnim.interpolator = LinearInterpolator();
        mProgressAnim.duration = 8000;
        mProgressAnim.addUpdateListener({ animation: ValueAnimator ->
            run {
                findViewById<ProgressBar>(R.id.pb_loading).setProgress(animation.animatedValue as Int)
            }
        })
        mProgressAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        })
        mProgressAnim.start();
    }
}