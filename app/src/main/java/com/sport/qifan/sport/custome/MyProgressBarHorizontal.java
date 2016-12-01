package com.sport.qifan.sport.custome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.sport.qifan.sport.R;
import com.sport.qifan.sport.utils.DensityUtil;

/**
 * Created by qifan on 2016/11/30.
 */

public class MyProgressBarHorizontal extends ProgressBar {

    private static final int DEFAUT_COLOR_UNREACH = 0XFFD3D6DA;
    private static final int DEFAUT_HEIGHT_UNREACH = 2; //DP
    private static final int DEFAUT_COLOR_REACH = 0XFFFC00D1;
    private static final int DEFAUT_HEIGHT_REACH = 2;

    private int mReachColor = DEFAUT_COLOR_REACH;
    private int mReachHeight = DEFAUT_HEIGHT_REACH;
    private int mUnReachColor = DEFAUT_COLOR_UNREACH;
    private int mUnReachHeight = DEFAUT_HEIGHT_UNREACH;

    //渐变颜色的配置
    private boolean needGradient = true;
    private int mStartColor = Color.BLUE;
    //    private int mMiddleColor = Color.GREEN;
    private int mEndColor = Color.GREEN;

    private Context context;
    private Shader mShader;
    private int mRealWidth;
    private Paint mReachPaint = new Paint();
    private Paint mUnReachPaint = new Paint();

    public MyProgressBarHorizontal(Context context) {
        this(context, null);
    }

    public MyProgressBarHorizontal(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressBarHorizontal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        obtainStyleAttrs(attrs);
    }

    private void obtainStyleAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyProgressBarHorizontal);
        mReachColor = ta.getColor(R.styleable.MyProgressBarHorizontal_reachColor, DEFAUT_COLOR_REACH);
        mUnReachColor = ta.getColor(R.styleable.MyProgressBarHorizontal_unReachColor, DEFAUT_COLOR_UNREACH);
        mReachHeight = (int) ta.getDimension(R.styleable.MyProgressBarHorizontal_reachHeight, DensityUtil.dp2px(context, DEFAUT_HEIGHT_REACH));
        mUnReachHeight = (int) ta.getDimension(R.styleable.MyProgressBarHorizontal_unReachHeight, DensityUtil.dp2px(context, DEFAUT_HEIGHT_UNREACH));
        needGradient = ta.getBoolean(R.styleable.MyProgressBarHorizontal_gradient, false);
        ta.recycle();
        //新建一个线性渐变，前两个参数是渐变开始的点坐标，
        // 第三四个参数是渐变结束的点的坐标。连接这2个点就拉出一条渐变线了，玩过PS的都懂。
        // 然后那个数组是渐变的颜色。
        //下一个参数是渐变颜色的分布，如果为空，每个颜色就是均匀分布的。
        // 最后是模式，这里设置的是循环渐变
        mShader = new LinearGradient(0, 0, 200, 0, mStartColor, mEndColor, Shader.TileMode.MIRROR);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 测量高
     * 重写onMeasure方法时要根据模式不同进行尺寸计算。下面代码就是一种比较典型的方式：
     *
     * @param measureSpec
     * @return
     */
    private int measureHeight(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int result = 0;
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            //result=2图片最高的那个 + 2个padding 这是自己定义的
            result = Math.max(mReachHeight, mUnReachHeight) + getPaddingTop() + getPaddingBottom();
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(size, result);
            }
        }
        return result;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //用来保存Canvas的状态。save之后，可以调用Canvas的平移、放缩、旋转、错切、裁剪等操作。
        canvas.save();
        canvas.translate(getPaddingLeft(), 0);
        //draw reach bar
        boolean needUnReach = false;
        float radio = getProgress() * 1.0f / getMax();
        if (radio < 1) {
            needUnReach = true;
        }
        //draw each bar
        float endX = mRealWidth * radio;
        if (endX > 0) {
            //画笔设置填充风格为实心
            mReachPaint.setStyle(Paint.Style.FILL);
            //设置抗锯齿
            mReachPaint.setAntiAlias(true);
            if (needGradient) {
                mReachPaint.setShader(mShader);
            } else {
                mReachPaint.setColor(mReachColor);
            }
            canvas.drawPath(drawReachBarPath(endX), mReachPaint);
        }
        //draw unreach bar
        if (needUnReach) {
            mUnReachPaint.setColor(mUnReachColor);
            mUnReachPaint.setStyle(Paint.Style.FILL);//设置实心
            mUnReachPaint.setAntiAlias(true);
            canvas.drawPath(drawUnReachBarPath(endX), mUnReachPaint);
        }
        //来恢复Canvas之前保存的状态。防止save后对Canvas执行的操作对后续的绘制有影响。
        canvas.restore();
    }

    private Path drawUnReachBarPath(float endX) {
        int r_cycle = getHeight() / 2;
        Path path = new Path();
        if (endX > 0) {
            path.moveTo(endX - r_cycle, 0);
            path.lineTo(mRealWidth - r_cycle, 0);
            path.quadTo(mRealWidth - r_cycle, r_cycle, mRealWidth - r_cycle, getHeight());
            path.lineTo(endX - r_cycle, getHeight());
            path.quadTo(endX-r_cycle,r_cycle,endX-r_cycle,0);
//            path.moveTo(endX - r_cycle, 0);
//            path.quadTo(endX, r_cycle, endX - r_cycle, getHeight()); //赛贝尔曲线画圆弧
//            path.lineTo(mRealWidth - r_cycle, getHeight());
//            path.quadTo(mRealWidth, r_cycle, mRealWidth - r_cycle, 0); //赛贝尔曲线画圆弧
//            path.lineTo(mRealWidth, 0);
            path.close();
        } else {
            path.moveTo(r_cycle, getHeight());
            path.lineTo(mRealWidth - r_cycle, getHeight());
            path.quadTo(mRealWidth - r_cycle, r_cycle, mRealWidth - r_cycle, 0);
            path.lineTo(r_cycle, 0);
            path.quadTo(0, r_cycle, r_cycle, getHeight());
            path.close();
        }
        return path;
    }

    //进度条边上圆弧赛贝尔曲线的执行点
    private Path drawReachBarPath(float endX) {
        int r_cycle = getHeight() / 2;
        Path path = new Path();
        //设置Path的起点
        path.moveTo(r_cycle, 0);
        // 添加上一个点到当前点之间的直线到Path
        path.lineTo(endX - r_cycle, 0);
        //二节
        path.quadTo(endX, r_cycle, endX - r_cycle, getHeight()); //赛贝尔曲线画圆弧
        path.lineTo(r_cycle, getHeight());
        path.quadTo(0, r_cycle, r_cycle, 0); //赛贝尔曲线画圆弧
        path.close();//封闭
        return path;
    }
}
