package lanou.amg1.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.amg1.R;

/**
 * Created by dllo on 16/10/10.
 */
public class ActivitySearchListViewAdapter extends BaseAdapter{

    private ActivitySearchBean bean;

    private ArrayList<String> arrayList;
    private Context context;
    public int i = 1;

    public ActivitySearchListViewAdapter(Context context) {
        this.context = context;
    }

    public void setBean(ActivitySearchBean bean) {
        this.bean = bean;
    }
    public void setBean(ArrayList<String> bean, int i) {
        this.arrayList = bean;
        this.i = i;
    }


    @Override
    public int getCount() {
        if(0 == i){
            if(arrayList.size() > 10){
                return 10;
            }else {
                return arrayList.size();
            }

        }else {

            return bean.getResult().getWordlist().size();
        }

    }

    @Override
    public Object getItem(int position) {
        if(0 == i){

            return arrayList.get(position);

        }else {
            return bean.getResult().getWordlist().get(position);

        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.activity_search_listview_adapter, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        if(0 == i){

            viewHolder.activity_Search_ListView_Item_TextView.setText(arrayList.get(position));


        }else {
            viewHolder.activity_Search_ListView_Item_TextView.setText(bean.getResult().getWordlist().get(position).getName());

        }

        return convertView;





    }

    class ViewHolder{

        private  TextView activity_Search_ListView_Item_TextView;
        private ImageView activity_Search_ListView_Item_ImageView;


        public ViewHolder(View convertView) {
            activity_Search_ListView_Item_TextView =(TextView)convertView.findViewById(R.id.activity_Search_ListView_Item_TextView);
            activity_Search_ListView_Item_ImageView =(ImageView)convertView.findViewById(R.id.activity_Search_ListView_Item_ImageView);
        }
    }

}
