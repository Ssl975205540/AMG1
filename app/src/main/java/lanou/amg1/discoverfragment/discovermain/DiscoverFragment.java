package lanou.amg1.discoverfragment.discovermain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.discoverfragment.discoverheadler_forme_recyclerview.ForMe_RecyclerView;
import lanou.amg1.discoverfragment.horizontallistview.HorizontalListView;
import lanou.amg1.discoverfragment.horizontallistview.HorizontalListViewAdapter;
import lanou.amg1.discoverfragment.horizontallistview.LimitedBuyTextView;
import lanou.amg1.discoverfragment.lovely.LoveLyRecyclerView;
import lanou.amg1.discoverfragment.recyclerviewnavigation.OnRecyclerltemClickListener;
import lanou.amg1.discoverfragment.recyclerviewnavigation.RecyclerViewNavigationAdapter;
import lanou.amg1.discoverfragment.recyclerviewsector.RecyclerViewServerAdapter;
import lanou.amg1.forumfragment.page.onepagefirst.OnePageFirstActivity;
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.VolleySingleton;
import lanou.amg1.search.SearchActivity;
import lanou.amg1.urlall.URLAll;


/**
 * Created by dllo on 16/9/20.
 */
public class DiscoverFragment extends Base_Fragment {

    private PullToRefreshListView listView_DiscoverFragment;
    private HorizontalListView discoverHorizontalListView;
    private RecyclerView recyclerViewDiscoverHeaderNavigation;
    private RecyclerView recyclerViewDiscoverHeader_Sector;
    private ImageView discoverHeader_Advertisement_ImageView;
    private ImageView activity_Zone_First_ImageView;
    private ImageView activity_Zone_Second_ImageView;
    private ImageView activity_Zone_Third_ImageView;
    private ImageView Singleframe_Button_ImageView;
    private RecyclerView discoverHeadler_Lovely_RecyclerView;
    private RecyclerView discoverHeadler_ForMe_RecyclerView;
    private View view;
    private SharedPreferences.Editor casc;
    private Banner discoverHeader_Advertisement_ViewPager;

    private static DiscoverBean bean;
    private List<String> mList;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    //广告图素材


    // 线程标志
    private boolean isStop = false;
    private Boolean mm = true;
    private RelativeLayout disHeader_RelativeLayout;
    private ImageView discover_fragment_search;


    @Override
    protected void networkRequest() {

        this.access();


        listView_DiscoverFragment.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                GsonRequest<DiscoverBean> gsonRequest1 = new GsonRequest<DiscoverBean>(URLAll.DISCOVERY_PAGE, DiscoverBean.class, new Response.Listener<DiscoverBean>() {
                    @Override
                    public void onResponse(DiscoverBean bean) {

                        for (int i = 0; i < bean.getResult().getCardlist().size() - 1; i++) {

                            if (bean.getResult().getCardlist().get(i).getDescription().equals("猜你喜欢")) {
                                DiscoverAdapter pageListViewAdapter = new DiscoverAdapter(getContext());
                                pageListViewAdapter.setBean(bean, i);
                                listView_DiscoverFragment.setAdapter(pageListViewAdapter);
                            }
                        }

                        listView_DiscoverFragment.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

                VolleySingleton.getInstance().addRequest(gsonRequest1);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                increase
//                das = das + 30;
//                String im = das+"";
//
//                String url1 = "http://223.99.255.20/clubnc.app.autohome.com.cn/club_v7.0.5/club/jingxuantopic.ashx?platud=2&categoryid=0&pageindex=1&pagesize=30&devicetype=android.1501_M02&deviceid=860954030358581&userid=0&operation=1&netstate=0&gps=38.889726%2C121." + im;
//
//                GsonRequest<OnePageFragmentBean> onePageFragmentBeanGsonRequest = new GsonRequest<OnePageFragmentBean>(url1, OnePageFragmentBean.class, new Response.Listener<OnePageFragmentBean>() {
//                    @Override
//                    public void onResponse(OnePageFragmentBean bean) {
//
//                        OnPageListViewAdapter pageListViewAdapter = new OnPageListViewAdapter(getContext());
//
//
//                        pageListViewAdapter.setBean(bean);
//
//                        onePageListView.setAdapter(pageListViewAdapter);
//                        onePageListView.onRefreshComplete();
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//
//                VolleySingleton.getInstance().addRequest(onePageFragmentBeanGsonRequest);
//
//
//
//
//                onePageListView.onRefreshComplete();
            }


        });


    }

    @Override
    protected int setLayout() {

        return R.layout.discover_fragment;
    }


    @Override
    protected void control() {


//        discoverHeader_fragment = findFragmentByid(R.id.discoverHeader_fragment);

        view = LayoutInflater.from(getActivity()).inflate(R.layout.discoverfragment_header, null);


        disHeader_RelativeLayout = findById(R.id.disHeader_RelativeLayout, view);

        discover_fragment_search = findById(R.id.discover_fragment_search);
        discover_fragment_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("search", "搜索车商城");
                startActivity(intent);


            }
        });
        discoverHeader_Advertisement_ViewPager = findById(R.id.discoverHeader_Advertisement_ViewPager, view);
        Singleframe_Button_ImageView = findById(R.id.Singleframe_Button_ImageView, view);
        activity_Zone_First_ImageView = findById(R.id.activity_Zone_First_ImageView, view);
        activity_Zone_Second_ImageView = findById(R.id.activity_Zone_Second_ImageView, view);
        activity_Zone_Third_ImageView = findById(R.id.activity_Zone_Third_ImageView, view);
        discoverHeader_Advertisement_ImageView = findById(R.id.discoverHeader_Advertisement_ImageView, view);
        listView_DiscoverFragment = findById(R.id.listView_DiscoverFragment);
        discoverHeadler_ForMe_RecyclerView = findById(R.id.discoverHeadler_ForMe_RecyclerView, view);
        recyclerViewDiscoverHeader_Sector = findById(R.id.recyclerViewDiscoverHeader_Sector, view);
        discoverHeadler_Lovely_RecyclerView = findById(R.id.discoverHeadler_Lovely_RecyclerView, view);
        discoverHorizontalListView = findById(R.id.discoverHorizontalListView, view);
        recyclerViewDiscoverHeaderNavigation = findById(R.id.recyclerViewDiscoverHeaderNavigation, view);

        listView_DiscoverFragment.getRefreshableView().addHeaderView(view);

    }


    private void access() {


        GsonRequest<DiscoverBean> gsonRequest = new GsonRequest<DiscoverBean>(URLAll.DISCOVERY_PAGE, DiscoverBean.class, new Response.Listener<DiscoverBean>() {


            private Boolean judge = false;
            private ArrayList<ImageView> arrayList;
            View view;
            LinearLayout.LayoutParams params;

            @Override
            public void onResponse(DiscoverBean bean) {
                mList = new ArrayList<>();
                DiscoverFragment.bean = bean;
                for (int i = 0; i < bean.getResult().getCardlist().get(0).getData().size(); i++) {

                    mList.add(bean.getResult().getCardlist().get(0).getData().get(i).getImageurl());


                }
                discoverHeader_Advertisement_ViewPager.setImages(mList);


                ArrayList<String> arrayList = new ArrayList<>();

                for (int i = 0; i < bean.getResult().getCardlist().size(); i++) {

                    arrayList.add(bean.getResult().getCardlist().get(i).getDescription());
                    if (bean.getResult().getCardlist().get(i).getDescription().equals("单帧大号横栏")) {


                        Picasso.with(context).load(bean.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(discoverHeader_Advertisement_ImageView);

                    }

                    if (bean.getResult().getCardlist().get(i).getDescription().equals("活动专区")) {

                        Picasso.with(context).load(bean.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(activity_Zone_First_ImageView);
                        Picasso.with(context).load(bean.getResult().getCardlist().get(i).getData().get(1).getImageurl()).into(activity_Zone_Second_ImageView);
                        Picasso.with(context).load(bean.getResult().getCardlist().get(i).getData().get(2).getImageurl()).into(activity_Zone_Third_ImageView);


                    }

                    if (bean.getResult().getCardlist().get(i).getDescription().equals("模块列表") && 2 == Integer.parseInt(bean.getResult().getCardlist().get(i).getTopblanktype())) {


                        LoveLyRecyclerView loveLyRecyclerView = new LoveLyRecyclerView(getContext());

                        loveLyRecyclerView.setBean(bean, i);
                        discoverHeadler_ForMe_RecyclerView.setAdapter(loveLyRecyclerView);


                        discoverHeadler_ForMe_RecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


                    }


                    if (bean.getResult().getCardlist().get(i).getDescription().equals("模块列表") && 0 == Integer.parseInt(bean.getResult().getCardlist().get(i).getTopblanktype())) {


                        ForMe_RecyclerView forMe_recyclerView = new ForMe_RecyclerView(getContext());

                        forMe_recyclerView.setBean(bean, i);
                        discoverHeadler_Lovely_RecyclerView.setAdapter(forMe_recyclerView);
                        discoverHeadler_Lovely_RecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    }

                    //下面的 广告图片
                    if (bean.getResult().getCardlist().get(i).getDescription().equals("单帧小号横栏") && 2 == Integer.parseInt(bean.getResult().getCardlist().get(i).getTopblanktype())) {
                        Picasso.with(context).load(bean.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(Singleframe_Button_ImageView);
                    }


                    if (bean.getResult().getCardlist().get(i).getDescription().equals("限时抢购")) {

                        //水平ListView
                        HorizontalListViewAdapter horizontalListViewAdapter = new HorizontalListViewAdapter(getContext());
                        horizontalListViewAdapter.setBean(bean, i);
                        discoverHorizontalListView.setAdapter(horizontalListViewAdapter);

                        LimitedBuyTextView limitedBuyTextView = new LimitedBuyTextView(context, bean.getResult().getCardlist().get(i).getRightbtn().getData());

                        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                        lp.addRule(RelativeLayout.CENTER_IN_PARENT);//与父容器的右侧对齐
                        lp.rightMargin=10;
                        View view = limitedBuyTextView.initTime();
                        view.setLayoutParams(lp);
                        disHeader_RelativeLayout.addView(view);


                    }


                    if (bean.getResult().getCardlist().get(i).getDescription().equals("田字小号专区")) {
                        RecyclerViewServerAdapter recyclerViewServerAdapter = new RecyclerViewServerAdapter(getContext());
                        recyclerViewServerAdapter.setBean(bean, i);
                        recyclerViewDiscoverHeader_Sector.setAdapter(recyclerViewServerAdapter);
                        recyclerViewDiscoverHeader_Sector.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    }

                    if (bean.getResult().getCardlist().get(i).getDescription().equals("业务入口")) {

                        //10个按钮
                        RecyclerViewNavigationAdapter recyclerViewNavigationAdapter = new RecyclerViewNavigationAdapter(getContext());
                        recyclerViewNavigationAdapter.setBean(bean, i);
                        recyclerViewDiscoverHeaderNavigation.setAdapter(recyclerViewNavigationAdapter);
                        recyclerViewDiscoverHeaderNavigation.setLayoutManager(new GridLayoutManager(getContext(), 5));

                        recyclerViewNavigationAdapter.setOnRecyclerltemClickListener(new OnRecyclerltemClickListener() {
                            @Override
                            public void click(int postion, RecyclerViewNavigationAdapter.ViewHoler holder) {


                                switch (postion) {


                                    case URLAll.ONE:
                                        Intent intentOne = new Intent(getActivity(), OnePageFirstActivity.class);
                                        intentOne.putExtra("title", "车商城");
                                        intentOne.putExtra("OnePageFragmentBean", URLAll.DISCOVER_CAR_MALL);
                                        intentOne.putExtra("layout", 5);
                                        context.startActivity(intentOne);
                                        break;

                                    case URLAll.TWO:
                                        Intent intentTwo = new Intent(getActivity(), OnePageFirstActivity.class);
                                        intentTwo.putExtra("title", "分期购车");
                                        intentTwo.putExtra("OnePageFragmentBean", URLAll.DISCOVER_HIRE_CAR);
                                        intentTwo.putExtra("layout", 5);
                                        context.startActivity(intentTwo);
                                        break;
                                    case URLAll.THREE:
                                        Intent intentThree = new Intent(getActivity(), OnePageFirstActivity.class);
                                        intentThree.putExtra("title", "养车之家");
                                        intentThree.putExtra("OnePageFragmentBean", URLAll.DISCOVER_SUBSIDY_HOME);
                                        intentThree.putExtra("layout", 5);
                                        context.startActivity(intentThree);
                                        break;
                                    case URLAll.FOUR:
                                        Intent intentFour = new Intent(getActivity(), OnePageFirstActivity.class);
                                        intentFour.putExtra("title", "找二手车");
                                        intentFour.putExtra("OnePageFragmentBean", URLAll.DISCOVER_FIND_CAR);
                                        intentFour.putExtra("layout", 5);
                                        context.startActivity(intentFour);
                                        break;


                                }


                            }
                        });


                    }
                    Log.d("DiscoverFragment", bean.getResult().getCardlist().get(i).getDescription());

//
                    if (bean.getResult().getCardlist().get(i).getDescription().equals("商品列表")) {

                        //最后的列表
                        DiscoverAdapter pageListViewAdapter = new DiscoverAdapter(getContext());
                        pageListViewAdapter.setBean(bean, i);
                        listView_DiscoverFragment.setAdapter(pageListViewAdapter);

                    }


                }

                for (int i = 0; i < arrayList.size(); i++) {

                    if (arrayList.get(i).equals("限时抢购")) {
                        judge = true;
                    }

                }
                if (judge == false) {

                    disHeader_RelativeLayout.setVisibility(View.GONE);
                    discoverHorizontalListView.setVisibility(View.GONE);

                } else {
                    disHeader_RelativeLayout.setVisibility(View.VISIBLE);
                    discoverHorizontalListView.setVisibility(View.VISIBLE);

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
