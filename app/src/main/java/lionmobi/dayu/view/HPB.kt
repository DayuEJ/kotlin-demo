package lionmobi.dayu.view

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.os.RecoverySystem
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.ly_spl_c1.view.*
import lionmobi.dayu.myapplication.R
import java.util.jar.Attributes

class HPB constructor(context: Context,  attrs : AttributeSet, defStyleAttr : Int): View(context, attrs, defStyleAttr) {

    var mUiThreadId : Long = 0L  //primitive 原始的 远古的  primitive type 原始类型
    var mMode : Int = 0
    var mStrokeWidth : Float = 0f
    var mProgressColor : Int = 0
    var mBackgroundColor : Int = 0;
    // attr for indeterminate
    var mPeriod : Int = 0;
    // attr for
    var mProgressBgColor : Int = 0;
    var mProgress : Float = 0f;
    var mMax : Float = 0f;
    var mMin : Float = 0f;
    var mWidth : Int = 0
    var mHeight : Int = 0;
    var mCenterX : Float = 0f
    var mCenterY : Float = 0f;
    var mRadius : Float = 0f;

    lateinit var mCornorPathEffect : CornerPathEffect;
    lateinit var rectF: RectF
    lateinit var mPolygonPath : Path
    lateinit var mPathMeasure : PathMeasure
    private var mGradStartAngle : Float = -90f
    private var mState = S_INDETERMINATE
    private var mGradFlagAngle : Float = 360f
    lateinit var mProgressAnim : ValueAnimator
    lateinit var mFinishAnim : ValueAnimator
    private var mFinishNow : Boolean = false;
    lateinit var mProgressListerer : ProgressListener;
    private val mVertexArray = arrayOfNulls<PointF>(SIDE_COUNT)

    companion object{
        var SIDE_COUNT : Int = 6;
        var MODE_DETERMINATE : Int = 1;
        var MODE_INDETERMINATE : Int = 2;
        var MODE_DRIVE : Int = 3;
        var S_INDETERMINATE : Int = 0;
        var S_FINISHING : Int =1;
        var S_FINISHED : Int = 2;
    }

    interface ProgressListener {
        fun onProgressEnd()
    }

    init {
        mUiThreadId= Thread.currentThread().id;
        var array : TypedArray = context.obtainStyledAttributes(attrs, R.styleable.HPB, defStyleAttr, 0)
        mStrokeWidth = array.getDimension(R.styleable.HPB_strokeWidth, 4f)
        mProgressColor = array.getColor(R.styleable.HPB_progressColor, 0)
        mProgressBgColor = array.getColor(R.styleable.HPB_progressBgColor, 0)
        mBackgroundColor = array.getColor(R.styleable.HPB_backgroundColor, 0)
        mPeriod = array.getInteger(R.styleable.HPB_period, 3000)
        mMode = array.getInteger(R.styleable.HPB_mode, MODE_DETERMINATE)
        try {
            mProgress = array.getFloat(R.styleable.HPB_progress, 0f)
            mMin = array.getFloat(R.styleable.HPB_android_min, 0f)
            mMax = array.getFloat(R.styleable.HPB_android_max, 100f)
        } catch (e: Exception) {
            mProgress = 0f
            mMin = 0f
            mMax = 100f
        }
        array.recycle()
    }
}