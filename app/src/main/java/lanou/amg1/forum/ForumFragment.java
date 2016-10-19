package lanou.amg1.forum;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.base.BaseFragment;
import lanou.amg1.forum.page.OnePageFragment;
import lanou.amg1.forum.page.TwoPageFragment;
import lanou.amg1.search.SearchAty;


/**
 * Created by dllo on 16/9/20.
 */
public class ForumFragment extends BaseFragment {


    private ViewPager forumFragmentViewPager;
    private TabLayout forumFragmentTabLayout;
    private ImageView forme_fragment_search;

    @Override
    protected void initData() {


       ForumFgtAdp forumFgtAdp = new ForumFgtAdp(getChildFragmentManager());

        ArrayList<Fragment> arrayList = new ArrayList<>();

        arrayList.add(new OnePageFragment());

        arrayList.add(new TwoPageFragment());


        forumFgtAdp.setArrayList(arrayList);

        forumFragmentViewPager.setAdapter(forumFgtAdp);

        forumFragmentTabLayout.setupWithViewPager(forumFragmentViewPager);




    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_forun;

    }



    @Override
    protected void initViews() {

        forumFragmentViewPager = findById(R.id.forumFragmentViewPager);
        forumFragmentTabLayout = findById(R.id.forumFragmentTabLayout);
        forme_fragment_search = findById(R.id.forme_fragment_search);

    }

    @Override
    protected void initListeners() {
        forme_fragment_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchAty.class);
                intent.putExtra("search","搜索论坛或帖子标题");
                startActivity(intent);

            }
        });
    }
}
