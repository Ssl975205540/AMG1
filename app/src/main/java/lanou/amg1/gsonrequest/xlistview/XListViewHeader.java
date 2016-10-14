package lanou.amg1.gsonrequest.xlistview;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lanou.amg1.R;


public class XListViewHeader extends LinearLayout {
	private LinearLayout mContainer;
//	private ImageView mArrowImageView;
	private ImageView mProgressBar;
	private TextView mHintTextView;
	private int mState = STATE_NORMAL;// 初始状态

	private Animation mRotateUpAnim;
	private Animation mRotateDownAnim;

	private final int ROTATE_ANIM_DURATION = 180;

	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_REFRESHING = 2;
	private ImageView ahlib_common_pull_refresh_frame_01;

	public XListViewHeader(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public XListViewHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		// 初始情况，设置下拉刷新view高度为0
		LayoutParams lp = new LayoutParams(
				LayoutParams.FILL_PARENT, 0);
		// 时间TextView
		mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.xlistview_header, null);
		addView(mContainer, lp);
		setGravity(Gravity.BOTTOM);
		// 找到头部页面里的控件
//		mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
		mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);
		mProgressBar = (ImageView) findViewById(R.id.xlistview_header_progressbar);

		ahlib_common_pull_refresh_frame_01 = (ImageView) mContainer.findViewById(R.id.ahlib_common_pull_refresh_frame_01);
		mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateUpAnim.setFillAfter(true);
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateDownAnim.setFillAfter(true);
	}

	// 设置状态
	public void setState(int state) {
		if (state == mState)
			return;

		if (state == STATE_REFRESHING) { // 显示进度
//			mArrowImageView.clearAnimation();
//			mArrowImageView.setVisibility(View.INVISIBLE);// 不显示图片
			((AnimationDrawable)mProgressBar.getBackground()).start();
			mProgressBar.setVisibility(View.VISIBLE);// 显示进度条
		} else { // 显示箭头图片
//			mArrowImageView.setVisibility(View.VISIBLE);
			mProgressBar.setVisibility(View.INVISIBLE);
		}

		switch (state) {
		case STATE_NORMAL:
			if (mState == STATE_READY) {// 当状态时准备的时候，显示动画
//				mArrowImageView.startAnimation(mRotateDownAnim);
			}
			if (mState == STATE_REFRESHING) {// 当状态显示进度条的时候，清除动画
//				mArrowImageView.clearAnimation();
			}

			ahlib_common_pull_refresh_frame_01.setVisibility(VISIBLE);
			mHintTextView.setText("下拉刷新");// 文字提示：下拉刷新
			break;
		case STATE_READY:
			if (mState != STATE_READY) {
//				mArrowImageView.clearAnimation();
//				mArrowImageView.startAnimation(mRotateUpAnim);
				mHintTextView.setText("松开刷新");// 松开刷新数据
			}
			break;
		case STATE_REFRESHING:
			ahlib_common_pull_refresh_frame_01.setVisibility(GONE);
			mHintTextView.setText("刷新中...");
			break;
		default:
		}

		mState = state;
	}

	public void setVisiableHeight(int height) {
		if (height < 0)
			height = 0;
		LayoutParams lp = (LayoutParams) mContainer
				.getLayoutParams();
		lp.height = height;
		mContainer.setLayoutParams(lp);
	}

	public int getVisiableHeight() {
		return mContainer.getHeight();
	}

}