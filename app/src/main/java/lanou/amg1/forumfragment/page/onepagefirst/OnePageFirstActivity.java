package lanou.amg1.forumfragment.page.onepagefirst;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import lanou.amg1.R;
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.VolleySingleton;
import lanou.amg1.urlall.URLAll;

public class OnePageFirstActivity extends AppCompatActivity {

    private PullToRefreshListView onPageFirstActivcty_listView;
    private ImageView onePage_ImageView;
    private TextView one_Page_First_Title;
    private ListView one_Page_First_All_ListView;
    private WebView one_Page_First_All_WebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getIntExtra("layout", 2) == 1) {
            setContentView(R.layout.activity_one_page_first);
            one_Page_First_Title = (TextView) findViewById(R.id.one_Page_First_Title);
            one_Page_First_Title.setText(getIntent().getStringExtra("onePageHeaderTitle"));
            onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);
            onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
            onPageFirstActivcty_listView = (PullToRefreshListView) findViewById(R.id.onPageFirstActivcty_listView);


            GsonRequest<OnePageFirstBean> gsonRequest1 = new GsonRequest<OnePageFirstBean>(getIntent().getStringExtra("onePageHeader"), OnePageFirstBean.class, new Response.Listener<OnePageFirstBean>() {
                @Override
                public void onResponse(OnePageFirstBean bean) {

                    inAdapter(bean);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });

            VolleySingleton.getInstance().addRequest(gsonRequest1);


        }



        if (getIntent().getIntExtra("layout", 3) == 2) {

            setContentView(R.layout.activity_one_page_first_all);
            one_Page_First_All_WebView = (WebView) findViewById(R.id.one_Page_First_All_WebView);
            one_Page_First_All_WebView.setVisibility(View.INVISIBLE);
            onePage_ImageView = (ImageView) findViewById(R.id.onePage_ImageView);
            onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
            one_Page_First_All_ListView = (ListView)findViewById(R.id.one_Page_First_All_ListView);
            one_Page_First_All_ListView.setVisibility(View.VISIBLE);
            OnPageFirstTwoAdapter onPageFirstTwoAdapter = new OnPageFirstTwoAdapter(this);
            onPageFirstTwoAdapter.setBean(URLAll.ARRLIST_ALL());
            one_Page_First_All_ListView.setAdapter(onPageFirstTwoAdapter);

            one_Page_First_All_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(OnePageFirstActivity.this,OnePageFirstActivity.class);
                    intent.putExtra("layout",1);
                    intent.putExtra("onePageHeaderTitle",URLAll.ARRLIST_ALL().get(position).getTitle());
                    intent.putExtra("onePageHeader",URLAll.ARRLIST_ALL().get(position).getUrl());
                    startActivity(intent);
                    finish();

                }
            });




        }


        if(getIntent().getIntExtra("layout", 3) == 3){

            setContentView(R.layout.activity_one_page_first_all);
            one_Page_First_All_ListView = (ListView)findViewById(R.id.one_Page_First_All_ListView);
            onePage_ImageView = (ImageView)findViewById(R.id.onePage_ImageView);
            one_Page_First_All_WebView = (WebView) findViewById(R.id.one_Page_First_All_WebView);
            one_Page_First_All_WebView.setVisibility(View.VISIBLE);
            one_Page_First_All_ListView.setVisibility(View.INVISIBLE);

            one_Page_First_All_WebView.loadUrl(getIntent().getStringExtra("OnePageFragmentBean"));
            onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });


        }

        if(getIntent().getIntExtra("layout", 3) == 4){
            setContentView(R.layout.activity_hot);
            onePage_ImageView =(ImageView) findViewById(R.id.onePage_ImageView);

            onePage_ImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
            onPageFirstActivcty_listView = (PullToRefreshListView) findViewById(R.id.onPageFirstHotActivcty_listView);
            LinearLayout layout = new LinearLayout(this);
            TextView view = new TextView(this);
            view.setText(R.string.hot);
            layout.addView(view);
            onPageFirstActivcty_listView.getRefreshableView().addHeaderView(layout);

            GsonRequest<OnePageFirstHotBean> gsonRequest1 = new GsonRequest<OnePageFirstHotBean>(getIntent().getStringExtra("onePageHeaderHot"), OnePageFirstHotBean.class, new Response.Listener<OnePageFirstHotBean>() {
                @Override
                public void onResponse(OnePageFirstHotBean bean) {

                    inAdapter(bean);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });

            VolleySingleton.getInstance().addRequest(gsonRequest1);

        }

    }

    public void inAdapter(OnePageFirstBean bean){
        OnPageFirstAdapter onPageFirstAdapter = new OnPageFirstAdapter(this);
        onPageFirstAdapter.setBean(bean);
        onPageFirstActivcty_listView.setAdapter(onPageFirstAdapter);

    }
    public void inAdapter(OnePageFirstHotBean bean){
        OnPageFirstHotAdapter onPageFirstHotAdapter = new OnPageFirstHotAdapter(this);
        onPageFirstHotAdapter.setBean(bean);
        onPageFirstActivcty_listView.setAdapter(onPageFirstHotAdapter);


    }

}
