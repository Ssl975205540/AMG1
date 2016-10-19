package lanou.amg1.recommend;

import android.content.ContentValues;
import android.content.Intent;
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
import lanou.amg1.base.BaseActivity;
import lanou.amg1.bean.MoreAtyBean;
import lanou.amg1.tools.MySQLite;
import lanou.amg1.tools.SendEvent;
import lanou.amg1.tools.URLAll;
import lanou.amg1.tools.recycleview.MyItemTouchCallback;
import lanou.amg1.tools.recycleview.OnRecyclerItemClickListener;
import lanou.amg1.tools.requset.GsonRequest;
import lanou.amg1.tools.requset.VolleySingleton;

public class RecommendMoreAty extends BaseActivity implements MyItemTouchCallback.OnDragListener {


    public static List<MoreAtyBean.ResultBean.MetalistBean.ListBean> bean = new ArrayList<>();
    private RecyclerView activity_recommend_more_recyclerview;
    private ImageView activity_recommend_more_imageview;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void initData() {

        MySQLite mySQLite = new MySQLite(this, "search.db", null, 1);
        sqLiteDatabase = mySQLite.getReadableDatabase();
        GsonRequest<MoreAtyBean> gsonRequest = new GsonRequest<MoreAtyBean>(URLAll.MORE_URL, MoreAtyBean.class, new Response.Listener<MoreAtyBean>() {

            private MoreRcvAdp moreRecyclerviewAdapter;

            @Override
            public void onResponse(final MoreAtyBean bean) {

                RecommendMoreAty.bean = new ArrayList<>();
                Cursor cursor = sqLiteDatabase.query("more", null, null, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {

                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String url = cursor.getString(cursor.getColumnIndex("url"));

                        MoreAtyBean.ResultBean.MetalistBean.ListBean m = new MoreAtyBean.ResultBean.MetalistBean.ListBean();
                        m.setName(name);
                        m.setIconurl(url);

                        RecommendMoreAty.bean.add(m);


                    }

                }


                if (RecommendMoreAty.bean.size() == 0) {

                    Log.d("RecommendMoreActivity", "ni");

                    for (int i = 0; i < bean.getResult().getMetalist().get(0).getList().size(); i++) {

                        if(bean.getResult().getMetalist().get(0).getList().get(i).getName().equals("游记")){
                            RecommendMoreAty.bean.add(0,bean.getResult().getMetalist().get(0).getList().get(i));
                        }else {
                            RecommendMoreAty.bean.add(bean.getResult().getMetalist().get(0).getList().get(i));
                        }
                    }

                }

                moreRecyclerviewAdapter = new MoreRcvAdp(RecommendMoreAty.this);

                moreRecyclerviewAdapter.setMoreActivityBean(RecommendMoreAty.bean);
                activity_recommend_more_recyclerview.setLayoutManager(new GridLayoutManager(RecommendMoreAty.this, 3));
                activity_recommend_more_recyclerview.setAdapter(moreRecyclerviewAdapter);

                final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchCallback(moreRecyclerviewAdapter).setOnDragListener(RecommendMoreAty.this));

                itemTouchHelper.attachToRecyclerView(activity_recommend_more_recyclerview);

                activity_recommend_more_recyclerview.addOnItemTouchListener(new OnRecyclerItemClickListener(activity_recommend_more_recyclerview) {


                    @Override
                    public void onLongClick(RecyclerView.ViewHolder vh) {

//                        可以设置第几个不能拖动
                        if (vh.getLayoutPosition() != 0) {
                            itemTouchHelper.startDrag(vh);
                        }


                    }


                    @Override
                    public void onItemClick(RecyclerView.ViewHolder vh) {

                        Log.d("RecommendMoreActivity", "vh.getLayoutPosition():" + vh.getLayoutPosition());
                        Log.d("RecommendMoreActivity", RecommendMoreAty.bean.get(vh.getLayoutPosition()).getName());

//                        SendEvent sendEvent = new SendEvent();

//                        sendEvent.setContent(RecommendMoreAty.bean.get(vh.getLayoutPosition()).getName());
//                        sendEvent.setChoice(14);
//                        sendEvent.setAnInt(vh.getLayoutPosition());
//                        Log.d("RecommendMoreAty", RecommendMoreAty.bean.get(vh.getLayoutPosition()).getName());
//                        EventBus.getDefault().post(sendEvent);
                        Intent intent = new Intent();
                        intent.putExtra("position",vh.getLayoutPosition());
                        setResult(101,intent);
                        finish();

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
    protected int setContentView() {
        return R.layout.activity_recommend_more;
    }

    @Override
    protected void initViews() {

        activity_recommend_more_recyclerview = (RecyclerView) findViewById(R.id.activity_recommend_more_recyclerview);
        activity_recommend_more_imageview = (ImageView) findViewById(R.id.activity_recommend_more_imageview);


    }

    @Override
    protected void initListeners() {

        activity_recommend_more_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });




    }

    @Override
    public void onFinishDrag() {
        //存入缓存


        sqLiteDatabase.delete("more", null, null);
        for (int i = 0; i < RecommendMoreAty.bean.size(); i++) {

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", RecommendMoreAty.bean.get(i).getName());
            contentValues.put("url", RecommendMoreAty.bean.get(i).getIconurl());
            sqLiteDatabase.insert("more", null, contentValues);

        }

        Log.d("RecommendMoreAty", "这");
        SendEvent sendEvent = new SendEvent();
        sendEvent.setChoice(15);
        EventBus.getDefault().post(sendEvent);





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
