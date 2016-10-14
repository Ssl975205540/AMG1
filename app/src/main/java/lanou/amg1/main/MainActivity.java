package lanou.amg1.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import lanou.amg1.R;
import lanou.amg1.discoverfragment.discovermain.DiscoverFragment;
import lanou.amg1.findcarfragment.FindCarFragment;
import lanou.amg1.forumfragment.ForumFragment;
import lanou.amg1.personalfragment.PersonalFragment;
import lanou.amg1.recommendfragment.RecommendFragment;
import lanou.amg1.urlall.URLAll;

public class MainActivity extends MainActivityBase implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreference_edit;
    private FrameLayout frameLayout_MainActivity;
    private ImageView nav_icon_article, nav_icon_findcar, nav_icon_forum, nav_icon_my, nav_icon_sale;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    private RecommendFragment recommendFragment;
    private PersonalFragment personalFragment;
    private DiscoverFragment discoverFragment;
    private FindCarFragment findCarFragment;
    private ForumFragment forumFragment;
    private LinearLayout activity_main_LinearLayout_All;
    private SharedPreferences sharedPreferences1;
    private Boolean visible;
    private LinearLayout guidepageone_linearlayout;
    private Button btn_GuidePageActivity_Fragment_One;
    private LinearLayout guidepagetwo_linearlayout;
    private Button btn_GuidePageActivity;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void control() {


        EventBus.getDefault().register(this);
        frameLayout_MainActivity = (FrameLayout) findViewById(R.id.frameLayout_MainActivity);
        guidepageone_linearlayout = (LinearLayout) findViewById(R.id.guidepageone_linearlayout);
        guidepagetwo_linearlayout = (LinearLayout) findViewById(R.id.guidepagetwo_linearlayout);
        btn_GuidePageActivity_Fragment_One = (Button) findViewById(R.id.btn_GuidePageActivity_Fragment_One);
        btn_GuidePageActivity = (Button) findViewById(R.id.btn_GuidePageActivity);
        btn_GuidePageActivity_Fragment_One.setOnClickListener(this);
        btn_GuidePageActivity.setOnClickListener(this);
        activity_main_LinearLayout_All = (LinearLayout) findViewById(R.id.activity_main_LinearLayout_All);
        nav_icon_article = (ImageView) findViewById(R.id.nav_icon_article);
        nav_icon_findcar = (ImageView) findViewById(R.id.nav_icon_findcar);
        nav_icon_forum = (ImageView) findViewById(R.id.nav_icon_forum);
        nav_icon_my = (ImageView) findViewById(R.id.nav_icon_my);
        nav_icon_sale = (ImageView) findViewById(R.id.nav_icon_sale);

        sharedPreferences = getSharedPreferences(URLAll.SHAREDPREFERENCES_MAIN, MODE_PRIVATE);
        sharedPreference_edit = sharedPreferences.edit();
        sharedPreference_edit.commit();
        Boolean first = sharedPreferences.getBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN, false);
        if (first == false) {

            guidepageone_linearlayout.setVisibility(View.VISIBLE);
//            Intent intent = new Intent(MainActivity.this, GuidePageActivity.class);
//            startActivity(intent);
//            overridePendingTransition(URLAll.ZERO, URLAll.ZERO);
//            guidepageone_linearlayout.setVisibility(View.VISIBLE);
//            sharedPreference_edit.putBoolean(URLAll.SHAREDPREFERENCES_WELCOMEPAGE_BOOLEAN, true);
//            sharedPreference_edit.commit();
        }


        recommendFragment = new RecommendFragment();
        fragmentTransaction.replace(R.id.frameLayout_MainActivity, recommendFragment);
        fragmentTransaction.commit();
        interchange(URLAll.ONE);
        sharedPreferences1 = this.getSharedPreferences("PersonalFragment", Context.MODE_PRIVATE);

        visible = sharedPreferences1.getBoolean("VISIBLE", true);
        if (visible) {

            activity_main_LinearLayout_All.setVisibility(View.INVISIBLE);

        } else {
            activity_main_LinearLayout_All.setVisibility(View.VISIBLE);

        }

    }


    @Override
    protected void listenIn() {
        nav_icon_article.setOnClickListener(this);
        nav_icon_findcar.setOnClickListener(this);
        nav_icon_forum.setOnClickListener(this);
        nav_icon_my.setOnClickListener(this);
        nav_icon_sale.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        switch (v.getId()) {

            case R.id.nav_icon_article:
                interchange(URLAll.ONE);
                if (recommendFragment == null) {
                    recommendFragment = new RecommendFragment();
                }

                fragmentTransaction.replace(R.id.frameLayout_MainActivity, recommendFragment);

                break;
            case R.id.nav_icon_forum:
                interchange(URLAll.TWO);
                if (forumFragment == null) {
                    forumFragment = new ForumFragment();
                }

                fragmentTransaction.replace(R.id.frameLayout_MainActivity, forumFragment);


                break;

            case R.id.nav_icon_findcar:
                interchange(URLAll.THREE);
                if (findCarFragment == null) {
                    findCarFragment = new FindCarFragment();
                }

                fragmentTransaction.replace(R.id.frameLayout_MainActivity, findCarFragment);


                break;

            case R.id.nav_icon_sale:
                interchange(URLAll.FOUR);
                if (discoverFragment == null) {
                    discoverFragment = new DiscoverFragment();
                }

                fragmentTransaction.replace(R.id.frameLayout_MainActivity, discoverFragment);

                break;

            case R.id.nav_icon_my:
                interchange(URLAll.Five);

                if (personalFragment == null) {
                    personalFragment = new PersonalFragment();
                }
                fragmentTransaction.replace(R.id.frameLayout_MainActivity, personalFragment);

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
        fragmentTransaction.commit();


    }


    private void interchange(int i) {

        switch (i) {

            case URLAll.ONE:

                nav_icon_article.setImageResource(R.mipmap.nav_icon_article_p);
                nav_icon_forum.setImageResource(R.mipmap.nav_icon_forum_f);
                nav_icon_findcar.setImageResource(R.mipmap.nav_icon_findcar_f);
                nav_icon_sale.setImageResource(R.mipmap.nav_icon_sale_f);
                nav_icon_my.setImageResource(R.mipmap.nav_icon_my_f);
                break;

            case URLAll.TWO:

                nav_icon_article.setImageResource(R.mipmap.nav_icon_article_f);
                nav_icon_forum.setImageResource(R.mipmap.nav_icon_forum_p);
                nav_icon_findcar.setImageResource(R.mipmap.nav_icon_findcar_f);
                nav_icon_sale.setImageResource(R.mipmap.nav_icon_sale_f);
                nav_icon_my.setImageResource(R.mipmap.nav_icon_my_f);
                break;
            case URLAll.THREE:
                nav_icon_article.setImageResource(R.mipmap.nav_icon_article_f);
                nav_icon_forum.setImageResource(R.mipmap.nav_icon_forum_f);
                nav_icon_findcar.setImageResource(R.mipmap.nav_icon_findcar_p);
                nav_icon_sale.setImageResource(R.mipmap.nav_icon_sale_f);
                nav_icon_my.setImageResource(R.mipmap.nav_icon_my_f);
                break;
            case URLAll.FOUR:
                nav_icon_article.setImageResource(R.mipmap.nav_icon_article_f);
                nav_icon_forum.setImageResource(R.mipmap.nav_icon_forum_f);
                nav_icon_findcar.setImageResource(R.mipmap.nav_icon_findcar_f);
                nav_icon_sale.setImageResource(R.mipmap.nav_icon_sale_p);
                nav_icon_my.setImageResource(R.mipmap.nav_icon_my_f);
                break;
            case URLAll.Five:
                nav_icon_article.setImageResource(R.mipmap.nav_icon_article_f);
                nav_icon_forum.setImageResource(R.mipmap.nav_icon_forum_f);
                nav_icon_findcar.setImageResource(R.mipmap.nav_icon_findcar_f);
                nav_icon_sale.setImageResource(R.mipmap.nav_icon_sale_f);
                nav_icon_my.setImageResource(R.mipmap.nav_icon_my_p);
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
}

