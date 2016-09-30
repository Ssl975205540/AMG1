package lanou.amg1.recommendfragment;

import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.VolleySingleton;
import lanou.amg1.viewpageradpter.ImageAdapter;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendFragment extends Base_Fragment {


    public ImageFragmentHandler handler = new ImageFragmentHandler(new WeakReference<RecommendFragment>(this));
    public ViewPager recommendViewPager;

    private RecommendlistViewAdapter _FragmentRecommendlistViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    private String url = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l0.json";
    //    private RecyclerView recyclerView_Recommend_Fragment;
    private RecommendRecyclerViewAdapter adapter_recyclerViewRecommendFragment;
    private PullToRefreshListView listView_Recommend_Fragment;

    private CircleIndicator1 recommendViewPagerCircleIndicator;


    private RecommendlistViewAdapter adapter_listView_recommend_fragment;
    private FragmentRecommendBean bean;
    private LinearLayout line1;
    private ImageView imageasdcas;

    @Override
    protected void networkRequest() {


//
        GsonRequest<FragmentRecommendBean> gsonRequest = new GsonRequest<FragmentRecommendBean>(url, FragmentRecommendBean.class, new Response.Listener<FragmentRecommendBean>() {
            @Override
            public void onResponse(FragmentRecommendBean bean) {

                adapter_listView_recommend_fragment = new RecommendlistViewAdapter(getContext());

                adapter_listView_recommend_fragment.setBean(bean);

                listView_Recommend_Fragment.setAdapter(adapter_listView_recommend_fragment);


                View view1 = LayoutInflater.from(context).inflate(R.layout.headerview_listview_recommend_fragment, null);
                listView_Recommend_Fragment.getRefreshableView().addHeaderView(view1);
                recommendViewPagerCircleIndicator = (CircleIndicator1) view1.findViewById(R.id.recommendViewPagerCircleIndicator);
                recommendViewPager = (ViewPager) view1.findViewById(R.id.recommendViewPager);

                ArrayList<ImageView> a = new ArrayList<>();
                for (int i = 0; i < bean.getResult().getFocusimg().size(); i++) {
                    ImageView ima = new ImageView(context);
                    Picasso.with(context).load(bean.getResult().getFocusimg().get(i).getImgurl()).into(ima);
                    a.add(ima);

                }

                ImageAdapter imageAdapter = new ImageAdapter(a);


                recommendViewPager.setAdapter(imageAdapter);


                //开始轮播效果


                recommendViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


                    //配合Adapter的currentItem字段进行设置。

                    @Override

                    public void onPageSelected(int arg0) {

                        handler.sendMessage(Message.obtain(handler, ImageFragmentHandler.MSG_PAGE_CHANGED, arg0, 0));


                        Log.d("1", arg0 + "");
                    }


                    @Override

                    public void onPageScrolled(int arg0, float arg1, int arg2) {
                        Log.d("2", "1");
                    }


                    //覆写该方法实现轮播效果的暂停和恢复

                    @Override

                    public void onPageScrollStateChanged(int arg0) {

                        switch (arg0) {

                            case ViewPager.SCROLL_STATE_DRAGGING:
//                        handler.sendEmptyMessage(ImageFragmentHandler.MSG_KEEP_SILENT);

                                Log.d("3", "1");
                                break;

                            case ViewPager.SCROLL_STATE_IDLE:

//                                handler.sendEmptyMessageDelayed(ImageFragmentHandler.MSG_UPDATE_IMAGE, ImageFragmentHandler.MSG_DELAY);


                                handler.sendEmptyMessageDelayed(ImageFragmentHandler.MSG_UPDATE_IMAGE, ImageFragmentHandler.MSG_DELAY);
                                Log.d("4", arg0 + "");
                                break;

                            default:

                                break;
                        }
                    }
                });


                recommendViewPagerCircleIndicator.setViewPager(recommendViewPager, a);


                handler.sendEmptyMessageDelayed(ImageFragmentHandler.MSG_UPDATE_IMAGE, ImageFragmentHandler.MSG_DELAY);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        VolleySingleton.getInstance().addRequest(gsonRequest);

        listView_Recommend_Fragment.setOnScrollListener(new AbsListView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                Log.d("RecommendFragment", "firstVisibleItem:" + firstVisibleItem);
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageasdcas.getLayoutParams();
//
//                layoutParams.setMargins(-100,20,20,20);
//
//                imageasdcas.setLayoutParams(layoutParams);


            }
        });




    }

    @Override
    protected View setLayout(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.recommend_fragment, null);
        return view;
    }

    @Override
    protected void control() {

        listView_Recommend_Fragment = findById(R.id.listView_Recommend_Fragment);
        imageasdcas = findById(R.id.imageasdcas);
        line1 = findById(R.id.line1);
        Log.d("RecommendFragment", "1");


    }


}
