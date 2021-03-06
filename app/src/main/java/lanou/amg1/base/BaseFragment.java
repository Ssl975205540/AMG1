package lanou.amg1.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/9/20.
 */
public abstract class BaseFragment extends Fragment{

    public Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(setContentView(),null);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initListeners();
        initData();
    }

    protected abstract void initData();

    protected abstract int setContentView();

    protected abstract void initViews();

    protected abstract void initListeners();


    public < T extends Fragment> T findFragmentByid(int i){


        return (T) getFragmentManager().findFragmentById(i);

    }




    public  <T extends View> T findById (int i){

        return  (T)getView().findViewById(i);
    }

    public <T extends View> T findById (int i ,View view){

        return  (T)view.findViewById(i);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void onStop() {
        super.onStop();

    }


}
