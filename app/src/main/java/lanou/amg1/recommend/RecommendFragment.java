package lanou.amg1.recommend;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.base.BaseFragment;
import lanou.amg1.bean.RcdFgtBean;
import lanou.amg1.search.SearchAty;
import lanou.amg1.tools.MySQLite;
import lanou.amg1.tools.SendEvent;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendFragment extends BaseFragment {


    private RcdFgtBean bean;
    private ImageView audio_recommend_ImageView;
    private TabLayout recommend_Tab;
    private ViewPager recommend_ViewPager;
    private ImageView recommend_fragment_search;
    private ImageView recommend_More;
    private RcdAdp rcdAdp;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<FragBean> arrayList;
    private Fragment[] fragment;
    private String[] title;


    @Override
    protected void initData() {

        title = new String[]{"推荐", "优创+", "说客", "视频", "快报", "行情", "新闻", "评测", "导购", "用车", "技术", "文化", "改装"};
        MySQLite mySQLite = new MySQLite(context, "search.db", null, 1);
        sqLiteDatabase = mySQLite.getReadableDatabase();
        arrayList = new ArrayList<>();
        fragment = new Fragment[13];
        for (int i = 0; i < fragment.length; i++) {

            if (i == 3) {
                fragment[i] = new VideoRcdFragment();


            } else {
                fragment[i] = new OneRcdFragment();
            }
        }

        notyLayout();
        rcdAdp = new RcdAdp(getChildFragmentManager());
//
        rcdAdp.setArrayList(arrayList);
//
        recommend_ViewPager.setAdapter(rcdAdp);

        recommend_Tab.setupWithViewPager(recommend_ViewPager);

        recommend_Tab.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    //    }
    @Override
    protected int setContentView() {

        return R.layout.fragment_recommend;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


    @Subscribe

    public void setSendEvent(SendEvent sendEvent) {

        if (sendEvent.getChoice() == 14) {

//            for (int i = 0; i < arrayList.size(); i++) {
//
//                if (sendEvent.getContent().equals(arrayList.get(i).getTitle())) {
//
//                    for (int j = 0; j < arrayList.size() ; j++) {
//
//                        if(arrayList.get(i).getFragment().equals(arrayList.get(j).getFragment())){
//                            Log.d("RecommendFragment", "j:" + j);
//
//
//                        }
//
//                    }
//
//
//                }
//
//
//            }
        }


        if (sendEvent.getChoice() == 15) {


            arrayList.clear();
            notyLayout();

            rcdAdp = new RcdAdp(getChildFragmentManager());
            rcdAdp.setArrayList(arrayList);
            recommend_ViewPager.setAdapter(rcdAdp);
            recommend_Tab.setupWithViewPager(recommend_ViewPager);


        }
    }

    private void notyLayout() {
        Cursor cursor = sqLiteDatabase.query("more", null, null, null, null, null, null);
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {


                String name = cursor.getString(cursor.getColumnIndex("name"));
                FragBean fragBean = new FragBean();
                if (name.equals("游记")) {
                    fragBean.setTitle("推荐");
                    fragBean.setFragment(fragment[0]);
                    arrayList.add(fragBean);
                }
                if (name.equals("优创+")) {

                    fragBean.setFragment(fragment[1]);
                    fragBean.setTitle("优创+");
                    arrayList.add(fragBean);
                }
                if (name.equals("说客")) {

                    fragBean.setFragment(fragment[2]);
                    fragBean.setTitle("说客");
                    arrayList.add(fragBean);
                }
                if (name.equals("视频")) {
                    fragBean.setFragment(fragment[3]);
                    fragBean.setTitle("视频");
                    arrayList.add(fragBean);
                }

                if (name.equals("快报")) {


                    fragBean.setFragment(fragment[4]);
                    fragBean.setTitle("快报");
                    arrayList.add(fragBean);
                }
                if (name.equals("行情")) {
                    fragBean.setFragment(fragment[5]);
                    fragBean.setTitle("行情");
                    arrayList.add(fragBean);
                }
                if (name.equals("新闻")) {
                    fragBean.setFragment(fragment[6]);
                    fragBean.setTitle("新闻");
                    arrayList.add(fragBean);
                }
                if (name.equals("评测")) {
                    fragBean.setFragment(fragment[7]);
                    fragBean.setTitle("评测");
                    arrayList.add(fragBean);
                }
                if (name.equals("导购")) {
                    fragBean.setFragment(fragment[8]);
                    fragBean.setTitle("导购");
                    arrayList.add(fragBean);
                }
                if (name.equals("用车")) {
                    fragBean.setFragment(fragment[9]);
                    fragBean.setTitle("用车");
                    arrayList.add(fragBean);
                }
                if (name.equals("技术")) {
                    fragBean.setFragment(fragment[10]);
                    fragBean.setTitle("技术");
                    arrayList.add(fragBean);
                }
                if (name.equals("文化")) {
                    fragBean.setFragment(fragment[11]);
                    fragBean.setTitle("文化");
                    arrayList.add(fragBean);
                }
                if (name.equals("改装")) {
                    fragBean.setFragment(fragment[12]);
                    fragBean.setTitle("改装");
                    arrayList.add(fragBean);
                }

            }

            Log.d("RecommendMoreAty", "这1");

        }


        if (cursor.getCount() == 0) {


            for (int i = 0; i < fragment.length; i++) {

                if (i == 3) {

                    FragBean fragBean = new FragBean();
                    fragBean.setFragment(fragment[i]);
                    fragBean.setTitle(title[i]);
                    arrayList.add(fragBean);

                } else {

                    FragBean fragBean = new FragBean();
                    fragBean.setFragment(fragment[i]);
                    fragBean.setTitle(title[i]);
                    arrayList.add(fragBean);
                }


            }


        }


    }

    @Override
    protected void initViews() {

        EventBus.getDefault().register(this);
        recommend_ViewPager = findById(R.id.recommend_ViewPager);
        recommend_Tab = findById(R.id.recommend_Tab);
        recommend_More = findById(R.id.recommend_More);
        audio_recommend_ImageView = findById(R.id.audio_recommend_ImageView);
        recommend_fragment_search = findById(R.id.recommend_fragment_search);


    }

    @Override
    protected void initListeners() {

        recommend_fragment_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchAty.class);
                intent.putExtra("search", "搜索关键字");
                startActivity(intent);


            }
        });
        recommend_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), RecommendMoreAty.class);

                startActivityForResult(intent,100);

            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(100 == requestCode && 101 == resultCode){


            recommend_ViewPager.setCurrentItem(data.getIntExtra("position",0));


        }

    }

}
