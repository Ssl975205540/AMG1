package lanou.amg1.guidepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import lanou.amg1.R;
import lanou.amg1.main.MainActivity;
import lanou.amg1.main.SendEvent;
import lanou.amg1.urlall.URLAll;


/**
 * Created by dllo on 16/9/19.
 */
public class GuidePager_Fragment_Two extends Fragment {

    private Button btn_GuidePageActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.guidepager_fragment_two, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_GuidePageActivity = (Button) view.findViewById(R.id.btn_GuidePageActivity);


        btn_GuidePageActivity.setOnClickListener(new View.OnClickListener() {

            private SharedPreferences.Editor savedInstanceState_edit;

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(URLAll.ZERO, URLAll.ZERO);
                SendEvent sendEvent = new SendEvent();
                sendEvent.setChoice(URLAll.THREE);
                EventBus.getDefault().post(sendEvent);

            }
        });

    }
}
