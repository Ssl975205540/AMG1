package lanou.amg1.forum.page.onepagefirst;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.amg1.R;
import lanou.amg1.tools.URLAll;

/**
 * Created by dllo on 16/9/28.
 */
public class OnPageFirstTwoAdp extends BaseAdapter{
    private Context context;

    private ArrayList<URLAll.Bean> bean;

    public OnPageFirstTwoAdp(Context context) {
        this.context = context;
    }

    public void setBean(ArrayList<URLAll.Bean> bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {



        return bean.size();
    }

    @Override
    public Object getItem(int position) {




        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {



        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        Log.d("OnePageFirstActivity", "麻痹的3");
        if (convertView == null) {


            convertView = LayoutInflater.from(context).inflate(R.layout.two_adapter_item, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.item_INParent_TextView.setText(bean.get(position).getTitle());

        return convertView;
    }




    class ViewHolder {



        private TextView item_INParent_TextView;

        public ViewHolder(View convertView) {


            item_INParent_TextView = (TextView)convertView.findViewById(R.id.item_INParent_TextView);

        }
    }


}
