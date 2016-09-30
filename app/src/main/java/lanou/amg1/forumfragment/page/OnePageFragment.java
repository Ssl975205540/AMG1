package lanou.amg1.forumfragment.page;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.Random;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.forumfragment.page.onepagefirst.OnePageFirstActivity;
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.VolleySingleton;
import lanou.amg1.urlall.URLAll;

/**
 * Created by dllo on 16/9/21.
 */
public class OnePageFragment extends Base_Fragment implements View.OnClickListener {


    private PullToRefreshListView onePageListView;
    private RelativeLayout onePageHeaderRelativelayout;

    private TextView[] onePageHeader;
    int  das =550943;
    private RelativeLayout onePageHeader_all;
    private static OnePageFragmentBean bean;
    @Override
    protected void networkRequest() {



        GsonRequest<OnePageFragmentBean> onePageFragmentBeanGsonRequest = new GsonRequest<OnePageFragmentBean>(URLAll.FORUM_PAGE, OnePageFragmentBean.class, new Response.Listener<OnePageFragmentBean>() {
            @Override
            public void onResponse(OnePageFragmentBean bean) {

                OnPageListViewAdapter pageListViewAdapter = new OnPageListViewAdapter(getContext());

                pageListViewAdapter.setBean(bean);

                onePageListView.setAdapter(pageListViewAdapter);

                OnePageFragment.bean = bean;


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
                    Intent intent = new Intent(getActivity(),OnePageFirstActivity.class);
                    Log.d("OnePageFragment", URLAll.FORUM_PAGE_Q + OnePageFragment.bean.getResult().getList().get(position).getTopicid() + URLAll.FORUM_PAGE_H);
                    intent.putExtra("layout",3);
                    intent.putExtra("OnePageFragmentBean",URLAll.FORUM_PAGE_Q +OnePageFragment.bean.getResult().getList().get(position-2).getTopicid()+URLAll.FORUM_PAGE_H);

                    startActivity(intent);
                }catch (ArrayIndexOutOfBoundsException e){

                }





            }
        });
    }

    @Override
    protected View setLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.onepageframent,null);
    }


    @Override
    protected void control() {

        onePageListView = findById(R.id.onePageListView);



        onePageHeader = new TextView[7];
        View view = LayoutInflater.from(getContext()).inflate(R.layout.onepageheaderview,null);
        onePageHeader_all = findById(R.id.onePageHeader_all,view);
        onePageHeader_all.setOnClickListener(this);
        onePageHeader[0] = findById(R.id.onePageHeader1,view);
        onePageHeader[1] = findById(R.id.onePageHeader2,view);
        onePageHeader[2] = findById(R.id.onePageHeader3,view);
        onePageHeader[3] = findById(R.id.onePageHeader4,view);
        onePageHeader[4] = findById(R.id.onePageHeader5,view);
        onePageHeader[5] = findById(R.id.onePageHeader6,view);
        onePageHeader[6] = findById(R.id.onePageHeader7,view);
        for (int i = 0; i < onePageHeader.length; i++) {
            onePageHeader[i].setOnClickListener(this);

        }

        onePageHeaderRelativelayout = findById(R.id.onePageHeaderRelativelayout,view);
        onePageHeaderRelativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),OnePageFirstActivity.class);
                intent.putExtra("layout",4);
                intent.putExtra("onePageHeaderHot",URLAll.TOP_POSTS_URL);
                startActivity(intent);

            }
        });
        onePageListView.getRefreshableView().addHeaderView(view);
        Random();
        Up_Mode();

        onePageListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                Random();

                GsonRequest<OnePageFragmentBean> onePageFragmentBeanGsonRequest = new GsonRequest<OnePageFragmentBean>(URLAll.FORUM_PAGE, OnePageFragmentBean.class, new Response.Listener<OnePageFragmentBean>() {
                    @Override
                    public void onResponse(OnePageFragmentBean bean) {

                        OnPageListViewAdapter pageListViewAdapter = new OnPageListViewAdapter(getContext());

                        pageListViewAdapter.setBean(bean);

                        onePageListView.setAdapter(pageListViewAdapter);
                        onePageListView.onRefreshComplete();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                VolleySingleton.getInstance().addRequest(onePageFragmentBeanGsonRequest);



            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                das = das + 30;
                String im = das+"";

                String url1 = "http://223.99.255.20/clubnc.app.autohome.com.cn/club_v7.0.5/club/jingxuantopic.ashx?platud=2&categoryid=0&pageindex=1&pagesize=30&devicetype=android.1501_M02&deviceid=860954030358581&userid=0&operation=1&netstate=0&gps=38.889726%2C121." + im;

                GsonRequest<OnePageFragmentBean> onePageFragmentBeanGsonRequest = new GsonRequest<OnePageFragmentBean>(url1, OnePageFragmentBean.class, new Response.Listener<OnePageFragmentBean>() {
                    @Override
                    public void onResponse(OnePageFragmentBean bean) {

                        OnPageListViewAdapter pageListViewAdapter = new OnPageListViewAdapter(getContext());
                        pageListViewAdapter.setBean(bean);

                        onePageListView.setAdapter(pageListViewAdapter);
                        onePageListView.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                VolleySingleton.getInstance().addRequest(onePageFragmentBeanGsonRequest);




                onePageListView.onRefreshComplete();
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

    private void Up_Mode() {
        // 设置PullToRefreshListView的模式
        onePageListView.setMode(PullToRefreshBase.Mode.BOTH);

        onePageListView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载...");
        onePageListView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        onePageListView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多...");
        // 设置PullRefreshListView下拉加载时的加载提示
        onePageListView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
        onePageListView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在加载...");
        onePageListView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开加载更多...");

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(),OnePageFirstActivity.class);

        switch (v.getId()){

            case R.id.onePageHeader1:



                intent.putExtra("layout",1);
                intent.putExtra("onePageHeaderTitle",onePageHeader[0].getText().toString());
                intent.putExtra("onePageHeader",URLAll.WIFE_MODEL_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader2:

                intent.putExtra("layout",1);
                intent.putExtra("onePageHeaderTitle",onePageHeader[1].getText().toString());
                intent.putExtra("onePageHeader",URLAll.NOTORIOUS_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader3:


                //高端阵地
                intent.putExtra("layout",1);
                intent.putExtra("onePageHeaderTitle",onePageHeader[2].getText().toString());

                intent.putExtra("onePageHeader",URLAll.HIGH_POINT_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader4:

                intent.putExtra("layout",1);
                intent.putExtra("onePageHeaderTitle",onePageHeader[3].getText().toString());

                intent.putExtra("onePageHeader",URLAll.FRIEND_HEAVEN_EARTH_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader5:

                intent.putExtra("layout",1);
                intent.putExtra("onePageHeaderTitle",onePageHeader[4].getText().toString());

                intent.putExtra("onePageHeader",URLAll.AUSLESE_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader6:

                intent.putExtra("layout",1);
                intent.putExtra("onePageHeaderTitle",onePageHeader[5].getText().toString());

                intent.putExtra("onePageHeader",URLAll.SISTER_CAR_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader7:

                intent.putExtra("layout",1);
                intent.putExtra("onePageHeaderTitle",onePageHeader[6].getText().toString());

                intent.putExtra("onePageHeader",URLAll.HOF_URL.toString());
                startActivity(intent);


                break;

            case R.id.onePageHeader_all:
                intent.putExtra("layout",2);
                intent.putExtra("onePageHeader",URLAll.HOF_URL.toString());
                startActivity(intent);


                break;



        }


    }
}
