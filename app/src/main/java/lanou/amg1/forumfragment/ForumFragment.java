package lanou.amg1.forumfragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.forumfragment.page.OnePageFragment;
import lanou.amg1.forumfragment.page.TwoPageFragment;
import lanou.amg1.search.SearchActivity;


/**
 * Created by dllo on 16/9/20.
 */
public class ForumFragment extends Base_Fragment {


    private ViewPager forumFragmentViewPager;
    private TabLayout forumFragmentTabLayout;
    private ImageView forme_fragment_search;

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
    protected int setLayout() {
        return R.layout.forunfragment;

    }



    @Override
    protected void control() {

        forumFragmentViewPager = findById(R.id.forumFragmentViewPager);
        forumFragmentTabLayout = findById(R.id.forumFragmentTabLayout);
        forme_fragment_search = findById(R.id.forme_fragment_search);
        forme_fragment_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("search","搜索论坛或帖子标题");
                startActivity(intent);

            }
        });
    }
}
