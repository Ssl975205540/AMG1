package lanou.amg1.personal.option;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import lanou.amg1.R;
import lanou.amg1.base.BaseFragment;

/**
 * Created by dllo on 16/10/14.
 */
public class PushFragment extends BaseFragment implements View.OnClickListener {
    private PopupWindow popupWindow;
    int np1Price = 0, np2Price = 0;
    private LinearLayout pushfragment_layout,pushfragment_layout1;
    private NumberPicker np1;
    private View popupWindow_view;
    private NumberPicker np2;
    private Button activity_popup_btn1;
    private Button activity_popup_btn2;
    private TextView pushfragment_layout_text;
    private String stringNp1Price;
    private String stringNp2Price;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private StringBuffer daysSB;
    private Set<Integer> days;
    private Switch pushfragment_switch1, pushfragment_switch2;


    @Override
    protected void initData() {
        sharedPreferences = context.getSharedPreferences("PersonalFragment", context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
        edit.commit();
        if(sharedPreferences.getBoolean("pushfragment_switch1", false)){

            pushfragment_layout1.setVisibility(View.VISIBLE);
        }
        pushfragment_layout_text.setText(sharedPreferences.getString("stringNpPrice", "每日:00:00 - 00:00"));
        pushfragment_switch1.setChecked(sharedPreferences.getBoolean("pushfragment_switch1", false));

        pushfragment_switch2.setChecked(sharedPreferences.getBoolean("pushfragment_switch2",false));


        String[] mDisplayNames = new String[24];

        for (int i = 0; i < mDisplayNames.length; i++) {
            if (i > 9) {
                mDisplayNames[i] = i + ":" + "00";

            } else {

                mDisplayNames[i] = "0" + i + ":" + "00";
            }

        }


        //设置np1的最小值和最大值

        np1.setMinValue(0);
        np1.setMaxValue(mDisplayNames.length - 1);
        np1.setDisplayedValues(mDisplayNames);
        //设置np1的当前值
        np1.setValue(np1Price);


        np2.setMinValue(0);
        np2.setMaxValue(mDisplayNames.length - 1);
        np2.setDisplayedValues(mDisplayNames);
        //设置np1的当前值
        np2.setValue(np2Price);

    }

    @Override
    protected int setContentView() {
        return R.layout.pushfragment;
    }

    @Override
    protected void initViews() {

        pushfragment_layout_text = findById(R.id.pushfragment_layout_text);
        pushfragment_layout = findById(R.id.pushfragment_layout);
        pushfragment_layout1 = findById(R.id.pushfragment_layout1);
        popupWindow_view = LayoutInflater.from(context).inflate(R.layout.activity_popupwindow, null, false);
        pushfragment_switch1 = findById(R.id.pushfragment_switch1);
        pushfragment_switch2 = findById(R.id.pushfragment_switch1);

        np1 = (NumberPicker) findById(R.id.np1, popupWindow_view);
        np2 = (NumberPicker) findById(R.id.np2, popupWindow_view);
        activity_popup_btn1 = (Button) findById(R.id.activity_popup_btn1, popupWindow_view);
        activity_popup_btn2 = (Button) findById(R.id.activity_popup_btn2, popupWindow_view);






    }

    @Override
    protected void initListeners() {
        pushfragment_layout.setOnClickListener(this);
        activity_popup_btn1.setOnClickListener(this);
        activity_popup_btn2.setOnClickListener(this);
        pushfragment_switch1.setOnClickListener(this);
        pushfragment_switch2.setOnClickListener(this);
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // TODO Auto-generated method stub
                np1Price = newVal;


            }

        });
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // TODO Auto-generated method stub
                Log.d("PushFragment", "newVal:" + newVal);
                Log.d("PushFragment", "oldVal:" + oldVal);

                np2Price = newVal;


            }

        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.pushfragment_layout:


                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                popupWindow = new PopupWindow(popupWindow_view, LinearLayout.LayoutParams.MATCH_PARENT, 400, true);
                // 设置动画效果
//                popupWindow.setAnimationStyle(R.style.AnimationFade);
                // 这里是位置显示方式,在屏幕的左侧
                popupWindow.showAtLocation(pushfragment_layout, Gravity.BOTTOM, 0, 0);
                // 点击其他地方消失
                popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        // TODO Auto-generated method stub
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                        return false;
                    }
                });


                break;


            case R.id.activity_popup_btn1:

                popupWindow.dismiss();

            case R.id.pushfragment_switch1:

                if (pushfragment_switch1.isChecked()) {
                    JPushInterface.resumePush(context.getApplicationContext());
                    pushfragment_layout1.setVisibility(View.VISIBLE);

                } else {
                    JPushInterface.stopPush(context.getApplicationContext());
                    pushfragment_layout1.setVisibility(View.GONE);
                }
                edit.putBoolean("pushfragment_switch1", pushfragment_switch1.isChecked());
                edit.commit();
            case R.id.pushfragment_switch2:

                edit.putBoolean("pushfragment_switch2", pushfragment_switch2.isChecked());
                edit.commit();


                break;
            case R.id.activity_popup_btn2:
                if (sharedPreferences.getBoolean("pushfragment_switch1", false)) {


                    setPushTime();

                    Log.d("PushFragment", "np1Price:" + np1Price);
                    Log.d("PushFragment", "np2Price:" + np2Price);


                    if (np1Price < 10) {
                        stringNp1Price = "0" + np1Price;
                    } else {
                        stringNp1Price = "" + np1Price;

                    }
                    if (np2Price < 10) {
                        stringNp2Price = "0" + np2Price;
                    } else {
                        stringNp2Price = "" + np2Price;

                    }

                    if (np1Price > np2Price) {
                        pushfragment_layout_text.setText("每日:" + stringNp2Price + ":00 - " + stringNp1Price + ":00");
                        JPushInterface.setPushTime(getContext(), days, np2Price, np1Price - 1);

                    }

                    if (np1Price < np2Price) {
                        pushfragment_layout_text.setText("每日:" + stringNp1Price + ":00 - " + stringNp2Price + ":00");
                        JPushInterface.setPushTime(getContext(), days, np1Price, np2Price - 1);

                    }
                    if (np1Price == np2Price) {
                        if (0 == np1Price) {
                            pushfragment_layout_text.setText("每日:" + stringNp1Price + ":00 - " + 24 + ":00");
                            JPushInterface.setPushTime(context.getApplicationContext(), days, 0, 23);


                        } else {
                            pushfragment_layout_text.setText("每日:" + stringNp1Price + ":00 - " + stringNp2Price + ":00");
                            JPushInterface.setPushTime(context.getApplicationContext(), days, 0, 0);

                        }

                    }




                if (stringNp1Price.equals("00")) {

                    edit.putString("stringNpPrice", "每日:" + stringNp2Price + ":00 - " + 24 + ":00");

                } else {
                    edit.putString("stringNpPrice", "每日:" + stringNp2Price + ":00 - " + stringNp1Price + ":00");

                }
                edit.commit();
                }
                popupWindow.dismiss();


                break;
        }


    }

    private void setPushTime() {


        StringBuffer daysSB = new StringBuffer();
        days = new HashSet<Integer>();


        for (int i = 0; i < 7; i++) {
            days.add(i);
            daysSB.append("0" + i + ",");
        }


    }




}