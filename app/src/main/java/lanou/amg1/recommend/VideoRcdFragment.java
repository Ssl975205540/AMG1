package lanou.amg1.recommend;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.amg1.R;
import lanou.amg1.base.BaseFragment;
import lanou.amg1.tools.URLAll;
import lanou.amg1.tools.requset.GsonRequest;
import lanou.amg1.tools.requset.VolleySingleton;
import lanou.amg1.tools.xlistview.XListView;

/**
 * Created by dllo on 16/10/18.
 */
public class VideoRcdFragment extends BaseFragment{


    private XListView video_xlistview;

    @Override
    protected void initData() {



        GsonRequest<VideoBean> gsonRequest = new GsonRequest<VideoBean>(URLAll.URL_VIEDIO, VideoBean.class, new Response.Listener<VideoBean>() {
            @Override
            public void onResponse(VideoBean bean) {

                VideoAdp videoAdp = new VideoAdp(context);

                videoAdp.setBean(bean);

                video_xlistview.setAdapter(videoAdp);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        VolleySingleton.getInstance().addRequest(gsonRequest);
    }




    @Override
    protected int setContentView() {
        return R.layout.video_rcd_fragment;
    }

    @Override
    protected void initViews() {

        video_xlistview = findById(R.id.video_xlistview);



    }

    @Override
    protected void initListeners() {


    }

}
