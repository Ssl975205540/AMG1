package lanou.amg1.forumfragment.page;

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
 * Created by dllo on 16/9/21.
 */
public class OnPageListViewAdapter extends BaseAdapter{

    private OnePageFragmentBean bean;

    private Context context;

    public OnPageListViewAdapter(Context context) {
        this.context = context;
    }

    public void setBean(OnePageFragmentBean bean) {
        this.bean = bean;
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

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.onepageframent_item, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        Picasso.with(context).load(bean.getResult().getList().get(position).getSmallpic()).into(viewHolder.onePageImageView);
        viewHolder.onePageTextViewTitle.setText(bean.getResult().getList().get(position).getTitle());
        viewHolder.onePageTextView.setText(bean.getResult().getList().get(position).getReplycounts()+"回帖");
        viewHolder.onePageTextViewTitleBelow.setText(bean.getResult().getList().get(position).getBbsname());

    return convertView;





    }

    public void setBean1(OnePageFragmentBean bean1) {
        bean.getResult().getList().addAll(bean1.getResult().getList());
        notifyDataSetChanged();

    }

    class ViewHolder{


        private  TextView onePageTextViewTitle;
        private  TextView onePageTextViewTitleBelow;
        private  ImageView onePageImageView;
        private  TextView onePageTextView;

        public ViewHolder(View convertView) {
            onePageTextViewTitle =(TextView)convertView.findViewById(R.id.onePageTextViewTitle);
            onePageTextViewTitleBelow =(TextView)convertView.findViewById(R.id.onePageTextViewTitleBelow);
            onePageImageView =(ImageView)convertView.findViewById(R.id.onePageImageView);
            onePageTextView =(TextView)convertView.findViewById(R.id.onePageTextView);
        }
    }

}
