package lanou.amg1.forumfragment.page.onepagefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.VolleySingleton;
import lanou.amg1.gsonrequest.xlistview.XListView;
import lanou.amg1.urlall.URLAll;

public class OnePageFirstActivity extends AppCompatActivity implements View.OnClickListener, XListView.IXListViewListener {

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
    private OnPageFirstAdapter onPageFirstAdapter;
    private ImageView collection;
    private Handler mHandler;
    private boolean Load = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getIntExtra("layout", 2) == 1) {
            setContentView(R.layout.activity_one_page_first);

            onePageActivity_LinearLayout = (LinearLayout) findViewById(R.id.onePageActivity_LinearLayout);
            one_Page_First_Title = (TextView) findViewById(R.id.one_Page_First_Title);
            one_Page_First_Title.setText(getIntent().getStringExtra("onePageHeaderTitle"));
            onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);
            onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
            onPageFirstActivcty_listView = (XListView) findViewById(R.id.onPageFirstActivcty_listView);

            onPageFirstActivcty_listView.setXListViewListener(this);
            onPageFirstActivcty_listView.setPullLoadEnable(true);
            mHandler = new Handler();




            GsonRequest<OnePageFirstBean> gsonRequest1 = new GsonRequest<OnePageFirstBean>(getIntent().getStringExtra("onePageHeader"), OnePageFirstBean.class, new Response.Listener<OnePageFirstBean>() {


                @Override
                public void onResponse(OnePageFirstBean bean) {


                    onPageFirstAdapter = new OnPageFirstAdapter(OnePageFirstActivity.this);
                    onPageFirstAdapter.setBean(bean);
                    onPageFirstActivcty_listView.setAdapter(onPageFirstAdapter);


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

        }


        //全部页
        if (getIntent().getIntExtra("layout", 3) == 2) {

            setContentView(R.layout.activity_one_page_first_all);
            onePageActivity_LinearLayout = (LinearLayout) findViewById(R.id.onePageActivity_LinearLayout);
            one_Page_First_All_WebView = (WebView) findViewById(R.id.one_Page_First_All_WebView);
            one_Page_First_All_WebView.setVisibility(View.INVISIBLE);
            one_Page_First_All_WebView.loadUrl(getIntent().getStringExtra("OnePageFragmentBean"));
            one_Page_First_All_WebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // TODO Auto-generated method stub
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });

            one_Page_First_All_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

            WebSettings settings = one_Page_First_All_WebView.getSettings();
            settings.setJavaScriptEnabled(true);
            onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);
            onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
            one_Page_First_All_ListView = (ListView) findViewById(R.id.one_Page_First_All_ListView);
            one_Page_First_All_ListView.setVisibility(View.VISIBLE);
            OnPageFirstTwoAdapter onPageFirstTwoAdapter = new OnPageFirstTwoAdapter(this);
            onPageFirstTwoAdapter.setBean(URLAll.ARRLIST_ALL());
            one_Page_First_All_ListView.setAdapter(onPageFirstTwoAdapter);

            one_Page_First_All_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(OnePageFirstActivity.this, OnePageFirstActivity.class);
                    intent.putExtra("layout", 1);
                    intent.putExtra("onePageHeaderTitle", URLAll.ARRLIST_ALL().get(position).getTitle());
                    intent.putExtra("onePageHeader", URLAll.ARRLIST_ALL().get(position).getUrl());
                    startActivity(intent);
                    finish();

                }
            });
            sharedPreferences1 = this.getSharedPreferences("PersonalFragment", this.MODE_PRIVATE);

            visible = sharedPreferences1.getBoolean("VISIBLE", true);
            if (visible) {

                onePageActivity_LinearLayout.setVisibility(View.INVISIBLE);

            } else {
                onePageActivity_LinearLayout.setVisibility(View.VISIBLE);

            }


        }

//下面listview 每个点击webview
        if (getIntent().getIntExtra("layout", 3) == 3) {


            setContentView(R.layout.activity_one_page_first_all);
            ShareSDK.initSDK(this);
            share_relayout = (RelativeLayout) findViewById(R.id.share_relayout);
            share_relayout.setVisibility(View.VISIBLE);
            share = (ImageView) findViewById(R.id.share);
            collection = (ImageView) findViewById(R.id.collection);
            share.setOnClickListener(this);
            collection.setOnClickListener(this);
            onePageActivity_LinearLayout = (LinearLayout) findViewById(R.id.onePageActivity_LinearLayout);
            one_Page_First_All_ListView = (ListView) findViewById(R.id.one_Page_First_All_ListView);
            onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);
            one_Page_First_All_WebView = (WebView) findViewById(R.id.one_Page_First_All_WebView);
            one_Page_First_All_WebView.setVisibility(View.VISIBLE);
            one_Page_First_All_ListView.setVisibility(View.INVISIBLE);

            one_Page_First_All_WebView.loadUrl(getIntent().getStringExtra("OnePageFragmentBean"));
            one_Page_First_All_WebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // TODO Auto-generated method stub
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });

            one_Page_First_All_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

            WebSettings settings = one_Page_First_All_WebView.getSettings();
            settings.setJavaScriptEnabled(true);
            onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
            sharedPreferences1 = this.getSharedPreferences("PersonalFragment", this.MODE_PRIVATE);

            visible = sharedPreferences1.getBoolean("VISIBLE", true);
            if (visible) {

                onePageActivity_LinearLayout.setVisibility(View.INVISIBLE);

            } else {
                onePageActivity_LinearLayout.setVisibility(View.VISIBLE);

            }
        }


        if (getIntent().getIntExtra("layout", 3) == 4) {
            setContentView(R.layout.activity_hot);
            onePageActivity_LinearLayout = (LinearLayout) findViewById(R.id.onePageActivity_LinearLayout);

            onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);

            onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
            onPageFirstActivcty_listView = (XListView) findViewById(R.id.onPageFirstHotActivcty_listView);

            LinearLayout layout = new LinearLayout(this);
            TextView view = new TextView(this);
            view.setText(R.string.hot);
            layout.addView(view);
            onPageFirstActivcty_listView.addHeaderView(layout);
            onPageFirstActivcty_listView.setXListViewListener(this);
            mHandler = new Handler();

            GsonRequest<OnePageFirstHotBean> gsonRequest1 = new GsonRequest<OnePageFirstHotBean>(getIntent().getStringExtra("onePageHeaderHot"), OnePageFirstHotBean.class, new Response.Listener<OnePageFirstHotBean>() {
                @Override
                public void onResponse(OnePageFirstHotBean bean) {

                    OnPageFirstHotAdapter onPageFirstHotAdapter = new OnPageFirstHotAdapter(OnePageFirstActivity.this);
                    onPageFirstHotAdapter.setBean(bean);
                    onPageFirstActivcty_listView.setAdapter(onPageFirstHotAdapter);


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
        }


        //
        if (getIntent().getIntExtra("layout", 3) == 5) {

            setContentView(R.layout.activity_one_page_first_all);
            onePageActivity_LinearLayout = (LinearLayout) findViewById(R.id.onePageActivity_LinearLayout);

            one_page_first_all_progress = (ProgressBar) findViewById(R.id.one_page_first_all_progress);
            one_Page_First_Title = (TextView) findViewById(R.id.one_Page_First_Title);
            one_Page_First_Title.setText(getIntent().getStringExtra("title"));
            one_Page_First_All_WebView = (WebView) findViewById(R.id.one_Page_First_All_WebView);
            one_Page_First_All_WebView.setVisibility(View.VISIBLE);
            setHandler();
            one_Page_First_All_WebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // TODO Auto-generated method stub
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });

            one_Page_First_All_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

            WebSettings settings = one_Page_First_All_WebView.getSettings();
            settings.setJavaScriptEnabled(true);
            onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);
            onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });


            sharedPreferences1 = this.getSharedPreferences("PersonalFragment", this.MODE_PRIVATE);

            visible = sharedPreferences1.getBoolean("VISIBLE", true);
            if (visible) {

                onePageActivity_LinearLayout.setVisibility(View.INVISIBLE);

            } else {
                onePageActivity_LinearLayout.setVisibility(View.VISIBLE);

            }


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

                            onPageFirstAdapter.setBean(bean);

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


                                onPageFirstAdapter.setBean1(bean);

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
