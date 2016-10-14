package lanou.amg1.recommendfragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.main.SendEvent;
import lanou.amg1.search.SearchActivity;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendFragment extends Base_Fragment {


    private FragmentRecommendBean bean;
    private ImageView audio_recommend_ImageView;
    private TabLayout recommend_Tab;
    private ViewPager recommend_ViewPager;
    private ImageView recommend_fragment_search;
    private ImageView recommend_More;

    public RecommendFragment() {



    }

    @Override
    protected void networkRequest() {


        ArrayList<Fragment> arrayList = new ArrayList<>();

        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());
        arrayList.add(new OneRecommendFragment());


        RecommendAdapter recommendAdapter = new RecommendAdapter(getChildFragmentManager());
        recommendAdapter.setArrayList(arrayList);

        recommend_ViewPager.setAdapter(recommendAdapter);

        recommend_Tab.setupWithViewPager(recommend_ViewPager);

        recommend_Tab.setTabMode(TabLayout.MODE_SCROLLABLE);




    }

    @Override
    protected int setLayout() {

        return R.layout.recommend_fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


    @Subscribe

    public void setSendEvent(SendEvent sendEvent){


      ArrayList<String>  arrayList1 = new ArrayList<>();
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


        for (int i = 0; i < arrayList1.size(); i++) {

            if(sendEvent.getContent().equals(arrayList1.get(i))){
                recommend_ViewPager.setCurrentItem(i);
        }


        }





    }

    @Override
    protected void control() {

        EventBus.getDefault().register(this);
        recommend_ViewPager = findById(R.id.recommend_ViewPager);
        recommend_Tab = findById(R.id.recommend_Tab);
        recommend_More = findById(R.id.recommend_More);

        audio_recommend_ImageView = findById(R.id.audio_recommend_ImageView);
        recommend_fragment_search = findById(R.id.recommend_fragment_search);
        recommend_fragment_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("search","搜索关键字");
                startActivity(intent);


            }
        });
        recommend_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(),RecommendMoreActivity.class);

                startActivity(intent);

            }
        });
    }


}
