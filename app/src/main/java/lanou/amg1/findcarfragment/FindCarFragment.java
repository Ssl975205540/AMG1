package lanou.amg1.findcarfragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.findcarfragment.fincarpage.findcarone.FindCar_OnePgae;
import lanou.amg1.findcarfragment.fincarpage.findcartwo.FindCar_TwoPage;


/**
 * Created by dllo on 16/9/20.
 */
public class FindCarFragment extends Base_Fragment {
    private ViewPager findCarFragment_ViewPager;
    private TabLayout findCarFragment_TabLayout;
    private ImageView findCarFragment_Js_ImageView;
    private ImageView findCarFragment_Vs_ImageView;
    private TextView findCarFragment_Bj_TextView;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void networkRequest() {


        FindCarFragmentAdapter findCarFragmentAdapter = new FindCarFragmentAdapter(getChildFragmentManager());

        ArrayList<Fragment> arrayList = new ArrayList<>();

        arrayList.add(new FindCar_OnePgae());

        arrayList.add(new FindCar_TwoPage());


        findCarFragmentAdapter.setArrayList(arrayList);

        findCarFragment_ViewPager.setAdapter(findCarFragmentAdapter);

        findCarFragment_TabLayout.setupWithViewPager(findCarFragment_ViewPager);

        findCarFragment_ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                switch (findCarFragment_ViewPager.getCurrentItem()) {

                    case 0:

                        findCarFragment_Js_ImageView.setVisibility(View.VISIBLE);
                        findCarFragment_Vs_ImageView.setVisibility(View.VISIBLE);
                        findCarFragment_Bj_TextView.setVisibility(View.INVISIBLE);


                        break;

                    case 1:
                        findCarFragment_Js_ImageView.setVisibility(View.INVISIBLE);
                        findCarFragment_Vs_ImageView.setVisibility(View.INVISIBLE);
                        findCarFragment_Bj_TextView.setVisibility(View.VISIBLE);


                        break;


                }


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
    @Override
    protected View setLayout(LayoutInflater inflater) {



        return inflater.inflate(R.layout.findcar_fragment,null);
    }

    @Override
    protected void control() {

        findCarFragment_ViewPager = findById(R.id.findCarFragment_ViewPager);
        findCarFragment_TabLayout = findById(R.id.findCarFragment_TabLayout);


        findCarFragment_Js_ImageView = findById(R.id.findCarFragment_Js_ImageView);
        findCarFragment_Vs_ImageView = findById(R.id.findCarFragment_Vs_ImageView);
        findCarFragment_Bj_TextView = findById(R.id.findCarFragment_Bj_TextView);

    }
}
