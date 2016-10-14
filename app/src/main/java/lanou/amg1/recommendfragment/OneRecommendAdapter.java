package lanou.amg1.recommendfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.amg1.R;

/**
 * Created by dllo on 16/10/8.
 */
public class OneRecommendAdapter extends BaseAdapter{

    private FragmentRecommendBean bean;

    private Context context;

    public OneRecommendAdapter(Context context) {
        this.context = context;
    }

    public void setBean(FragmentRecommendBean bean) {

        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getResult().getNewslist().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getResult().getNewslist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){


            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_listview_recommend_fragment,null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();


        }


        try {
            Picasso.with(context).load(bean.getResult().getNewslist().get(position).getSmallpic()).into(viewHolder.imageView_item_adapter_listview_recommend_fragment);

        }catch (IllegalArgumentException e){


        }

        viewHolder.textview_item_adapter_listview_recommend_fragment.setText(bean.getResult().getNewslist().get(position).getTime());
        viewHolder.textview_title_item_adapter_listview_recommend_fragment.setText(bean.getResult().getNewslist().get(position).getTitle());

//        viewHolder.textview_titlebelow_item_adapter_listview_recommend_fragment.setText(bean.getResult().getNewslist().get(position).getReplycount());


        return convertView;
    }

    class ViewHolder{


        private  TextView textview_titlebelow_item_adapter_listview_recommend_fragment;
        private  TextView textview_title_item_adapter_listview_recommend_fragment;
        private  TextView textview_item_adapter_listview_recommend_fragment;
        private  ImageView imageView_item_adapter_listview_recommend_fragment;

        public ViewHolder(View convertView) {

            textview_title_item_adapter_listview_recommend_fragment =(TextView)convertView.findViewById(R.id.textview_title_item_adapter_listview_recommend_fragment);
            textview_item_adapter_listview_recommend_fragment =(TextView)convertView.findViewById(R.id.textview_item_adapter_listview_recommend_fragment);
            imageView_item_adapter_listview_recommend_fragment =(ImageView)convertView.findViewById(R.id.imageView_item_adapter_listview_recommend_fragment);
            textview_titlebelow_item_adapter_listview_recommend_fragment =(TextView)convertView.findViewById(R.id.textview_titlebelow_item_adapter_listview_recommend_fragment);

        }
    }
}
