package lanou.amg1.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import lanou.amg1.R;
import lanou.amg1.base.BaseActivity;
import lanou.amg1.discoverfragment.discovermain.DiscoverFragment;
import lanou.amg1.findcar.FindCarFragment;
import lanou.amg1.forum.ForumFragment;
import lanou.amg1.personal.PersonalFragment;
import lanou.amg1.recommend.RecommendFragment;
import lanou.amg1.tools.SendEvent;
import lanou.amg1.tools.URLAll;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreference_edit;
    private FrameLayout frameLayout_MainActivity;
    private RadioButton  nav_icon_findcar, nav_icon_forum, nav_icon_my, nav_icon_sale;
    private RadioButton nav_icon_article;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private RecommendFragment recommendFragment;
    private ForumFragment forumFragment;
    private DiscoverFragment discoverFragment;
    private PersonalFragment personalFragment;
    private FindCarFragment findCarFragment;

    private LinearLayout activity_main_LinearLayout_All;
    private SharedPreferences sharedPreferences1;
    private Boolean visible;
    private LinearLayout guidepageone_linearlayout;
    private Button btn_GuidePageActivity_Fragment_One;
    private LinearLayout guidepagetwo_linearlayout;
    private Button btn_GuidePageActivity;
    private RadioGroup activity_main_radiogroup;
    private boolean back = false;


    @Override
    protected void initData() {
        nav_icon_article.setChecked(true);
        sharedPreferences = getSharedPreferences(URLAll.SHAREDPREFERENCES_MAIN, MODE_PRIVATE);
        sharedPreference_edit = sharedPreferences.edit();
        sharedPreference_edit.commit();
        Boolean first = sharedPreferences.getBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN, false);
        if (first == false) {

            guidepageone_linearlayout.setVisibility(View.VISIBLE);

        }


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        recommendFragment = new RecommendFragment();
        fragmentTransaction.add(R.id.frameLayout_MainActivity, recommendFragment);
        fragmentTransaction.commit();
        fragment = recommendFragment;

        sharedPreferences1 = this.getSharedPreferences("PersonalFragment", Context.MODE_PRIVATE);

        visible = sharedPreferences1.getBoolean("VISIBLE", true);
        if (visible) {

            activity_main_LinearLayout_All.setVisibility(View.INVISIBLE);

        } else {
            activity_main_LinearLayout_All.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

        EventBus.getDefault().register(this);
        frameLayout_MainActivity = (FrameLayout) findViewById(R.id.frameLayout_MainActivity);
        guidepageone_linearlayout = (LinearLayout) findViewById(R.id.guidepageone_linearlayout);
        guidepagetwo_linearlayout = (LinearLayout) findViewById(R.id.guidepagetwo_linearlayout);
        btn_GuidePageActivity_Fragment_One = (Button) findViewById(R.id.btn_GuidePageActivity_Fragment_One);
        btn_GuidePageActivity = (Button) findViewById(R.id.btn_GuidePageActivity);
        btn_GuidePageActivity_Fragment_One.setOnClickListener(this);
        btn_GuidePageActivity.setOnClickListener(this);
        activity_main_LinearLayout_All = (LinearLayout) findViewById(R.id.activity_main_LinearLayout_All);
        nav_icon_findcar = (RadioButton) findViewById(R.id.nav_icon_findcar);
        nav_icon_forum = (RadioButton) findViewById(R.id.nav_icon_forum);
        nav_icon_my = (RadioButton) findViewById(R.id.nav_icon_my);
        nav_icon_sale = (RadioButton) findViewById(R.id.nav_icon_sale);
        nav_icon_article = (RadioButton) findViewById(R.id.nav_icon_article);




    }


    @Override
    protected void initListeners() {

        nav_icon_findcar.setOnClickListener(this);
        nav_icon_forum.setOnClickListener(this);
        nav_icon_my.setOnClickListener(this);
        nav_icon_sale.setOnClickListener(this);
        nav_icon_article.setOnClickListener(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.nav_icon_article:
//                interchange(URLAll.ONE);

                if (recommendFragment == null) {
                    recommendFragment = new RecommendFragment();
                }
                switchContent(recommendFragment);
                break;
            case R.id.nav_icon_forum:
//                interchange(URLAll.TWO);

                if (forumFragment == null) {
                    forumFragment = new ForumFragment();
                }
                switchContent(forumFragment);
                break;

            case R.id.nav_icon_findcar:
//                interchange(URLAll.THREE);

                if (findCarFragment == null) {
                    findCarFragment = new FindCarFragment();
                }
                switchContent(findCarFragment);


                break;

            case R.id.nav_icon_sale:
//                interchange(URLAll.FOUR);


                if (discoverFragment == null) {
                    discoverFragment = new DiscoverFragment();
                }
                switchContent(discoverFragment);

                break;

            case R.id.nav_icon_my:
//                interchange(URLAll.Five);
                if (personalFragment == null) {
                    personalFragment = new PersonalFragment();
                }
                switchContent(personalFragment);


                break;

            case R.id.btn_GuidePageActivity_Fragment_One:

                guidepageone_linearlayout.setVisibility(View.GONE);

                guidepagetwo_linearlayout.setVisibility(View.VISIBLE);

                break;
            case R.id.btn_GuidePageActivity:

                guidepagetwo_linearlayout.setVisibility(View.GONE);


                sharedPreference_edit.putBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN, true);
                sharedPreference_edit.commit();

                break;

        }


    }




    @Subscribe
    public void sendText(SendEvent event) {

        switch (event.getChoice()) {
            case URLAll.ONE:
                activity_main_LinearLayout_All.setVisibility(View.VISIBLE);

                break;

            case URLAll.TWO:
                activity_main_LinearLayout_All.setVisibility(View.INVISIBLE);

                break;

            case URLAll.THREE:

                finish();
                break;


        }
    }


    private Fragment fragment;

    public void switchContent(Fragment to) {

        if (fragment != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();

            if (!to.isAdded()) {

                transaction.hide(fragment).add(R.id.frameLayout_MainActivity, to).commit(); // “˛≤ÿµ±«∞µƒfragment£¨addœ¬“ª∏ˆµΩActivity÷–
            } else {

                transaction.hide(fragment).show(to).commit();
            }
            fragment = to;
        }

    }




    @Override
    public void onBackPressed() {

        if(back == false){

            Toast.makeText(this, "再按一次退出汽车之家", Toast.LENGTH_SHORT).show();
            back = true;

        }else {
            super.onBackPressed();
        }




    }



}

