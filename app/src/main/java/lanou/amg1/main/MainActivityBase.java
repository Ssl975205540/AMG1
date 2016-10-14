package lanou.amg1.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/9/19.
 */
public abstract class MainActivityBase extends AppCompatActivity {

    private SharedPreferences.Editor savedInstanceState_edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(setLayout());
        control();
        listenIn();



    }



    protected abstract int setLayout();
    protected abstract void control();
    protected abstract void listenIn();



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
