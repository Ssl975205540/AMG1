package lanou.amg1.guidepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lanou.amg1.R;


/**
 * Created by dllo on 16/9/19.
 */
public class GuidePager_Fagment_One extends Fragment{

    private Button btn_GuidePageActivity_Fragment_One;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.guidepager_fragment_one,null);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_GuidePageActivity_Fragment_One = (Button) view.findViewById(R.id.btn_GuidePageActivity_Fragment_One);

        btn_GuidePageActivity_Fragment_One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout_GuidePageActivity,new GuidePager_Fragment_Two());
                fragmentTransaction.commit();
            }
        });

    }
}
