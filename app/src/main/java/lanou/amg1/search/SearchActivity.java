package lanou.amg1.search;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import lanou.amg1.R;
import lanou.amg1.gsonrequest.EncodeUtil;
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.VolleySingleton;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    //该程序模拟天成长度为100的数组
    private int[] data = new int[100];
    protected boolean isVisible;
    int hasData = 0;
    private boolean isReady = false;
    // 记录ProgressBar的完成进度
    int progressStatus = 0;
    private WebView activity_Search_WebView;
    private EditText activity_Search_editText;
    private ListView activity_Search_ListView;
    private SQLiteDatabase database;
    private int variable;
    private LinearLayout activity_close_relayout;
    private RelativeLayout activity_history_relayout;
    private LinearLayout search_activity__LinearLayout_All;
    private SharedPreferences sharedPreferences1;
    private boolean visible;
    private ProgressBar activity_Search_ProgressBar;
    private ActivitySearchListViewAdapter activitySearchListViewAdapter;
    private ImageView close_all;
    private TextView search_activity__finsh_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        activity_Search_WebView = (WebView) findViewById(R.id.activity_Search_WebView);
        activity_Search_editText = (EditText) findViewById(R.id.activity_Search_editText);
        activity_Search_ListView = (ListView) findViewById(R.id.activity_Search_ListView);
        close_all = (ImageView) findViewById(R.id.close_all);
        search_activity__finsh_textview = (TextView)findViewById(R.id.search_activity__finsh_textview);
        search_activity__finsh_textview.setOnClickListener(this);
        close_all.setOnClickListener(this);
        activity_Search_ProgressBar = (ProgressBar) findViewById(R.id.activity_Search_ProgressBar);
        search_activity__LinearLayout_All = (LinearLayout) findViewById(R.id.search_activity__LinearLayout_All);
        activity_history_relayout = (RelativeLayout) findViewById(R.id.activity_history_relayout);
        activity_close_relayout = (LinearLayout) findViewById(R.id.activity_close_relayout);
        MySQLite mySQLite = new MySQLite(this, "search.db", null, 1);
        database = mySQLite.getWritableDatabase();
        activity_Search_editText.addTextChangedListener(textWatcher);
        activity_Search_editText.setOnEditorActionListener(myOnEditorActionListener);
        activity_close_relayout.setOnClickListener(this);
        activity_Search_editText.setHint(getIntent().getStringExtra("search"));
        setOnItem();
        setMoon();
        Cursor cursor = database.query("historyrecord", null, null, null, null, null, null);
        ArrayList<String> arrayList = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                arrayList.add(0, name);
            }

        }
        if (arrayList.size() != 0) {
            activitySearchListViewAdapter = new ActivitySearchListViewAdapter(SearchActivity.this);
            activitySearchListViewAdapter.setBean(arrayList, 0);
            activity_Search_ListView.setVisibility(View.VISIBLE);
            activity_Search_ListView.setAdapter(activitySearchListViewAdapter);

            Log.d("SearchActivity", "wocao");
            activity_history_relayout.setVisibility(View.VISIBLE);
        } else {

            activity_history_relayout.setVisibility(View.GONE);

        }

        activity_Search_WebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        variable = activity_Search_editText.getText().toString().length();
        activity_Search_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        WebSettings settings = activity_Search_WebView.getSettings();
        settings.setJavaScriptEnabled(true);


    }

    private void setMoon() {
        sharedPreferences1 = this.getSharedPreferences("PersonalFragment", this.MODE_PRIVATE);
        visible = sharedPreferences1.getBoolean("VISIBLE", true);
        if (visible) {

            search_activity__LinearLayout_All.setVisibility(View.INVISIBLE);

        } else {
            search_activity__LinearLayout_All.setVisibility(View.VISIBLE);

        }

    }


    private TextWatcher textWatcher = new TextWatcher() {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (activity_Search_editText.getText().toString().length() > variable) {
                variable = activity_Search_editText.getText().toString().length();

                setString(s);
                activity_history_relayout.setVisibility(View.GONE);
                close_all.setVisibility(View.VISIBLE);

            } else {
                variable = activity_Search_editText.getText().toString().length();

                activity_Search_ListView.setVisibility(View.VISIBLE);
                close_all.setVisibility(View.VISIBLE);
                if (activity_Search_editText.getText().toString().length() > 1 ) {
                    setString(s);
                    activity_close_relayout.setVisibility(View.INVISIBLE);

                    activity_Search_WebView.setVisibility(View.INVISIBLE);
                }else {

                    close_all.setVisibility(View.GONE);
                    Cursor cursor = database.query("historyrecord", null, null, null, null, null, null);
                    ArrayList<String> arrayList = new ArrayList<>();
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            arrayList.add(0, name);
                        }

                    }
                    if (cursor.getCount() != 0) {


                        activitySearchListViewAdapter = new ActivitySearchListViewAdapter(SearchActivity.this);
                        activitySearchListViewAdapter.setBean(arrayList, 0);
                        activity_Search_ListView.setAdapter(activitySearchListViewAdapter);
                        activity_history_relayout.setVisibility(View.VISIBLE);
                        activity_close_relayout.setVisibility(View.VISIBLE);


                    } else {

                        activity_Search_ListView.setVisibility(View.INVISIBLE);
                    }
                }


            }


        }

        @Override
        public void afterTextChanged(Editable s) {


            Log.d("SearchActivity", "这个是");

        }
    };

    public void setString(CharSequence s) {

        GsonRequest<ActivitySearchBean> onePageFragmentBeanGsonRequest = new GsonRequest<ActivitySearchBean>("http://mobilenc.app.autohome.com.cn/sou_v5.7.0/sou/suggestwords.ashx?pm=2&k=" + s + "&t=4", ActivitySearchBean.class, new Response.Listener<ActivitySearchBean>() {
            @Override
            public void onResponse(ActivitySearchBean bean) {

                activitySearchListViewAdapter = new ActivitySearchListViewAdapter(SearchActivity.this);

                activitySearchListViewAdapter.setBean(bean);

                activity_Search_ListView.setAdapter(activitySearchListViewAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance().addRequest(onePageFragmentBeanGsonRequest);


    }


    private TextView.OnEditorActionListener myOnEditorActionListener = new TextView.OnEditorActionListener() {

        private TextView tv_pop;

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                // do something

                if (activity_Search_editText.getText().toString().length() > 0) {
                    Log.d("SearchActivity", "2");

                    hasData = 0;

                    progressStatus = 0;
                    activity_Search_ProgressBar.setVisibility(View.VISIBLE);
                    setHandler();
                    activity_Search_WebView.setVisibility(View.VISIBLE);

                    Cursor cursor = database.query("historyrecord", null, null, null, null, null, null);
                    ArrayList<String> arrayList = new ArrayList<>();

                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            arrayList.add(0, name);
                        }

                    }
                   if(arrayList.contains(activity_Search_editText.getText().toString())){
                       Log.d("SearchActivity", "1");

                       database.delete("historyrecord", "name = ?", new String[]{activity_Search_editText.getText().toString()});
                       ContentValues contentValues = new ContentValues();
                       contentValues.put("name", activity_Search_editText.getText().toString());
                       database.insert("historyrecord", null, contentValues);

                   }else {
                       ContentValues contentValues = new ContentValues();
                       contentValues.put("name", activity_Search_editText.getText().toString());
                       database.insert("historyrecord", null, contentValues);
                   }

                    activity_Search_ListView.setVisibility(View.INVISIBLE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(activity_Search_editText.getWindowToken(), 0);
                }


            }
            return true;
        }
    };


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.activity_close_relayout:
                database.delete("historyrecord", null, null);
                Cursor cursor = database.query("historyrecord", null, null, null, null, null, null);
                ArrayList<String> arrayList = new ArrayList<>();
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        arrayList.add(0, name);
                    }

                }

                ActivitySearchListViewAdapter activitySearchListViewAdapter = new ActivitySearchListViewAdapter(SearchActivity.this);
                activitySearchListViewAdapter.setBean(arrayList, 0);
                activity_Search_ListView.setAdapter(activitySearchListViewAdapter);
                activity_history_relayout.setVisibility(View.GONE);


                break;
            case R.id.close_all:

                activity_Search_editText.setText("");
                Cursor cursor1 = database.query("historyrecord", null, null, null, null, null, null);
                ArrayList<String> arrayList1 = new ArrayList<>();

                if (cursor1 != null) {
                    while (cursor1.moveToNext()) {
                        String name = cursor1.getString(cursor1.getColumnIndex("name"));
                        arrayList1.add(0, name);
                    }

                }
                if (arrayList1.size() != 0) {
                    activitySearchListViewAdapter = new ActivitySearchListViewAdapter(SearchActivity.this);
                    activitySearchListViewAdapter.setBean(arrayList1, 0);
                    activity_Search_ListView.setVisibility(View.VISIBLE);
                    activity_Search_ListView.setAdapter(activitySearchListViewAdapter);

                    Log.d("SearchActivity", "wocao");
                    activity_history_relayout.setVisibility(View.VISIBLE);
                } else {

                    activity_history_relayout.setVisibility(View.GONE);

                }
                activity_Search_WebView.setVisibility(View.GONE);

                break;

            case R.id.search_activity__finsh_textview:

                finish();

                break;

        }


    }

    private void setHandler() {


        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x111) {
                    activity_Search_ProgressBar.setProgress(progressStatus);
                }


                if (progressStatus == 80) {

                    activity_Search_WebView.loadUrl("http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword=" + EncodeUtil.encode(activity_Search_editText.getText().toString()) + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0");


                }
                if (hasData == 100) {


                    activity_Search_ProgressBar.setVisibility(View.GONE);

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


    public void setOnItem() {
        activity_Search_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = database.query("historyrecord", null, null, null, null, null, null);
                ArrayList<String> arrayList = new ArrayList<>();

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        arrayList.add(0, name);
                    }

                }
                Log.d("SearchActivity", "activitySearchListViewAdapter.i:" + activitySearchListViewAdapter.i);

                if (1 == activitySearchListViewAdapter.i) {

                    ActivitySearchBean.ResultBean.WordlistBean activitySearchBean = (ActivitySearchBean.ResultBean.WordlistBean) parent.getItemAtPosition(position);

                    activity_Search_editText.setText(activitySearchBean.getName());
                    hasData = 0;

                    progressStatus = 0;
                    activity_Search_ProgressBar.setVisibility(View.VISIBLE);
                    setHandler();
                    activity_Search_ListView.setVisibility(View.INVISIBLE);

                    activity_Search_WebView.setVisibility(View.VISIBLE);
                    activity_Search_WebView.setVisibility(View.VISIBLE);

                    Log.d("SearchActivity", "进来了");



                    if(arrayList.contains(activity_Search_editText.getText().toString())){
                        Log.d("SearchActivity", "进来了");
                        database.delete("historyrecord", "name = ?", new String[]{activity_Search_editText.getText().toString()});
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("name", activity_Search_editText.getText().toString());
                        database.insert("historyrecord", null, contentValues);

                    }else {

                        ContentValues contentValues = new ContentValues();
                        contentValues.put("name", activity_Search_editText.getText().toString());
                        database.insert("historyrecord", null, contentValues);

                    }






                    activity_Search_ListView.setVisibility(View.INVISIBLE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(activity_Search_editText.getWindowToken(), 0);
                    activity_Search_editText.setSelection(activity_Search_editText.getText().length());

//
// setString(activitySearchBean.getName());

                } else if (0 == activitySearchListViewAdapter.i) {

//                    setString((String)parent.getItemAtPosition(position));
                    activity_Search_editText.setText((String) parent.getItemAtPosition(position));
                    hasData = 0;

                    progressStatus = 0;
                    activity_Search_ProgressBar.setVisibility(View.VISIBLE);
                    setHandler();
                    activity_Search_ListView.setVisibility(View.INVISIBLE);

                    activity_Search_WebView.setVisibility(View.VISIBLE);
                    database.delete("historyrecord", "name = ?", new String[]{(String) parent.getItemAtPosition(position)});
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", activity_Search_editText.getText().toString());
                    database.insert("historyrecord", null, contentValues);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(activity_Search_editText.getWindowToken(), 0);

                    activity_Search_editText.setSelection(activity_Search_editText.getText().length());

                }


            }
        });


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


}
