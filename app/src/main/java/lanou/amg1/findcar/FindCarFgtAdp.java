package lanou.amg1.findcar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/21.
 */
public class FindCarFgtAdp extends FragmentPagerAdapter{

    private ArrayList<Fragment> arrayList;
    private ArrayList<String> arrayList1;


    public void setArrayList(ArrayList<Fragment> arrayList) {
        this.arrayList = arrayList;

        arrayList1 = new ArrayList<>();

        arrayList1.add("新车");
        arrayList1.add("二手车");

    }

    public FindCarFgtAdp(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {





        return arrayList1.get(position);



    }
}
