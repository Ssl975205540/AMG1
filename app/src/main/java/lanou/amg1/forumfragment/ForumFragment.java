package lanou.amg1.forumfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.forumfragment.page.OnePageFragment;
import lanou.amg1.forumfragment.page.TwoPageFragment;


/**
 * Created by dllo on 16/9/20.
 */
public class ForumFragment extends Base_Fragment {


    private ViewPager forumFragmentViewPager;
    private TabLayout forumFragmentTabLayout;

    @Override
    protected void networkRequest() {


       ForumFragmentAdapter forumFragmentAdapter = new ForumFragmentAdapter(getChildFragmentManager());

        ArrayList<Fragment> arrayList = new ArrayList<>();

        arrayList.add(new OnePageFragment());

        arrayList.add(new TwoPageFragment());


        forumFragmentAdapter.setArrayList(arrayList);

        forumFragmentViewPager.setAdapter(forumFragmentAdapter);

        forumFragmentTabLayout.setupWithViewPager(forumFragmentViewPager);




    }

    @Override
    protected View setLayout(LayoutInflater inflater) {

        return inflater.inflate(R.layout.forunfragment,null);

    }

    @Override
    protected void control() {

        forumFragmentViewPager = findById(R.id.forumFragmentViewPager);
        forumFragmentTabLayout = findById(R.id.forumFragmentTabLayout);

    }
}
