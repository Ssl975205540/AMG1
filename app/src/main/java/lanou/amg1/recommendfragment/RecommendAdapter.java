package lanou.amg1.recommendfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/8.
 */
public class RecommendAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> arrayList;
    private ArrayList<String> arrayList1;

    public void setArrayList(ArrayList<Fragment> arrayList) {
        this.arrayList = arrayList;

    }

    public RecommendAdapter(FragmentManager fm) {
        super(fm);

        arrayList1 = new ArrayList<>();
        arrayList1.add("推荐");
        arrayList1.add("优创+");
        arrayList1.add("说客");
        arrayList1.add("视频");
        arrayList1.add("快报");
        arrayList1.add("行情");
        arrayList1.add("新闻");
        arrayList1.add("评测");
        arrayList1.add("导购");
        arrayList1.add("用车");
        arrayList1.add("技术");
        arrayList1.add("文化");
        arrayList1.add("改装");

    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {


        return arrayList1.get(position);


    }
}
