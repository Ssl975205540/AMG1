package lanou.amg1.personalfragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.discoverfragment.horizontallistview.HorizontalListView;
import lanou.amg1.forumfragment.page.onepagefirst.OnPageFirstHotAdapter;
import lanou.amg1.main.SendEvent;
import lanou.amg1.personalfragment.option.OptionActivity;
import lanou.amg1.urlall.URLAll;

/**
 * Created by dllo on 16/9/20.
 */
public class PersonalFragment extends Base_Fragment implements View.OnClickListener {
    private LinearLayout personal_LinearLayout;
    private ListView listView;
    private LinearLayout activity_main_LinearLayout;
    private FrameLayout frameLayout_MainActivity;
    private LinearLayout lllll;
    private HorizontalListView personal_Fragment_horiListView;
    private ImageView personal_Fragment_UserpicImagView;
    private LinearLayout activity_main_LinearLayout_All;
    private ImageView personal_Moon_ImageView;
    private Boolean V = true;
    private ImageView personal__Sunlight_ImageView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private Boolean visible;
    private RelativeLayout personal_Sina_relayout, personal_QQ_relayout, personal_wechat_relayout,personal_option_relayout;
    private PlatformActionListener paListener;


    @Override
    protected void networkRequest() {


    }

    @Override
    protected int setLayout() {
        return R.layout.personal_frament;
    }


    @Override
    protected void control() {

        EventBus.getDefault().register(this);
        ShareSDK.initSDK(context);
        personal_Moon_ImageView = findById(R.id.personal_Moon_ImageView);
        personal__Sunlight_ImageView = findById(R.id.personal__Sunlight_ImageView);
        personal_QQ_relayout = findById(R.id.personal_QQ_relayout);
        personal_wechat_relayout = findById(R.id.personal_wechat_relayout);
        personal_Sina_relayout = findById(R.id.personal_Sina_relayout);

        personal_option_relayout = findById(R.id.personal_option_relayout);
        personal_option_relayout.setOnClickListener(this);
        personal_wechat_relayout.setOnClickListener(this);
        personal_QQ_relayout.setOnClickListener(this);
        personal_Sina_relayout.setOnClickListener(this);

        personal_Moon_ImageView.setOnClickListener(this);
        personal__Sunlight_ImageView.setOnClickListener(this);
        personal_Fragment_UserpicImagView = findById(R.id.personal_Fragment_UserpicImagView);
        Picasso.with(context).load(R.mipmap.ahlib_userpic_default).transform(new OnPageFirstHotAdapter.CircleTransform()).into(personal_Fragment_UserpicImagView);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
        sharedPreferences = getContext().getSharedPreferences("PersonalFragment", Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
        edit.commit();

        visible = sharedPreferences.getBoolean("VISIBLE", true);
        if (visible) {

            personal__Sunlight_ImageView.setVisibility(View.INVISIBLE);
            personal_Moon_ImageView.setVisibility(View.VISIBLE);


        } else {

            personal_Moon_ImageView.setVisibility(View.INVISIBLE);
            personal__Sunlight_ImageView.setVisibility(View.VISIBLE);
        }


    }


    @Subscribe
    public void setSendEvent(SendEvent sendEvent){

        if(1 == sendEvent.getChoice()){

            personal__Sunlight_ImageView.setVisibility(View.VISIBLE);
            personal_Moon_ImageView.setVisibility(View.GONE);

        }

        if(2 == sendEvent.getChoice()){
            personal_Moon_ImageView.setVisibility(View.VISIBLE);
            personal__Sunlight_ImageView.setVisibility(View.GONE);


        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.personal_Moon_ImageView:


                personal_Moon_ImageView.setVisibility(View.GONE);
                personal__Sunlight_ImageView.setVisibility(View.VISIBLE);
                SendEvent sendEvent = new SendEvent();
                sendEvent.setChoice(URLAll.ONE);
                EventBus.getDefault().post(sendEvent);
                edit.putBoolean("VISIBLE", false);

                edit.commit();


                break;


            case R.id.personal__Sunlight_ImageView:

                personal__Sunlight_ImageView.setVisibility(View.GONE);
                personal_Moon_ImageView.setVisibility(View.VISIBLE);

                SendEvent sendEvent1 = new SendEvent();
                sendEvent1.setChoice(URLAll.TWO);
                EventBus.getDefault().post(sendEvent1);
                edit.putBoolean("VISIBLE", true);
                edit.commit();


                break;


            case R.id.personal_wechat_relayout:


                break;
            case R.id.personal_QQ_relayout:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);

                qq.setPlatformActionListener(paListener);
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息


                break;
            case R.id.personal_Sina_relayout:

                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.setPlatformActionListener(paListener);
//authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                weibo.showUser(null);//授权并获取用户信息

                break;



            case R.id.personal_option_relayout:

                Intent intent = new Intent(getActivity(), OptionActivity.class);
                startActivity(intent);

                break;

        }


    }
}
