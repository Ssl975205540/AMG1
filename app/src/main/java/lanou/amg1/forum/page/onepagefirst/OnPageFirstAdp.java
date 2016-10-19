package lanou.amg1.forum.page.onepagefirst;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.amg1.R;
import lanou.amg1.bean.OnePageFirstBean;

/**
 * Created by dllo on 16/9/28.
 */
public class OnPageFirstAdp extends BaseAdapter{
    private Context context;

    private OnePageFirstBean bean;

    public OnPageFirstAdp(Context context) {
        Log.d("OnePageFirstActivity", "麻痹的2");
        this.context = context;
    }

    public void setBean(OnePageFirstBean bean) {
        this.bean = bean;
    }


    public void setBean1(OnePageFirstBean bean1) {

        bean.getResult().getList().addAll(bean1.getResult().getList());
        notifyDataSetChanged();

    }
    @Override
    public int getCount() {



        return bean.getResult().getList().size();
    }

    @Override
    public Object getItem(int position) {




        return bean.getResult().getList().get(position);
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


            convertView = LayoutInflater.from(context).inflate(R.layout.fgt_rcd_adp_lv_item, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        Picasso.with(context).load(bean.getResult().getList().get(position).getSmallpic()).into(viewHolder.imageView_item_adapter_listview_recommend_fragment);
        viewHolder.textview_titlebelow_item_adapter_listview_recommend_fragment.setText(bean.getResult().getList().get(position).getBbsname());
        viewHolder.textview_item_adapter_listview_recommend_fragment.setText(bean.getResult().getList().get(position).getReplycounts()+ "回帖");
        viewHolder.textview_title_item_adapter_listview_recommend_fragment.setText(bean.getResult().getList().get(position).getTitle());

        return convertView;
    }




    class ViewHolder {


        private  TextView textview_titlebelow_item_adapter_listview_recommend_fragment;
        private TextView textview_title_item_adapter_listview_recommend_fragment;
        private ImageView imageView_item_adapter_listview_recommend_fragment;
        private  TextView textview_item_adapter_listview_recommend_fragment;

        public ViewHolder(View convertView) {

            textview_item_adapter_listview_recommend_fragment = (TextView)convertView.findViewById(R.id.textview_item_adapter_listview_recommend_fragment);

            textview_title_item_adapter_listview_recommend_fragment = (TextView)convertView.findViewById(R.id.textview_title_item_adapter_listview_recommend_fragment);
            textview_titlebelow_item_adapter_listview_recommend_fragment = (TextView)convertView.findViewById(R.id.textview_titlebelow_item_adapter_listview_recommend_fragment);

            imageView_item_adapter_listview_recommend_fragment = (ImageView) convertView.findViewById(R.id.imageView_item_adapter_listview_recommend_fragment);
        }
    }


}
