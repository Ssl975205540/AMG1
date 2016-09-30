package lanou.amg1.findcarfragment.fincarpage.findcarone;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.amg1.R;
import lanou.amg1.basefragment.Base_Fragment;
import lanou.amg1.gsonrequest.GsonRequest;
import lanou.amg1.gsonrequest.VolleySingleton;
import lanou.amg1.urlall.URLAll;

/**
 * Created by dllo on 16/9/27.
 */
public class FindCar_OnePgae extends Base_Fragment{
    private RecyclerView findCar_OnePage_First_RecyclerView;

    @Override
    protected void networkRequest() {


        GsonRequest<AnePageFirstBean> gsonRequest1 = new GsonRequest<AnePageFirstBean>(URLAll.FINCAR_ONE_PAGE_FIRST, AnePageFirstBean.class, new Response.Listener<AnePageFirstBean>() {
            @Override
            public void onResponse(AnePageFirstBean bean) {



                        FindCarOnePageFirstAdapter findCarOnePageFirstAdapter = new FindCarOnePageFirstAdapter(getContext());
                         findCarOnePageFirstAdapter.setBean(bean);
                        findCar_OnePage_First_RecyclerView.setAdapter(findCarOnePageFirstAdapter);
                findCar_OnePage_First_RecyclerView.setLayoutManager(new GridLayoutManager(context,3));




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest1);




    }

    @Override
    protected View setLayout(LayoutInflater inflater) {

        return inflater.inflate(R.layout.findcar_onepage,null);
    }

    @Override
    protected void control() {

        findCar_OnePage_First_RecyclerView = findById(R.id.findCar_OnePage_First_RecyclerView);


    }
}
