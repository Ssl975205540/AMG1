package lanou.amg1.viewpageradpter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import lanou.amg1.R;


public class BannerViewPager extends LinearLayout implements Runnable {
    private ViewPager viewPager;
    private LinearLayout viewDots;
    private List<View> views;
    //存放所有的点
    private ImageView[] dotsImageViews ;
    //当前选中点的ImageView
    private ImageView tempImageView;
    //当前选中位置
    private int index = 0;
    //当前是否滚动
    private boolean isContinue = true;
    //Banner点击事件
    private OnSingleTouchListener mListener;
    /** 触摸时按下的点 **/
    private PointF downP = new PointF();
    /** 触摸时当前的点 **/
    private PointF curP = new PointF();
    /**属性*/
    private float dotsViewHeight;// 引导View的高度
    private float dotsSpacing;// 点与点之间的间隔
    private boolean autoChange;// 是否自动切换Banner
    private int changeInterval;// 切换Banner的时间
    private Drawable dotsFocusImage;// 当前选中的Dots
    private Drawable dotsBlurImage;// 未选中的Dots
    private Drawable dotsBackground;// 引导View的背景
    private ImageView.ScaleType scaleType;//view如果的图片的话使用
    private boolean adjustViewBounds;//view如果的图片的话使用
    private int dotsGravity;//
    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.BannerImageViewPager, 0, 0);
        try {
            dotsViewHeight = a.getDimension(
                    R.styleable.BannerImageViewPager_dotsViewHeight,
                    dp2px(context, 45));
            dotsSpacing = a.getDimension(
                    R.styleable.BannerImageViewPager_dotsSpacing,
                    dp2px(context, 10));
            changeInterval = a.getInteger(
                    R.styleable.BannerImageViewPager_changeInterval, 3000);
            autoChange = a.getBoolean(
                    R.styleable.BannerImageViewPager_autoChange, true);
            dotsFocusImage = a
                    .getDrawable(R.styleable.BannerImageViewPager_dotsFocusImage);
            dotsBlurImage = a
                    .getDrawable(R.styleable.BannerImageViewPager_dotsBlurImage);
            dotsBackground = a
                    .getDrawable(R.styleable.BannerImageViewPager_dotsBackground);
            ImageView.ScaleType[] values = ImageView.ScaleType.values();
            int val = a.getInt(R.styleable.BannerImageViewPager_android_scaleType,0);
            scaleType = values[val];
            adjustViewBounds =
                    a.getBoolean(R.styleable.BannerImageViewPager_android_adjustViewBounds, false);
            dotsGravity = a.getInt(R.styleable.BannerImageViewPager_android_gravity, Gravity.CENTER);
        } finally {
            // 回收TypedArray，以便后面重用
            a.recycle();
        }
        InitBannerView();
    }

    private void InitBannerView() {
        viewPager = new ViewPager(getContext());
        viewDots = new LinearLayout(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        addView(viewPager, lp);

        if (dotsBackground != null) {
            viewDots.setBackground(dotsBackground);
        }
        LayoutParams dotlp = new LayoutParams(LayoutParams.MATCH_PARENT,
                (int)dotsViewHeight);
        viewDots.setPadding((int) dotsSpacing, 0, 0, 0);
        viewDots.setGravity(dotsGravity);
        addView(viewDots, dotlp);
    }

    @SuppressWarnings("deprecation")
    public void setViewPagerViews(List<View> views){
        this.views = views;
        dotsImageViews = new ImageView[views.size()];
        addDots(views.size());
        viewPager.setAdapter(new BannerViewPagerAdapter(views,scaleType,adjustViewBounds));

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                super.onPageSelected(position);
                index = position;
                switchToDot(index);
            }

            private void switchToDot(int index) {
                if(tempImageView != null){
                    tempImageView.setImageDrawable(dotsBlurImage);
                }
                dotsImageViews[index].setImageDrawable(dotsFocusImage);
                tempImageView = dotsImageViews[index];
            }
        });

        viewPager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //每次进行onTouch事件都记录当前的按下的坐标
                curP.x = event.getX();
                curP.y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
                        downP.x = event.getX();
                        downP.y = event.getY();

                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        break;

                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        //在up时判断是否按下和松手的坐标为一个点
                        //如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick
                        if(downP.x==curP.x && downP.y==curP.y){
                            mListener.onSingleTouch(index);
                            return true;
                        }
                        break;

                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
        new Thread(this).start();
    }

    private void addDots(int size) {
        for (int i = 0; i < size ; i++) {
            ImageView imageView = new ImageView(getContext());
            LayoutParams params = new LayoutParams(
                    LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, (int)dotsSpacing, 0);
            imageView.setLayoutParams(params);
            dotsImageViews[i] = imageView;
            //默认选中的是第一张图片，此时第一个小圆点是选中状态，其他不是
            if(i==0){
                dotsImageViews[i].setImageDrawable(dotsFocusImage);
                tempImageView = dotsImageViews[i];
            }else{
                dotsImageViews[i].setImageDrawable(dotsBlurImage);
            }
            viewDots.addView(dotsImageViews[i]);
        }
    }

    @Override
    public void run() {
        while (autoChange) {
            if (isContinue) {
                pageHandler.sendEmptyMessage(index);
                index = (index + 1) % views.size();
                try {
                    Thread.sleep(changeInterval);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    Handler pageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            viewPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        View child = this.getChildAt(0);
        child.layout(0, 0, getWidth(), getHeight());
        if (changed) {
            child = this.getChildAt(1);
            child.measure(r - l, (int) dotsViewHeight);
            child.layout(0, getHeight() - (int) dotsViewHeight, getWidth(),
                    getHeight());
        }
    }



    public void setOnSingleTouchListener(OnSingleTouchListener mListener){
        this.mListener = mListener;
    }

    public interface OnSingleTouchListener{
        public void onSingleTouch(int position);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
