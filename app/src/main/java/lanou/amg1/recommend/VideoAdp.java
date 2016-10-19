package lanou.amg1.recommend;

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
 * Created by dllo on 16/10/18.
 */
public class VideoAdp extends BaseAdapter {

    private VideoBean bean;

    private Context context;

    public VideoAdp(Context context) {
        this.context = context;
    }

    public void setBean(VideoBean bean) {

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


            convertView = LayoutInflater.from(context).inflate(R.layout.fgt_video_adp_lv_item, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();


        }


        Picasso.with(context).load(bean.getResult().getList().get(position).getSmallimg()).into(viewHolder.video_xlistview_item_im);

        viewHolder.video_item_title_tv.setText(bean.getResult().getList().get(position).getTitle());
        viewHolder.video_item_playcount_tv.setText(bean.getResult().getList().get(position).getPlaycount()+"");

        viewHolder.video_item_updatetime_tv.setText(bean.getResult().getList().get(position).getUpdatetime());
        viewHolder.video_item_replycount_tv.setText(bean.getResult().getList().get(position).getReplycount()+"");


        return convertView;
    }

    class ViewHolder {


        private TextView video_item_updatetime_tv;
        private TextView video_item_title_tv;
        private TextView video_item_replycount_tv;
        private TextView video_item_playcount_tv;
        private ImageView video_xlistview_item_im;

        public ViewHolder(View convertView) {

            video_item_replycount_tv = (TextView) convertView.findViewById(R.id.video_item_replycount_tv);
            video_item_playcount_tv = (TextView) convertView.findViewById(R.id.video_item_playcount_tv);
            video_xlistview_item_im = (ImageView) convertView.findViewById(R.id.video_xlistview_item_im);
            video_item_title_tv = (TextView) convertView.findViewById(R.id.video_item_title_tv);
            video_item_updatetime_tv = (TextView) convertView.findViewById(R.id.video_item_updatetime_tv);

        }
    }
}
