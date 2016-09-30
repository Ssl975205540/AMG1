package lanou.amg1.guidepage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import lanou.amg1.R;
import lanou.amg1.main.SendEvent;
import lanou.amg1.urlall.URLAll;


public class GuidePageActivity extends FragmentActivity {

    private Button btn_GuidePageActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        EventBus.getDefault().register(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_GuidePageActivity,new GuidePager_Fagment_One());
        fragmentTransaction.commit();

    }

    @Subscribe
    public void setSendEvent(SendEvent sendEvent){

        if(sendEvent.getChoice() == URLAll.THREE){

            finish();


        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
