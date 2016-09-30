package lanou.amg1.viewpageradpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import lanou.amg1.R;
import lanou.amg1.recommendfragment.FragmentRecommendBean;

/**
 * Created by dllo on 16/9/21.
 */
public class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

    private FragmentRecommendBean bean;

    public void setBean(FragmentRecommendBean bean) {
        this.bean = bean;
    }

    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

@Override
public int getCount() {
        return bean.getResult().getFocusimg().size();
        }

@Override
public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
        }

@Override
public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
    Picasso.with(mContext).load(bean.getResult().getFocusimg().get(position).getImgurl()).into(imageView);


        container.addView(itemView);

        return itemView;
        }

@Override
public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);

}


        }