package lanou.amg1.personalfragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.discoverfragment.horizontallistview.HorizontalListView;
import lanou.amg1.forumfragment.page.onepagefirst.OnPageFirstHotAdapter;
import lanou.amg1.main.SendEvent;
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

    @Override
    protected void networkRequest() {



    }

    @Override
    protected View setLayout(LayoutInflater inflater) {



        return inflater.inflate(R.layout.personal_frament,null);

    }

    @Override
    protected void control() {


        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）



        personal_Moon_ImageView = findById(R.id.personal_Moon_ImageView);
        personal__Sunlight_ImageView = findById(R.id.personal__Sunlight_ImageView);

        personal_Moon_ImageView.setOnClickListener(this);
        personal__Sunlight_ImageView.setOnClickListener(this);
        personal_Fragment_UserpicImagView = findById(R.id.personal_Fragment_UserpicImagView);
        Picasso.with(context).load(R.mipmap.ahlib_userpic_default).transform(new OnPageFirstHotAdapter.CircleTransform()).into(personal_Fragment_UserpicImagView);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main,null);



    }


    @Override
    public void onClick(View v) {


        switch (v.getId()){


            case R.id.personal_Moon_ImageView:

                if(personal_Moon_ImageView.getVisibility() == View.VISIBLE){

                    personal_Moon_ImageView.setVisibility(View.INVISIBLE);
                    personal__Sunlight_ImageView.setVisibility(View.VISIBLE);
                    SendEvent sendEvent = new SendEvent();
                    sendEvent.setChoice(URLAll.ONE);
                    EventBus.getDefault().post(sendEvent);
                }





                break;


            case R.id.personal__Sunlight_ImageView:


                if(personal__Sunlight_ImageView.getVisibility() == View.VISIBLE){
                    personal__Sunlight_ImageView.setVisibility(View.INVISIBLE);
                    personal_Moon_ImageView.setVisibility(View.VISIBLE);

                    SendEvent sendEvent = new SendEvent();
                    sendEvent.setChoice(URLAll.TWO);
                    EventBus.getDefault().post(sendEvent);

                }
                break;



        }




    }
}
