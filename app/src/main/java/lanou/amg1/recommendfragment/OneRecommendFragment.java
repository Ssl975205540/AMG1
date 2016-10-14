package lanou.amg1.recommendfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.VolleySingleton;
import lanou.amg1.urlall.URLAll;

/**
 * Created by dllo on 16/10/8.
 */
public class OneRecommendFragment extends Base_Fragment{


    private Banner one_Recommend_Fragment_Banner;
    private ListView one_Recommend_Fragment_ListView;
    private View view;

    @Override
    protected void networkRequest() {

        GsonRequest<FragmentRecommendBean> gsonRequest = new GsonRequest<FragmentRecommendBean>(URLAll.NEW_URL, FragmentRecommendBean.class, new Response.Listener<FragmentRecommendBean>() {
            @Override
            public void onResponse(FragmentRecommendBean bean) {
                ArrayList<String> arrayList = new ArrayList<>();
                for(int i = 0; i < bean.getResult().getFocusimg().size(); i++){
                    arrayList.add(bean.getResult().getFocusimg().get(i).getImgurl());
                }
                one_Recommend_Fragment_Banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                one_Recommend_Fragment_Banner.setIndicatorGravity(BannerConfig.RIGHT);
                one_Recommend_Fragment_Banner.setImages(arrayList);

                OneRecommendAdapter adapter = new OneRecommendAdapter(context);

                adapter.setBean(bean);
                one_Recommend_Fragment_ListView.setAdapter(adapter);

                one_Recommend_Fragment_ListView.addHeaderView(view);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        VolleySingleton.getInstance().addRequest(gsonRequest);
        }

    @Override
    protected int setLayout() {
        return R.layout.one_recommend_frament;
    }



    @Override
    protected void control() {

        view = LayoutInflater.from(context).inflate(R.layout.one_recommend_frament_header_item,null);
        one_Recommend_Fragment_Banner = findById(R.id.one_Recommend_Fragment_Banner, view);

        one_Recommend_Fragment_ListView = findById(R.id.one_Recommend_Fragment_ListView);

    }
}
