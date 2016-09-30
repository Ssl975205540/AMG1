package lanou.amg1.discoverfragment.discovermain;

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.discoverfragment.discoverheadler_forme_recyclerview.ForMe_RecyclerView;
import lanou.amg1.discoverfragment.focusmap.BannerAdapter;
import lanou.amg1.discoverfragment.horizontallistview.HorizontalListView;
import lanou.amg1.discoverfragment.horizontallistview.HorizontalListViewAdapter;
import lanou.amg1.discoverfragment.lovely.LoveLyRecyclerView;
import lanou.amg1.discoverfragment.recyclerviewnavigation.RecyclerViewNavigationAdapter;
import lanou.amg1.discoverfragment.recyclerviewsector.RecyclerViewServerAdapter;
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.VolleySingleton;
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
    private ViewPager discoverHeader_Advertisement_ViewPager;

    private static DiscoverBean bean;
    private List<ImageView> mList;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    //广告图素材


    //广告语
    private String[] bannerTexts = {
            "ssssss", "aaaaaaaa", "wwwwwwwww", "awwwweeeeeee"
    };
    // ViewPager适配器与监听器
    private BannerAdapter mAdapter;
    private BannerListener bannerListener;

    // 圆圈标志位
    private int pointIndex = 0;
    // 线程标志
    private boolean isStop = false;
    private Boolean mm = true;



    @Override
    protected void networkRequest() {

        this.access();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(2000);
                 try {
                     getActivity().runOnUiThread(new Runnable() {

                         @Override
                         public void run() {
                             discoverHeader_Advertisement_ViewPager.setCurrentItem(discoverHeader_Advertisement_ViewPager.getCurrentItem() + 1);
                         }
                     });
                 }catch (NullPointerException e){

                 }

                }
            }
        }).start();

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
    protected View setLayout(LayoutInflater inflater) {

        return inflater.inflate(R.layout.discover_fragment, null);
    }

    @Override
    protected void control() {


//        discoverHeader_fragment = findFragmentByid(R.id.discoverHeader_fragment);

        view = LayoutInflater.from(getActivity()).inflate(R.layout.discoverfragment_header, null);

        mTextView = (TextView) findById(R.id.tv_bannertext, view);
        mLinearLayout = (LinearLayout) findById(R.id.points, view);

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


            private ArrayList<ImageView> arrayList;
            View view;
            LinearLayout.LayoutParams params;

            @Override
            public void onResponse(DiscoverBean bean) {
                mList = new ArrayList<>();
                DiscoverFragment.bean = bean;
                for (int i = 0; i < bean.getResult().getCardlist().get(0).getData().size(); i++) {
                    // 设置广告图
                    ImageView imageView = new ImageView(context);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
                    Picasso.with(context).load(bean.getResult().getCardlist().get(0).getData().get(i).getImageurl()).into(imageView);

                    mList.add(imageView);
                    view = new View(context);
                    params = new LinearLayout.LayoutParams(10, 5);
                    params.leftMargin = 10;
                    view.setBackgroundResource(R.drawable.shapebackground);
                    view.setLayoutParams(params);
                    view.setEnabled(false);
                    mLinearLayout.addView(view);
                }
                mAdapter = new BannerAdapter(mList);

                discoverHeader_Advertisement_ViewPager.setAdapter(mAdapter);

                bannerListener = new BannerListener();
                discoverHeader_Advertisement_ViewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) bannerListener);
                //取中间数来作为起始位置
                int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % mList.size());
                //用来出发监听器
                discoverHeader_Advertisement_ViewPager.setCurrentItem(index);
                mLinearLayout.getChildAt(pointIndex).setEnabled(true);


                for (int i = 0; i < bean.getResult().getCardlist().size(); i++) {


                    if (bean.getResult().getCardlist().get(i).getDescription().equals("单帧大号横栏")) {

                        Log.d("DiscoverFragment", "fcacac");
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


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);


    }



    class BannerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            int newPosition = position % DiscoverFragment.bean.getResult().getCardlist().get(0).getData().size();
//            mTextView.setText(bannerTexts[newPosition]);
            mLinearLayout.getChildAt(newPosition).setEnabled(true);
            mLinearLayout.getChildAt(pointIndex).setEnabled(false);
            // 更新标志位
            pointIndex = newPosition;

        }

    }

    @Override
    public void onDestroy() {
        isStop = true;
        super.onDestroy();

    }
}
