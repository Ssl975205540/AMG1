package lanou.amg1.findcar.fincarpage.findcarone;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lanou.amg1.R;
import lanou.amg1.base.BaseFragment;
import lanou.amg1.bean.AllVehiclesBean;
import lanou.amg1.bean.AnePageFirstBean;
import lanou.amg1.bean.FindCarOnePageGvBean;
import lanou.amg1.bean.ListViewBean;
import lanou.amg1.tools.requset.GsonRequest;
import lanou.amg1.tools.requset.VolleySingleton;
import lanou.amg1.tools.URLAll;

/**
 * Created by dllo on 16/9/27.
 */
public class FindCarOnePgae extends BaseFragment implements SectionIndexer {
    private RecyclerView findCar_OnePage_First_RecyclerView;
    private ListView sortListView;
    private SideBar sideBar;
    private FriendLvAdp adapter;
    private LinearLayout titleLayout;
    private TextView title;
    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<ListViewBean> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private TextView textvv;
    private RecyclerView onePage_GridView;
    private View view;
    private ImageView findCar_OnePage_Flagship_ImagView0;
    private ImageView findCar_OnePage_Flagship_ImagView1;
    private ImageView findCar_OnePage_Flagship_ImagView2;


    @Override
    protected void initData() {


        GsonRequest<AnePageFirstBean> gsonRequest1 = new GsonRequest<AnePageFirstBean>(URLAll.FINCAR_ONE_PAGE_FIRST, AnePageFirstBean.class, new Response.Listener<AnePageFirstBean>() {
            @Override
            public void onResponse(AnePageFirstBean bean) {

                FindCarOnePageFirstAdp findCarOnePageFirstAdp = new FindCarOnePageFirstAdp(getContext());
                findCarOnePageFirstAdp.setBean(bean);
                findCar_OnePage_First_RecyclerView.setAdapter(findCarOnePageFirstAdp);
                findCar_OnePage_First_RecyclerView.setLayoutManager(new GridLayoutManager(context, 3));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest1);

        GsonRequest<AllVehiclesBean> gsonRequest2 = new GsonRequest<AllVehiclesBean>(URLAll.NEWCAR_BRAND_URL, AllVehiclesBean.class, new Response.Listener<AllVehiclesBean>() {
            @Override
            public void onResponse(AllVehiclesBean bean) {


                SourceDateList = filledData(bean);
// 根据a-z进行排序源数据
                Collections.sort(SourceDateList, pinyinComparator);
                adapter = new FriendLvAdp(context, SourceDateList);
                sortListView.setAdapter(adapter);
                sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem,
                                         int visibleItemCount, int totalItemCount) {

                        if (firstVisibleItem == 0) {
                            titleLayout.setVisibility(View.INVISIBLE);

                            return;
                        }
                        firstVisibleItem = firstVisibleItem - 1;


                        int section = getSectionForPosition(firstVisibleItem);

                        int nextSection = getSectionForPosition(firstVisibleItem + 1);

                        int nextSecPosition = getPositionForSection(+nextSection);

                        if (firstVisibleItem != lastFirstVisibleItem) {
                            //获得layout的高度
                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                    .getLayoutParams();
                            //设置layout距离上边缘的距离
                            params.topMargin = 0;
                            titleLayout.setLayoutParams(params);


                            title.setText(SourceDateList.get(
                                    getPositionForSection(section)).getLetter());
                        }


                        if (visibleItemCount != 0) {

                            titleLayout.setVisibility(View.VISIBLE);

                        }


                        if (nextSecPosition == firstVisibleItem + 1) {


                            View childView = view.getChildAt(0);
                            if (childView != null) {
                                int titleHeight = titleLayout.getHeight();
                                int bottom = childView.getBottom();

                                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                        .getLayoutParams();
                                if (bottom < titleHeight) {
                                    float pushedDistance = bottom - titleHeight;
                                    params.topMargin = (int) pushedDistance;
                                    titleLayout.setLayoutParams(params);
                                } else {
                                    if (params.topMargin != 0) {
                                        params.topMargin = 0;
                                        titleLayout.setLayoutParams(params);
                                    }
                                }
                            }
                        }
                        lastFirstVisibleItem = firstVisibleItem;

                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest2);


        GsonRequest<FindCarOnePageGvBean> gsonRequest3 = new GsonRequest<FindCarOnePageGvBean>(URLAll.HOTBRAND_URL, FindCarOnePageGvBean.class, new Response.Listener<FindCarOnePageGvBean>() {
            @Override
            public void onResponse(FindCarOnePageGvBean bean) {

                onePage_GridView = (RecyclerView) view.findViewById(R.id.onePage_GridView);
                OnePageGvAdp onePageGvAdp = new OnePageGvAdp(context);
                onePageGvAdp.setArrayList(bean);
                onePage_GridView.setAdapter(onePageGvAdp);
                onePage_GridView.setLayoutManager(new GridLayoutManager(context, 5));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest3);

        GsonRequest<FindCarOnePageGvBean> gsonRequest4 = new GsonRequest<FindCarOnePageGvBean>(URLAll.MAINCAR_URL, FindCarOnePageGvBean.class, new Response.Listener<FindCarOnePageGvBean>() {
            @Override
            public void onResponse(FindCarOnePageGvBean bean) {


                Picasso.with(context).load(bean.getResult().getList().get(0).getImg()).into(findCar_OnePage_Flagship_ImagView0);
                Picasso.with(context).load(bean.getResult().getList().get(1).getImg()).into(findCar_OnePage_Flagship_ImagView1);
                Picasso.with(context).load(bean.getResult().getList().get(2).getImg()).into(findCar_OnePage_Flagship_ImagView2);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest4);


    }

    @Override
    protected int setContentView() {
        return R.layout.findcar_onepage;
    }


    @Override
    protected void initViews() {

        findCar_OnePage_First_RecyclerView = findById(R.id.findCar_OnePage_First_RecyclerView);
        titleLayout = (LinearLayout) findById(R.id.title_layout);
        title = (TextView) this.findById(R.id.title_layout_catalog);
        textvv = (TextView) findById(R.id.textvv);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findById(R.id.sidrbar);
        sortListView = (ListView) findById(R.id.country_lvcountry);
        view = LayoutInflater.from(context).inflate(R.layout.onepageframent_headerview, null);
        findCar_OnePage_Flagship_ImagView0 = findById(R.id.findCar_OnePage_Flagship_ImagView0, view);
        findCar_OnePage_Flagship_ImagView1 = findById(R.id.findCar_OnePage_Flagship_ImagView1, view);
        findCar_OnePage_Flagship_ImagView2 = findById(R.id.findCar_OnePage_Flagship_ImagView2, view);

        sortListView.addHeaderView(view);

    }

    @Override
    protected void initListeners() {

        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置

                textvv.setText(s);
                textvv.setTextSize(50);

                AlphaAnimation alp = new AlphaAnimation(1.0f, 0.0f);
                alp.setDuration(1000);
                textvv.setAnimation(alp);
                textvv.setVisibility(View.INVISIBLE);
                try {
                    int position = adapter.getPositionForSection(s.charAt(0));
                    if (position != -1) {
                        sortListView.setSelection(position + 1);
                    }
                } catch (NullPointerException e) {


                }


                if (s.equals("主") || s.equals("热") || s.equals("选") || s.equals("历")) {

                    sortListView.setSelection(0);

                }


            }


        });


        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    return;
                }
                position = position - 1;
                Toast.makeText(
                        context,

                        ((ListViewBean) adapter.getItem(position)).getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * 为ListView填充数据
     */
    private List<ListViewBean> filledData(AllVehiclesBean bean) {
        List<ListViewBean> mSortList = new ArrayList<ListViewBean>();

        for (int i = 0; i < bean.getResult().getBrandlist().size(); i++) {

            for (int j = 0; j < bean.getResult().getBrandlist().get(i).getList().size(); j++) {
                ListViewBean friend = new ListViewBean();
                friend.setName(bean.getResult().getBrandlist().get(i).getList().get(j).getName());
                friend.setUrl(bean.getResult().getBrandlist().get(i).getList().get(j).getImgurl());

                friend.setLetter(bean.getResult().getBrandlist().get(i).getLetter());
                mSortList.add(friend);
            }


        }
        return mSortList;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return SourceDateList.get(position).getLetter().charAt(0);
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < SourceDateList.size(); i++) {
            String sortStr = SourceDateList.get(i).getLetter();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}



