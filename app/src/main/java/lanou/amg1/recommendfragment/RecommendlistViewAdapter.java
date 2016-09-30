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
 * Created by dllo on 16/9/20.
 */
public class RecommendlistViewAdapter extends BaseAdapter {


    private Context context;
    private FragmentRecommendBean bean;

    private String length = "http://www2.autoimg.cn/newsdfs/g19/M04/2E/ED/160x120_0_autohomecar__wKgFU1fh5eGAZVyKAAGJXs6x4L0958.jpg";
    public RecommendlistViewAdapter(Context context) {
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
    public int getItemViewType(int position) {
        return super.getItemViewType(position);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null;

        if (convertView == null) {


            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_listview_recommend_fragment, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }
        if (bean.getResult().getNewslist().get(position).getSmallpic().length() > 10) {
            Picasso.with(context).load(bean.getResult().getNewslist().get(position).getSmallpic()).into(viewHolder.imageView_item_adapter_listview_recommend_fragment);
        }
        viewHolder.textview_item_adapter_listview_recommend_fragment.setText(bean.getResult().getNewslist().get(position).getTime());
        viewHolder.textview_title_item_adapter_listview_recommend_fragment.setText(bean.getResult().getNewslist().get(position).getTitle());

//        viewHolder.textview_titlebelow_item_adapter_listview_recommend_fragment.setText(bean.getResult().getNewslist().get(position).);
        return convertView;
    }




    class ViewHolder {


        private  TextView textview_titlebelow_item_adapter_listview_recommend_fragment;
        private  TextView textview_title_item_adapter_listview_recommend_fragment;
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
