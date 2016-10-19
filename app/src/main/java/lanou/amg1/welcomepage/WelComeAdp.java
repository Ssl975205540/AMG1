package lanou.amg1.welcomepage;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class WelComeAdp extends PagerAdapter {

    public void setArrayList(ArrayList<View> arrayList) {
        this.arrayList = arrayList;
    }

    private ArrayList<View> arrayList;
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {

        ((ViewPager) arg0).removeView(arrayList.get(arg1));
    }
    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(arrayList.get(arg1),0);
        return arrayList.get(arg1);
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(object);
    }
}
