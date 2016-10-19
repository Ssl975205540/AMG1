package lanou.amg1.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/9/19.
 */
public abstract class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initJudge();
        setContentView(setContentView());
        initViews();
        initListeners();
        initData();


    }


    protected abstract void initData();
    protected abstract int setContentView();
    protected abstract void initViews();
    protected abstract void initListeners();

    protected void initJudge(){

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
