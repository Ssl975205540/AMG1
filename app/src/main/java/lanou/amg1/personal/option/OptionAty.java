package lanou.amg1.personal.option;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import lanou.amg1.R;
import lanou.amg1.tools.clean.DataCleanManager;
import lanou.amg1.base.BaseActivity;
import lanou.amg1.tools.SendEvent;
import lanou.amg1.tools.URLAll;

public class OptionAty extends BaseActivity implements View.OnClickListener {


    private ImageView activity_option_imagview;
    private Switch activity_option_Switch1,activity_option_Switch2,activity_option_Switch3,activity_option_Switch4;
    private LinearLayout activity_option_layoutall,activity_option_layout1,activity_option_layout2,activity_option_layout3,activity_option_layout4,activity_option_layout5;
    private RadioButton activity_option_radiobtn1,activity_option_radiobtn2,activity_option_radiobtn3;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private TextView activity_option_layout2_text;
    private FrameLayout activity_option_fralayout;
    private TextView activity_option_set_text;
    private RelativeLayout activity_option_relayout;

    @Override
    protected void initData() {

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_option;
    }

    @Override
    protected void initViews() {



        activity_option_imagview = (ImageView) findViewById(R.id.activity_option_imagview);
        activity_option_Switch1 = (Switch) findViewById(R.id.activity_option_Switch1);
        activity_option_Switch2 = (Switch) findViewById(R.id.activity_option_Switch2);
        activity_option_Switch3 = (Switch) findViewById(R.id.activity_option_Switch3);

        activity_option_layout2_text = (TextView) findViewById(R.id.activity_option_layout2_text);

        activity_option_layoutall = (LinearLayout) findViewById(R.id.activity_option_layoutall);

        activity_option_fralayout = (FrameLayout) findViewById(R.id.activity_option_fralayout);

        activity_option_Switch4 = (Switch) findViewById(R.id.activity_option_Switch4);
        activity_option_Switch3 = (Switch) findViewById(R.id.activity_option_Switch3);
        activity_option_layout1 = (LinearLayout) findViewById(R.id.activity_option_layout1);
        activity_option_layout2 = (LinearLayout) findViewById(R.id.activity_option_layout2);
        activity_option_layout3 = (LinearLayout) findViewById(R.id.activity_option_layout3);
        activity_option_layout4 = (LinearLayout) findViewById(R.id.activity_option_layout4);
        activity_option_layout5 = (LinearLayout) findViewById(R.id.activity_option_layout5);
        activity_option_set_text = (TextView) findViewById(R.id.activity_option_set_text);
        activity_option_relayout = (RelativeLayout) findViewById(R.id.activity_option_relayout);

        activity_option_radiobtn1 = (RadioButton) findViewById(R.id.activity_option_radiobtn1);
        activity_option_radiobtn2 = (RadioButton) findViewById(R.id.activity_option_radiobtn2);
        activity_option_radiobtn3 = (RadioButton) findViewById(R.id.activity_option_radiobtn3);


    }

    @Override
    protected void initListeners() {

        activity_option_imagview.setOnClickListener(this);
        activity_option_Switch1.setOnCheckedChangeListener(switch1);
        activity_option_Switch2.setOnCheckedChangeListener(switch2);
        activity_option_Switch3.setOnCheckedChangeListener(switch3);
        activity_option_Switch4.setOnCheckedChangeListener(switch4);
        activity_option_layout1.setOnClickListener(this);
        activity_option_layout2.setOnClickListener(this);
        activity_option_layout3.setOnClickListener(this);
        activity_option_layout4.setOnClickListener(this);
        activity_option_layout5.setOnClickListener(this);
        activity_option_radiobtn1.setOnClickListener(this);
        activity_option_radiobtn2.setOnClickListener(this);
        activity_option_radiobtn3.setOnClickListener(this);
        String file = null;
        try {
            file = DataCleanManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        activity_option_layout2_text.setText(file);
        sharedPreferences = getSharedPreferences("PersonalFragment",MODE_PRIVATE);
        edit = sharedPreferences.edit();
        edit.commit();
        if(!sharedPreferences.getBoolean("VISIBLE",true)){

            activity_option_layoutall.setVisibility(View.VISIBLE);

        }
        activity_option_Switch1.setChecked(sharedPreferences.getBoolean("switch1",false));
        activity_option_Switch2.setChecked(sharedPreferences.getBoolean("switch2",false));
        activity_option_Switch3.setChecked(sharedPreferences.getBoolean("switch3",false));
        activity_option_Switch4.setChecked(!sharedPreferences.getBoolean("VISIBLE",false));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.activity_option_imagview:
                finish();
            break;
            case R.id.activity_option_layout1:

                Log.d("OptionActivity", "aowi");
                activity_option_fralayout.setVisibility(View.VISIBLE);
                activity_option_relayout.setVisibility(View.GONE);

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_option_fralayout,new PushFragment());
                fragmentTransaction.commit();

                break;
            case R.id.activity_option_layout2:
                DataCleanManager.clearAllCache(this);
                String file = null;
                try {
                    file = DataCleanManager.getTotalCacheSize(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                activity_option_layout2_text.setText(file);
                break;
            case R.id.activity_option_layout3:
                break;
            case R.id.activity_option_layout4:
                break;
            case R.id.activity_option_layout5:
                break;
            case R.id.activity_option_radiobtn1:
                break;
            case R.id.activity_option_radiobtn2:
                break;
            case R.id.activity_option_radiobtn3:
                break;


        }


    }





    public CompoundButton.OnCheckedChangeListener switch1 =  new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // TODO Auto-generated method stub

                edit.putBoolean("switch1",isChecked);
                edit.commit();






        }
    };

    public CompoundButton.OnCheckedChangeListener switch2 =  new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // TODO Auto-generated method stub

                edit.putBoolean("switch2",isChecked);
                edit.commit();

        }
    };


    public CompoundButton.OnCheckedChangeListener switch3 =  new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // TODO Auto-generated method stub

                edit.putBoolean("switch3",isChecked);
                edit.commit();


        }
    };

    public CompoundButton.OnCheckedChangeListener switch4 =  new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // TODO Auto-generated method stub

            edit.putBoolean("VISIBLE", !isChecked);

            edit.commit();
            if(isChecked){
                activity_option_layoutall.setVisibility(View.VISIBLE);
                SendEvent sendEvent = new SendEvent();
                sendEvent.setChoice(URLAll.ONE);
                EventBus.getDefault().post(sendEvent);


            }else {
                activity_option_layoutall.setVisibility(View.GONE);
                SendEvent sendEvent1 = new SendEvent();
                sendEvent1.setChoice(URLAll.TWO);
                EventBus.getDefault().post(sendEvent1);
            }




        }
    };

}
