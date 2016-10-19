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
import lanou.amg1.base.BaseFragment;
import lanou.amg1.bean.DiscoverBean;
import lanou.amg1.discoverfragment.discoverheadler_forme_recyclerview.ForMeRecyclerView;
import lanou.amg1.discoverfragment.horizontallistview.HorizontalListView;
import lanou.amg1.discoverfragment.horizontallistview.HorizontalListViewAdapter;
import lanou.amg1.discoverfragment.horizontallistview.LimitedBuyTextView;
import lanou.amg1.discoverfragment.lovely.LoveLyRecyclerView;
import lanou.amg1.discoverfragment.recyclerviewnavigation.OnRecyclerltemClickListener;
import lanou.amg1.discoverfragment.recyclerviewnavigation.RecyclerViewNavigationAdapter;
import lanou.amg1.discoverfragment.recyclerviewsector.RecyclerViewServerAdapter;
import lanou.amg1.forum.page.onepagefirst.OnePageFirstAty;
import lanou.amg1.tools.requset.GsonRequest;
import lanou.amg1.tools.requset.VolleySingleton;
import lanou.amg1.search.SearchAty;
import lanou.amg1.tools.URLAll;


/**
 * Created by dllo on 16/9/20.
 */
public class DiscoverFragment extends BaseFragment {

    private PullToRefreshListView discoverFragmentLV;
    private HorizontalListView discoverHeaderHorizontalLV;
    private RecyclerView discoverHeaderNavigationRV;
    private RecyclerView discoverHeaderSectorRV;
    private ImageView discoverHeaderAdvertisementIV;
    private ImageView activityZoneFirstIV;
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
    protected void initData() {

        this.request();


        discoverFragmentLV.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                GsonRequest<DiscoverBean> gsonRequest1 = new GsonRequest<DiscoverBean>(URLAll.DISCOVERY_PAGE, DiscoverBean.class, new Response.Listener<DiscoverBean>() {
                    @Override
                    public void onResponse(DiscoverBean bean) {

                        for (int i = 0; i < bean.getResult().getCardlist().size() - 1; i++) {

                            if (bean.getResult().getCardlist().get(i).getDescription().equals("猜你喜欢")) {
                                DiscoverAdp pageListViewAdapter = new DiscoverAdp(getContext());
                                pageListViewAdapter.setBean(bean, i);
                                discoverFragmentLV.setAdapter(pageListViewAdapter);
                            }
                        }

                        discoverFragmentLV.onRefreshComplete();
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


            }


        });


    }

    @Override
    protected int setContentView() {

        return R.layout.fragment_discover;
    }


    @Override
    protected void initViews() {


//        discoverHeader_fragment = findFragmentByid(R.id.discoverHeader_fragment);

        view = LayoutInflater.from(getActivity()).inflate(R.layout.discoverfragment_header, null);


        disHeader_RelativeLayout = findById(R.id.disHeader_RelativeLayout, view);

        discover_fragment_search = findById(R.id.discover_fragment_search);

        discoverHeader_Advertisement_ViewPager = findById(R.id.discoverHeader_Advertisement_ViewPager, view);
        Singleframe_Button_ImageView = findById(R.id.Singleframe_Button_ImageView, view);
        activityZoneFirstIV = findById(R.id.activity_Zone_First_ImageView, view);
        activity_Zone_Second_ImageView = findById(R.id.activity_Zone_Second_ImageView, view);
        activity_Zone_Third_ImageView = findById(R.id.activity_Zone_Third_ImageView, view);
        discoverHeaderAdvertisementIV = findById(R.id.discover_Header_Advertisement_iv, view);
        discoverFragmentLV = findById(R.id.discoverfragment_lv);
        discoverHeadler_ForMe_RecyclerView = findById(R.id.discoverHeadler_ForMe_RecyclerView, view);
        discoverHeaderSectorRV = findById(R.id.discover_Header_Sector_Rcv, view);
        discoverHeadler_Lovely_RecyclerView = findById(R.id.discoverHeadler_Lovely_RecyclerView, view);
        discoverHeaderHorizontalLV = findById(R.id.discover_horizontal_lv, view);
        discoverHeaderNavigationRV = findById(R.id.discover_header_navigation_rcv, view);
        discoverFragmentLV.getRefreshableView().addHeaderView(view);

    }

    @Override
    protected void initListeners() {

        discover_fragment_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchAty.class);
                intent.putExtra("search", "搜索车商城");
                startActivity(intent);


            }
        });

    }


    private void request() {


        GsonRequest<DiscoverBean> gsonRequest = new GsonRequest<DiscoverBean>(URLAll.DISCOVERY_PAGE, DiscoverBean.class, new Response.Listener<DiscoverBean>() {

            private Boolean judge = false;

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


                        Picasso.with(context).load(bean.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(discoverHeaderAdvertisementIV);

                    }

                    if (bean.getResult().getCardlist().get(i).getDescription().equals("活动专区")) {

                        Picasso.with(context).load(bean.getResult().getCardlist().get(i).getData().get(0).getImageurl()).into(activityZoneFirstIV);
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


                        ForMeRecyclerView forMe_recyclerView = new ForMeRecyclerView(getContext());

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
                        discoverHeaderHorizontalLV.setAdapter(horizontalListViewAdapter);

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
                        discoverHeaderSectorRV.setAdapter(recyclerViewServerAdapter);
                        discoverHeaderSectorRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    }

                    if (bean.getResult().getCardlist().get(i).getDescription().equals("业务入口")) {

                        //10个按钮
                        RecyclerViewNavigationAdapter recyclerViewNavigationAdapter = new RecyclerViewNavigationAdapter(getContext());
                        recyclerViewNavigationAdapter.setBean(bean, i);
                        discoverHeaderNavigationRV.setAdapter(recyclerViewNavigationAdapter);
                        discoverHeaderNavigationRV.setLayoutManager(new GridLayoutManager(getContext(), 5));

                        recyclerViewNavigationAdapter.setOnRecyclerltemClickListener(new OnRecyclerltemClickListener() {
                            @Override
                            public void click(int postion, RecyclerViewNavigationAdapter.ViewHoler holder) {


                                switch (postion) {


                                    case URLAll.ONE:
                                        Intent intentOne = new Intent(getActivity(), OnePageFirstAty.class);
                                        intentOne.putExtra("title", "车商城");
                                        intentOne.putExtra("OnePageFragmentBean", URLAll.DISCOVER_CAR_MALL);
                                        intentOne.putExtra("layout", 5);
                                        context.startActivity(intentOne);
                                        break;

                                    case URLAll.TWO:
                                        Intent intentTwo = new Intent(getActivity(), OnePageFirstAty.class);
                                        intentTwo.putExtra("title", "分期购车");
                                        intentTwo.putExtra("OnePageFragmentBean", URLAll.DISCOVER_HIRE_CAR);
                                        intentTwo.putExtra("layout", 5);
                                        context.startActivity(intentTwo);
                                        break;
                                    case URLAll.THREE:
                                        Intent intentThree = new Intent(getActivity(), OnePageFirstAty.class);
                                        intentThree.putExtra("title", "养车之家");
                                        intentThree.putExtra("OnePageFragmentBean", URLAll.DISCOVER_SUBSIDY_HOME);
                                        intentThree.putExtra("layout", 5);
                                        context.startActivity(intentThree);
                                        break;
                                    case URLAll.FOUR:
                                        Intent intentFour = new Intent(getActivity(), OnePageFirstAty.class);
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
                        DiscoverAdp pageListViewAdapter = new DiscoverAdp(getContext());
                        pageListViewAdapter.setBean(bean, i);
                        discoverFragmentLV.setAdapter(pageListViewAdapter);

                    }


                }

                for (int i = 0; i < arrayList.size(); i++) {

                    if (arrayList.get(i).equals("限时抢购")) {
                        judge = true;
                    }

                }
                if (judge == false) {

                    disHeader_RelativeLayout.setVisibility(View.GONE);
                    discoverHeaderHorizontalLV.setVisibility(View.GONE);

                } else {
                    disHeader_RelativeLayout.setVisibility(View.VISIBLE);
                    discoverHeaderHorizontalLV.setVisibility(View.VISIBLE);

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
