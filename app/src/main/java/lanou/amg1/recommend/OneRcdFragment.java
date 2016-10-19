package lanou.amg1.recommend;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.base.BaseFragment;
import lanou.amg1.bean.RcdFgtBean;
import lanou.amg1.convenientbanner.LocalImageHolderView;
import lanou.amg1.tools.requset.GsonRequest;
import lanou.amg1.tools.requset.VolleySingleton;
import lanou.amg1.tools.URLAll;


/**
 * Created by dllo on 16/10/8.
 */
public class OneRcdFragment extends BaseFragment implements OnItemClickListener {


    private ConvenientBanner one_Recommend_Fragment_Banner;
    private ListView one_Recommend_Fragment_ListView;
    private View view;

    @Override
    protected void initData() {

        GsonRequest<RcdFgtBean> gsonRequest = new GsonRequest<RcdFgtBean>(URLAll.NEW_URL, RcdFgtBean.class, new Response.Listener<RcdFgtBean>() {
            @Override
            public void onResponse(RcdFgtBean bean) {
                ArrayList<String> arrayList = new ArrayList<>();
                for (int i = 0; i < bean.getResult().getFocusimg().size(); i++) {
                    arrayList.add(bean.getResult().getFocusimg().get(i).getImgurl());
                }


                sdasc(arrayList);

                OneRcdAdp adapter = new OneRcdAdp(context);

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

    private void sdasc(ArrayList<String> arrayList) {

        one_Recommend_Fragment_Banner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, arrayList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.dot_selected_f, R.drawable.dot_unselected_f})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this);



}

    @Override
    protected int setContentView() {
        return R.layout.one_recommend_frament;
    }



    @Override
    protected void initViews() {

        view = LayoutInflater.from(context).inflate(R.layout.one_rcd_frg_header_item,null);
        one_Recommend_Fragment_Banner = findById(R.id.one_Recommend_Fragment_Banner, view);

        one_Recommend_Fragment_ListView = findById(R.id.one_Recommend_Fragment_ListView);

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onItemClick(int position) {




    }



}
