package lanou.amg1.findcar;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.base.BaseFragment;
import lanou.amg1.findcar.fincarpage.findcarone.FindCarOnePgae;
import lanou.amg1.findcar.fincarpage.findcartwo.FindCarTwoPage;
import lanou.amg1.search.SearchAty;


/**
 * Created by dllo on 16/9/20.
 */
public class FindCarFragment extends BaseFragment {
    private ViewPager findCarFragment_ViewPager;
    private TabLayout findCarFragment_TabLayout;
    private ImageView findCarFragment_Vs_ImageView;
    private TextView findCarFragment_Bj_TextView;
    private boolean mHasLoadedOnce = false;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void initData() {


        FindCarFgtAdp findCarFgtAdp = new FindCarFgtAdp(getChildFragmentManager());

        ArrayList<Fragment> arrayList = new ArrayList<>();

        arrayList.add(new FindCarOnePgae());

        arrayList.add(new FindCarTwoPage());


        findCarFgtAdp.setArrayList(arrayList);

        findCarFragment_ViewPager.setAdapter(findCarFgtAdp);

        findCarFragment_ViewPager.setOffscreenPageLimit(2);

        findCarFragment_TabLayout.setupWithViewPager(findCarFragment_ViewPager);

        findCarFragment_ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                switch (findCarFragment_ViewPager.getCurrentItem()) {

                    case 0:

                        findCarFragment_Vs_ImageView.setVisibility(View.VISIBLE);
                        findCarFragment_Bj_TextView.setVisibility(View.INVISIBLE);


                        break;

                    case 1:
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
    protected int setContentView() {
        return R.layout.fragment_findcar;

    }

    @Override
    protected void initViews() {

        findCarFragment_ViewPager = findById(R.id.findCarFragment_ViewPager);
        findCarFragment_TabLayout = findById(R.id.findCarFragment_TabLayout);
        findCarFragment_Vs_ImageView = findById(R.id.findCarFragment_Vs_ImageView);
        findCarFragment_Bj_TextView = findById(R.id.findCarFragment_Bj_TextView);




    }

    @Override
    protected void initListeners() {
        findCarFragment_Vs_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), SearchAty.class);

                intent.putExtra("search","搜索车系");
                startActivity(intent);



            }
        });


    }


}
