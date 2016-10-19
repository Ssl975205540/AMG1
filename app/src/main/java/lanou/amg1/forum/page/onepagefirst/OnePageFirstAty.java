package lanou.amg1.forum.page.onepagefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import lanou.amg1.R;
import lanou.amg1.base.BaseActivity;
import lanou.amg1.bean.OnePageFirstBean;
import lanou.amg1.bean.OnePageFirstHotBean;
import lanou.amg1.tools.requset.GsonRequest;
import lanou.amg1.tools.requset.VolleySingleton;
import lanou.amg1.tools.xlistview.XListView;
import lanou.amg1.tools.URLAll;

public class OnePageFirstAty extends BaseActivity implements View.OnClickListener, XListView.IXListViewListener {

    private XListView onPageFirstActivcty_listView;
    private ImageView onePage_ImageView;
    private TextView one_Page_First_Title;
    private ListView one_Page_First_All_ListView;
    private WebView one_Page_First_All_WebView;
    private LinearLayout onePageActivity_LinearLayout;
    private SharedPreferences sharedPreferences1;
    //该程序模拟天成长度为100的数组
    private int[] data = new int[100];
    protected boolean isVisible;
    int hasData = 0;
    private boolean isReady = false;
    private int i = 1;
    // 记录ProgressBar的完成进度
    int progressStatus = 0;
    private static ProgressBar one_page_first_all_progress;
    private boolean visible;
    private RelativeLayout share_relayout;
    private ImageView share;
    private OnPageFirstAdp onPageFirstAdp;
    private ImageView collection;
    private Handler mHandler;
    private int intLayout;
    private boolean Load = true;
    private WebSettings settings;



    @Override
    protected void initData() {
        switch (getIntent().getIntExtra("layout", 2)){

            case 1:
                one_Page_First_Title.setText(getIntent().getStringExtra("onePageHeaderTitle"));

                mHandler = new Handler();




                GsonRequest<OnePageFirstBean> gsonRequest1 = new GsonRequest<OnePageFirstBean>(getIntent().getStringExtra("onePageHeader"), OnePageFirstBean.class, new Response.Listener<OnePageFirstBean>() {


                    @Override
                    public void onResponse(OnePageFirstBean bean) {


                        onPageFirstAdp = new OnPageFirstAdp(OnePageFirstAty.this);
                        onPageFirstAdp.setBean(bean);
                        onPageFirstActivcty_listView.setAdapter(onPageFirstAdp);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

                VolleySingleton.getInstance().addRequest(gsonRequest1);
                sharedPreferences1 = this.getSharedPreferences("PersonalFragment", this.MODE_PRIVATE);
                visible = sharedPreferences1.getBoolean("VISIBLE", true);
                if (visible) {

                    onePageActivity_LinearLayout.setVisibility(View.INVISIBLE);

                } else {
                    onePageActivity_LinearLayout.setVisibility(View.VISIBLE);

                }


                break;
            case 2:

                one_Page_First_All_WebView.setVisibility(View.INVISIBLE);
                one_Page_First_All_WebView.loadUrl(getIntent().getStringExtra("OnePageFragmentBean"));


                one_Page_First_All_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

                WebSettings settings = one_Page_First_All_WebView.getSettings();
                settings.setJavaScriptEnabled(true);

                one_Page_First_All_ListView.setVisibility(View.VISIBLE);
                OnPageFirstTwoAdp onPageFirstTwoAdp = new OnPageFirstTwoAdp(this);
                onPageFirstTwoAdp.setBean(URLAll.ARRLIST_ALL());
                one_Page_First_All_ListView.setAdapter(onPageFirstTwoAdp);


                sharedPreferences1 = this.getSharedPreferences("PersonalFragment", this.MODE_PRIVATE);

                visible = sharedPreferences1.getBoolean("VISIBLE", true);
                if (visible) {

                    onePageActivity_LinearLayout.setVisibility(View.INVISIBLE);

                } else {
                    onePageActivity_LinearLayout.setVisibility(View.VISIBLE);

                }


                break;
            case 3:
                share_relayout.setVisibility(View.VISIBLE);

                one_Page_First_All_WebView.setVisibility(View.VISIBLE);
                one_Page_First_All_ListView.setVisibility(View.INVISIBLE);

                one_Page_First_All_WebView.loadUrl(getIntent().getStringExtra("OnePageFragmentBean"));

                one_Page_First_All_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

                settings = one_Page_First_All_WebView.getSettings();
                settings.setJavaScriptEnabled(true);
                sharedPreferences1 = this.getSharedPreferences("PersonalFragment", this.MODE_PRIVATE);

                visible = sharedPreferences1.getBoolean("VISIBLE", true);
                if (visible) {

                    onePageActivity_LinearLayout.setVisibility(View.INVISIBLE);

                } else {
                    onePageActivity_LinearLayout.setVisibility(View.VISIBLE);

                }

                break;
            case 4:

                LinearLayout layout = new LinearLayout(this);
                TextView view = new TextView(this);
                view.setText(R.string.hot);
                layout.addView(view);
                onPageFirstActivcty_listView.addHeaderView(layout);
                mHandler = new Handler();

                GsonRequest<OnePageFirstHotBean> gsonRequest2 = new GsonRequest<OnePageFirstHotBean>(getIntent().getStringExtra("onePageHeaderHot"), OnePageFirstHotBean.class, new Response.Listener<OnePageFirstHotBean>() {
                    @Override
                    public void onResponse(OnePageFirstHotBean bean) {

                        OnPageFirstHotAdp onPageFirstHotAdp = new OnPageFirstHotAdp(OnePageFirstAty.this);
                        onPageFirstHotAdp.setBean(bean);
                        onPageFirstActivcty_listView.setAdapter(onPageFirstHotAdp);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

                VolleySingleton.getInstance().addRequest(gsonRequest2);
                sharedPreferences1 = this.getSharedPreferences("PersonalFragment", this.MODE_PRIVATE);

                visible = sharedPreferences1.getBoolean("VISIBLE", true);
                if (visible) {

                    onePageActivity_LinearLayout.setVisibility(View.INVISIBLE);

                } else {
                    onePageActivity_LinearLayout.setVisibility(View.VISIBLE);

                }

                break;

            case 5:

                one_Page_First_Title.setText(getIntent().getStringExtra("title"));
                one_Page_First_All_WebView.setVisibility(View.VISIBLE);
                setHandler();


                one_Page_First_All_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

                settings = one_Page_First_All_WebView.getSettings();
                settings.setJavaScriptEnabled(true);


                sharedPreferences1 = this.getSharedPreferences("PersonalFragment", this.MODE_PRIVATE);

                visible = sharedPreferences1.getBoolean("VISIBLE", true);
                if (visible) {

                    onePageActivity_LinearLayout.setVisibility(View.INVISIBLE);

                } else {
                    onePageActivity_LinearLayout.setVisibility(View.VISIBLE);

                }

                break;


        }

    }

    @Override
    protected int setContentView() {
        return intLayout;
    }

    @Override
    protected void initViews() {
        switch (getIntent().getIntExtra("layout", 2)){

            case 1:

                onePageActivity_LinearLayout = (LinearLayout) findViewById(R.id.onePageActivity_LinearLayout);
                one_Page_First_Title = (TextView) findViewById(R.id.one_Page_First_Title);
                onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);
                onPageFirstActivcty_listView = (XListView) findViewById(R.id.onPageFirstActivcty_listView);

                break;
            case 2:
                onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);

                one_Page_First_All_ListView = (ListView) findViewById(R.id.one_Page_First_All_ListView);
                onePageActivity_LinearLayout = (LinearLayout) findViewById(R.id.onePageActivity_LinearLayout);
                one_Page_First_All_WebView = (WebView) findViewById(R.id.one_Page_First_All_WebView);
                break;
            case 3:
                ShareSDK.initSDK(this);
                share_relayout = (RelativeLayout) findViewById(R.id.share_relayout);
                share = (ImageView) findViewById(R.id.share);
                collection = (ImageView) findViewById(R.id.collection);
                onePageActivity_LinearLayout = (LinearLayout) findViewById(R.id.onePageActivity_LinearLayout);
                one_Page_First_All_ListView = (ListView) findViewById(R.id.one_Page_First_All_ListView);
                onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);
                one_Page_First_All_WebView = (WebView) findViewById(R.id.one_Page_First_All_WebView);
                break;
            case 4:
                onePageActivity_LinearLayout = (LinearLayout) findViewById(R.id.onePageActivity_LinearLayout);

                onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);
                onPageFirstActivcty_listView = (XListView) findViewById(R.id.onPageFirstHotActivcty_listView);

                break;

            case 5:
                onePageActivity_LinearLayout = (LinearLayout) findViewById(R.id.onePageActivity_LinearLayout);

                onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);
                one_page_first_all_progress = (ProgressBar) findViewById(R.id.one_page_first_all_progress);
                one_Page_First_All_WebView = (WebView) findViewById(R.id.one_Page_First_All_WebView);
                one_Page_First_Title = (TextView) findViewById(R.id.one_Page_First_Title);


                break;


        }


    }

    @Override
    protected void initListeners() {
        switch (getIntent().getIntExtra("layout", 2)){

            case 1:


                onPageFirstActivcty_listView.setXListViewListener(this);
                onPageFirstActivcty_listView.setPullLoadEnable(true);
                onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();

                    }
                });

                break;
            case 2:
                one_Page_First_All_WebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        // TODO Auto-generated method stub
                        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                        view.loadUrl(url);
                        return true;
                    }
                });

                onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();

                    }
                });

                one_Page_First_All_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(OnePageFirstAty.this, OnePageFirstAty.class);
                        intent.putExtra("layout", 1);
                        intent.putExtra("onePageHeaderTitle", URLAll.ARRLIST_ALL().get(position).getTitle());
                        intent.putExtra("onePageHeader", URLAll.ARRLIST_ALL().get(position).getUrl());
                        startActivity(intent);
                        finish();

                    }
                });


                break;
            case 3:
                share.setOnClickListener(this);
                collection.setOnClickListener(this);
                onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();

                    }
                });
                one_Page_First_All_WebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        // TODO Auto-generated method stub
                        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                        view.loadUrl(url);
                        return true;
                    }
                });
                break;
            case 4:
                onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();

                    }
                });
                onPageFirstActivcty_listView.setXListViewListener(this);
                break;

            case 5:
                onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();

                    }
                });

                one_Page_First_All_WebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        // TODO Auto-generated method stub
                        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                        view.loadUrl(url);
                        return true;
                    }
                });

                break;


        }
    }

    @Override
    protected void initJudge() {


        switch (getIntent().getIntExtra("layout", 2)){

            case 1:

                intLayout = R.layout.activity_one_page_first;

                break;
            case 2:
                intLayout = R.layout.activity_one_page_first_all;
                break;
            case 3:
                intLayout = R.layout.activity_one_page_first_all;
                break;
            case 4:
                intLayout = R.layout.activity_hot;
                break;

            case 5:
                intLayout = R.layout.activity_one_page_first_all;
                break;


        }




    }

    public void inAdapter(OnePageFirstHotBean bean) {

    }

    //模拟一个耗时的操作
    private int doWork() {
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasData;
    }

    private int doWork(int i) {

        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasData;
    }

    private void setHandler() {


        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x111) {
                    one_page_first_all_progress.setProgress(progressStatus);
                }


                if (progressStatus == 80) {

                    one_Page_First_All_WebView.loadUrl(getIntent().getStringExtra("OnePageFragmentBean"));

                }
                if (hasData == 100) {

                    one_page_first_all_progress.setVisibility(View.GONE);
                }

            }
        };
        // 启动线程来执行任务
        new Thread() {
            public void run() {
                while (progressStatus < 100) {
                    // 获取耗时的完成百分比


                    Message m = new Message();

                    if (progressStatus == 80) {

                        progressStatus = doWork(80);

                        m.what = 0x111;
                        // 发送消息到Handler
                        handler.sendMessage(m);
                    } else {
                        progressStatus = doWork();
                        m.what = 0x111;
                        // 发送消息到Handler
                        handler.sendMessage(m);
                    }


                }


            }
        }.start();
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.share:
                showShare();


                break;
            case R.id.collection:


                break;

        }


    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }


    private void Up_Mode(PullToRefreshListView listView) {
        // 设置PullToRefreshListView的模式
        listView.setMode(PullToRefreshBase.Mode.BOTH);

        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载...");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多...");
        // 设置PullRefreshListView下拉加载时的加载提示
        listView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
        listView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开加载更多...");

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    public void onRefresh() {

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (getIntent().getIntExtra("layout", 2) == 1) {

                    GsonRequest<OnePageFirstBean> gsonRequest1 = new GsonRequest<OnePageFirstBean>(getIntent().getStringExtra("onePageHeader"), OnePageFirstBean.class, new Response.Listener<OnePageFirstBean>() {

                        @Override
                        public void onResponse(OnePageFirstBean bean) {

                            onPageFirstAdp.setBean(bean);

                            onPageFirstActivcty_listView.stopRefresh();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                        }
                    });

                    VolleySingleton.getInstance().addRequest(gsonRequest1);

                }
                if (getIntent().getIntExtra("layout", 3) == 4) {




                    onPageFirstActivcty_listView.stopRefresh();


                }

            }
        }, 600);


    }

    @Override
    public void onLoadMore() {

        if (Load) {

            Load = false;
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {


                    if (getIntent().getIntExtra("layout", 2) == 1) {

                        String url = getIntent().getStringExtra("onePageHeader");

                        i++;
                        GsonRequest<OnePageFirstBean> gsonRequest1 = new GsonRequest<OnePageFirstBean>(url.substring(0, url.length() - 10) + i + "-s30.json", OnePageFirstBean.class, new Response.Listener<OnePageFirstBean>() {
                            @Override
                            public void onResponse(OnePageFirstBean bean) {


                                onPageFirstAdp.setBean1(bean);

                                onPageFirstActivcty_listView.stopLoadMore();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {


                            }
                        });

                        VolleySingleton.getInstance().addRequest(gsonRequest1);
                    }


                    if (getIntent().getIntExtra("layout", 3) == 4) {









                    }





                }
            }, 0);
        }
    }
}
