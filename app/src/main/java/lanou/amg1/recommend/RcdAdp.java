package lanou.amg1.recommend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/8.
 */
public class RcdAdp extends FragmentStatePagerAdapter{


    private ArrayList<FragBean> arrayList;

    public void setArrayList(ArrayList<FragBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }


    public RcdAdp(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return arrayList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {


        return arrayList.get(position).getTitle();


    }
}
