package lanou.amg1.recommendfragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import lanou.amg1.R;
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.MyItemTouchCallback;
import lanou.amg1.gsonrequest.OnRecyclerItemClickListener;
import lanou.amg1.gsonrequest.VolleySingleton;
import lanou.amg1.main.MainActivityBase;
import lanou.amg1.main.SendEvent;
import lanou.amg1.search.MySQLite;
import lanou.amg1.urlall.URLAll;

public class RecommendMoreActivity extends MainActivityBase implements MyItemTouchCallback.OnDragListener {


    public static List<MoreActivityBean.ResultBean.MetalistBean.ListBean> bean = new ArrayList<>();
    private RecyclerView activity_recommend_more_recyclerview;
    private ImageView activity_recommend_more_imageview;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected int setLayout() {
        return R.layout.activity_recommend_more;
    }

    @Override
    protected void control() {

        activity_recommend_more_recyclerview = (RecyclerView) findViewById(R.id.activity_recommend_more_recyclerview);
        activity_recommend_more_imageview = (ImageView) findViewById(R.id.activity_recommend_more_imageview);

        activity_recommend_more_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });
    }

    @Override
    protected void listenIn() {
        MySQLite mySQLite = new MySQLite(this, "search.db", null, 1);
        sqLiteDatabase = mySQLite.getReadableDatabase();
        GsonRequest<MoreActivityBean> gsonRequest = new GsonRequest<MoreActivityBean>(URLAll.MORE_URL, MoreActivityBean.class, new Response.Listener<MoreActivityBean>() {

            private MoreRecyclerviewAdapter moreRecyclerviewAdapter;

            @Override
            public void onResponse(final MoreActivityBean bean) {

                RecommendMoreActivity.bean = new ArrayList<>();
                Cursor cursor = sqLiteDatabase.query("more", null, null, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {

                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String url = cursor.getString(cursor.getColumnIndex("url"));

                        MoreActivityBean.ResultBean.MetalistBean.ListBean m = new MoreActivityBean.ResultBean.MetalistBean.ListBean();
                        m.setName(name);
                        m.setIconurl(url);

                        RecommendMoreActivity.bean.add(m);

                    }

                }


                if (RecommendMoreActivity.bean.size() == 0) {


                    Log.d("RecommendMoreActivity", "ni");
                    RecommendMoreActivity.bean = bean.getResult().getMetalist().get(0).getList();

                }

                moreRecyclerviewAdapter = new MoreRecyclerviewAdapter(RecommendMoreActivity.this);

                moreRecyclerviewAdapter.setMoreActivityBean(RecommendMoreActivity.bean);
                activity_recommend_more_recyclerview.setLayoutManager(new GridLayoutManager(RecommendMoreActivity.this, 3));
                activity_recommend_more_recyclerview.setAdapter(moreRecyclerviewAdapter);

                final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchCallback(moreRecyclerviewAdapter).setOnDragListener(RecommendMoreActivity.this));

                itemTouchHelper.attachToRecyclerView(activity_recommend_more_recyclerview);

                activity_recommend_more_recyclerview.addOnItemTouchListener(new OnRecyclerItemClickListener(activity_recommend_more_recyclerview) {


                    @Override
                    public void onLongClick(RecyclerView.ViewHolder vh) {
                        itemTouchHelper.startDrag(vh);
                        //可以设置第几个不能拖动
//                        if (vh.getLayoutPosition()!=bean.getResult().getMetalist().get(0).getList().size()-1) {
//
//                        }

                    }


                    @Override
                    public void onItemClick(RecyclerView.ViewHolder vh) {

                        Log.d("RecommendMoreActivity", "vh.getLayoutPosition():" + vh.getLayoutPosition());
                        Log.d("RecommendMoreActivity", RecommendMoreActivity.bean.get(vh.getLayoutPosition()).getName());

                        finish();
                        SendEvent sendEvent = new SendEvent();

                        sendEvent.setContent(RecommendMoreActivity.bean.get(vh.getLayoutPosition()).getName());


                        EventBus.getDefault().post(sendEvent);


                    }


                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        VolleySingleton.getInstance().addRequest(gsonRequest);


    }

    @Override
    public void onFinishDrag() {
        //存入缓存


        Log.d("RecommendMoreActivity", "wocao");
        sqLiteDatabase.delete("more", null, null);
        for (int i = 0; i < RecommendMoreActivity.bean.size(); i++) {

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", RecommendMoreActivity.bean.get(i).getName());
            contentValues.put("url", RecommendMoreActivity.bean.get(i).getIconurl());
            sqLiteDatabase.insert("more", null, contentValues);

        }

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
