package lanou.amg1.forum.page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Random;

import lanou.amg1.R;
import lanou.amg1.base.BaseFragment;
import lanou.amg1.bean.OnePageFgtBean;
import lanou.amg1.forum.page.onepagefirst.OnePageFirstAty;
import lanou.amg1.tools.requset.GsonRequest;
import lanou.amg1.tools.requset.VolleySingleton;
import lanou.amg1.tools.xlistview.XListView;
import lanou.amg1.tools.URLAll;

/**
 * Created by dllo on 16/9/21.
 */
public class OnePageFragment extends BaseFragment implements View.OnClickListener ,XListView.IXListViewListener {


    private XListView onePageListView;
    private RelativeLayout onePageHeaderRelativelayout;
    private OnPageLvAdp pageListViewAdapter;
    private TextView[] onePageHeader;
    int i = 1;
    private RelativeLayout onePageHeader_all;
    private static OnePageFgtBean bean;
    private LinearLayout popup_recommend;
    private TextView popup_recommend_text;
    private Handler mHandler;

    private boolean Load= true;
    private ImageView onePageRefresh;
    private RelativeLayout onePage_refresh_relativeLayout;
    private TextView onePage_refresh_textview;

    @Override
    protected void initData() {




        Random();
        ((AnimationDrawable)onePageRefresh.getBackground()).start();
        mHandler = new Handler();


        GsonRequest<OnePageFgtBean> onePageFragmentBeanGsonRequest = new GsonRequest<OnePageFgtBean>(URLAll.FORUM_PAGE, OnePageFgtBean.class, new Response.Listener<OnePageFgtBean>() {
            @Override
            public void onResponse(OnePageFgtBean bean) {

                pageListViewAdapter = new OnPageLvAdp(getContext());

                pageListViewAdapter.setBean(bean);

                onePageListView.setAdapter(pageListViewAdapter);

                OnePageFragment.bean = bean;

                onePage_refresh_relativeLayout.setVisibility(View.GONE);
                if (isNetworkAvailable(getActivity())) {

                    AlphaAnimation alp = new AlphaAnimation(1.0f, 0.0f);
                    alp.setDuration(3000);
                    popup_recommend.setAnimation(alp);
                    popup_recommend.setVisibility(View.INVISIBLE);



                } else {
                    popup_recommend_text.setText("当前网络不可用,请检查网络设置");
                    AlphaAnimation alp = new AlphaAnimation(1.0f, 0.0f);
                    alp.setDuration(3000);
                    popup_recommend.setAnimation(alp);
                    popup_recommend.setVisibility(View.INVISIBLE);


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance().addRequest(onePageFragmentBeanGsonRequest);
        onePageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    Intent intent = new Intent(getActivity(), OnePageFirstAty.class);
                    Log.d("OnePageFragment", URLAll.FORUM_PAGE_Q + OnePageFragment.bean.getResult().getList().get(position).getTopicid() + URLAll.FORUM_PAGE_H);
                    intent.putExtra("layout", 3);
                    intent.putExtra("OnePageFragmentBean", URLAll.FORUM_PAGE_Q + OnePageFragment.bean.getResult().getList().get(position - 2).getTopicid() + URLAll.FORUM_PAGE_H);
                    startActivity(intent);

                } catch (ArrayIndexOutOfBoundsException e) {

                }


            }
        });


    }


    @Override
    protected int setContentView() {
        return R.layout.onepageframent;
    }


    @Override
    protected void initViews() {

        onePageListView = findById(R.id.onePageListView);

        popup_recommend = findById(R.id.popup_recommend);

        popup_recommend_text = findById(R.id.popup_recommend_text);

        onePageHeader = new TextView[7];
        View view = LayoutInflater.from(getContext()).inflate(R.layout.onepageheaderview, null);
        onePageHeader_all = findById(R.id.onePageHeader_all, view);
        onePageHeader_all.setOnClickListener(this);
        onePageHeader[0] = findById(R.id.onePageHeader1, view);
        onePageHeader[1] = findById(R.id.onePageHeader2, view);
        onePageHeader[2] = findById(R.id.onePageHeader3, view);
        onePageHeader[3] = findById(R.id.onePageHeader4, view);
        onePageHeader[4] = findById(R.id.onePageHeader5, view);
        onePageHeader[5] = findById(R.id.onePageHeader6, view);
        onePageHeader[6] = findById(R.id.onePageHeader7, view);

        onePageRefresh =  findById(R.id.onePage_refresh);
        onePage_refresh_relativeLayout = findById(R.id.onePage_refresh_relativeLayout);
        onePage_refresh_textview = findById(R.id.onePage_refresh_textview);



        for (int i = 0; i < onePageHeader.length; i++) {
            onePageHeader[i].setOnClickListener(this);

        }

        onePageHeaderRelativelayout = findById(R.id.onePageHeaderRelativelayout, view);

        onePageListView.addHeaderView(view);






    }

    @Override
    protected void initListeners() {

        onePageListView.setXListViewListener(this);
        onePageListView.setPullLoadEnable(true);
        onePageHeaderRelativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OnePageFirstAty.class);
                intent.putExtra("layout", 4);
                intent.putExtra("onePageHeaderHot", URLAll.TOP_POSTS_URL);
                startActivity(intent);

            }
        });
    }

    private void Random() {
        for (int i = 0; i < 7; i++) {
            onePageHeader[i].setBackgroundResource(R.drawable.selector_textview_blu);

            onePageHeader[i].setTextColor(this.getResources().getColor(R.color.textViewOnePage_blue));
        }
        Random random = new Random();

        for (int i = 0; i < random.nextInt(7); i++) {
            int d = random.nextInt(7);
            onePageHeader[d].setBackgroundResource(R.drawable.selector_textview_org);
            onePageHeader[d].setTextColor(this.getResources().getColor(R.color.textViewOnePage_org));
        }


    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), OnePageFirstAty.class);

        switch (v.getId()) {

            case R.id.onePageHeader1:


                intent.putExtra("layout", 1);
                intent.putExtra("onePageHeaderTitle", onePageHeader[0].getText().toString());
                intent.putExtra("onePageHeader", URLAll.WIFE_MODEL_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader2:

                intent.putExtra("layout", 1);
                intent.putExtra("onePageHeaderTitle", onePageHeader[1].getText().toString());
                intent.putExtra("onePageHeader", URLAll.NOTORIOUS_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader3:


                //高端阵地
                intent.putExtra("layout", 1);
                intent.putExtra("onePageHeaderTitle", onePageHeader[2].getText().toString());

                intent.putExtra("onePageHeader", URLAll.HIGH_POINT_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader4:

                intent.putExtra("layout", 1);
                intent.putExtra("onePageHeaderTitle", onePageHeader[3].getText().toString());

                intent.putExtra("onePageHeader", URLAll.FRIEND_HEAVEN_EARTH_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader5:

                intent.putExtra("layout", 1);
                intent.putExtra("onePageHeaderTitle", onePageHeader[4].getText().toString());

                intent.putExtra("onePageHeader", URLAll.AUSLESE_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader6:

                intent.putExtra("layout", 1);
                intent.putExtra("onePageHeaderTitle", onePageHeader[5].getText().toString());

                intent.putExtra("onePageHeader", URLAll.SISTER_CAR_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader7:

                intent.putExtra("layout", 1);
                intent.putExtra("onePageHeaderTitle", onePageHeader[6].getText().toString());

                intent.putExtra("onePageHeader", URLAll.HOF_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader_all:
                intent.putExtra("layout", 2);
                intent.putExtra("onePageHeader", URLAll.HOF_URL.toString());
                startActivity(intent);


                break;


        }


    }
    public boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // 刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                Random();

                GsonRequest<OnePageFgtBean> onePageFragmentBeanGsonRequest = new GsonRequest<OnePageFgtBean>(URLAll.FORUM_PAGE, OnePageFgtBean.class, new Response.Listener<OnePageFgtBean>() {



                    @Override
                    public void onResponse(OnePageFgtBean bean) {

                        pageListViewAdapter = new OnPageLvAdp(getContext());

                        pageListViewAdapter.setBean(bean);

                        onePageListView.setAdapter(pageListViewAdapter);


                        onePageListView.stopRefresh();

                        if (isNetworkAvailable(getActivity())) {

                            AlphaAnimation alp = new AlphaAnimation(1.0f, 0.0f);
                            alp.setDuration(3000);
                            popup_recommend.setAnimation(alp);
                            popup_recommend.setVisibility(View.INVISIBLE);



                        } else {
                            popup_recommend_text.setText("当前网络不可用,请检查网络设置");
                            AlphaAnimation alp = new AlphaAnimation(1.0f, 0.0f);
                            alp.setDuration(3000);
                            popup_recommend.setAnimation(alp);
                            popup_recommend.setVisibility(View.INVISIBLE);


                        }




                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                VolleySingleton.getInstance().addRequest(onePageFragmentBeanGsonRequest);

            }
        }, 0);
    }

    // 加载更多
    @Override
    public void onLoadMore() {


        if(Load){

            Load = false;
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {

                    Log.d("OnePageFragment", "机那里了");
                    i++;

                    String url1 = "http://223.99.255.20/clubnc.app.autohome.com.cn/club_v7.0.5/club/jingxuantopic.ashx?platud=2&categoryid=0&pageindex="+i+"&pagesize=30&devicetype=android.1501_M02&deviceid=860954030358581&userid=0&operation=1&netstate=0&gps=38.889726%2C121.550943";

                    GsonRequest<OnePageFgtBean> onePageFragmentBeanGsonRequest = new GsonRequest<OnePageFgtBean>(url1, OnePageFgtBean.class, new Response.Listener<OnePageFgtBean>() {
                        @Override
                        public void onResponse(OnePageFgtBean bean) {

                            try {
                                pageListViewAdapter.setBean1(bean);
                                Load = true;
                                onePageListView.stopLoadMore();
                            }catch (NullPointerException e){

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    VolleySingleton.getInstance().addRequest(onePageFragmentBeanGsonRequest);




                }
            }, 0);
        }

    }


}
