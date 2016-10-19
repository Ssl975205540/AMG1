package lanou.amg1.welcomepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import lanou.amg1.R;
import lanou.amg1.advert.AdvertAty;
import lanou.amg1.guidepoint.CircleIndicator;
import lanou.amg1.tools.SendEvent;
import lanou.amg1.tools.URLAll;

public class WelComeAty extends FragmentActivity {

    private ViewPager viewPager_WelComePage;
    private WelComeAdp _welComeAdp;
    private SharedPreferences.Editor savedInstanceState_edit;
    private Button btn_fragment;
    private CircleIndicator indicator;
    private SendEvent sendEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SharedPreferences sharedPreferences = getSharedPreferences(URLAll.SHAREDPREFERENCES_WELCOMEPAGE,MODE_PRIVATE);
        savedInstanceState_edit = sharedPreferences.edit();
        savedInstanceState_edit.commit();

        Boolean first = sharedPreferences.getBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN,false);


        if(first){
            Intent intent = new Intent(WelComeAty.this, AdvertAty.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_welcome);

        viewPager_WelComePage = (ViewPager) findViewById(R.id.viewPager_WelComePage);

        indicator = (CircleIndicator)findViewById(R.id.indicator);

        _welComeAdp = new WelComeAdp();

        ArrayList<View> arrayList = new ArrayList<>();
        View view0 = getLayoutInflater().inflate(R.layout.fragment_one,null);
        View view1 = getLayoutInflater().inflate(R.layout.fragment_two,null);
        View view2 = getLayoutInflater().inflate(R.layout.fragment_three,null);
        View view3 = getLayoutInflater().inflate(R.layout.fragment_four, null);
        arrayList.add(view0);
        arrayList.add(view1);
        arrayList.add(view2);
        arrayList.add(view3);

        btn_fragment =(Button)view3.findViewById(R.id.btn_fragment);

        btn_fragment.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelComeAty.this,AdvertAty.class);
                startActivity(intent);
                SharedPreferences sharedPreferences = getSharedPreferences(URLAll.SHAREDPREFERENCES_WELCOMEPAGE,MODE_PRIVATE);
                SharedPreferences.Editor savedInstanceState_edit = sharedPreferences.edit();
                savedInstanceState_edit.putBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN,true);
                savedInstanceState_edit.commit();

                sendEvent = new SendEvent();
                sendEvent.setContent(URLAll.WELCOMEPAGE_FINSH);
                //关闭当前的Activity
                EventBus.getDefault().post(sendEvent);


            }
        });
        _welComeAdp.setArrayList(arrayList);

        viewPager_WelComePage.setAdapter(_welComeAdp);
        indicator.setViewPager(viewPager_WelComePage);
        viewPager_WelComePage.setCurrentItem(URLAll.ZERO);
        viewPager_WelComePage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @Subscribe

    public void setSendEvent(SendEvent sendEvent){

        if(sendEvent.getContent().equals(URLAll.WELCOMEPAGE_FINSH)){
            finish();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
