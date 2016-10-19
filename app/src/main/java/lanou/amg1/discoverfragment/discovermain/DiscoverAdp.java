package lanou.amg1.discoverfragment.discovermain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.amg1.R;
import lanou.amg1.bean.DiscoverBean;

/**
 * Created by dllo on 16/9/22.
 */
public class DiscoverAdp extends BaseAdapter {

    private DiscoverBean bean;

    private Context context;
    private int i;

    public DiscoverAdp(Context context) {
        this.context = context;
    }

    public void setBean(DiscoverBean bean,int i) {
        this.bean = bean;
        this.i = i;
    }


    @Override
    public int getCount() {
        return bean.getResult().getCardlist().get(i).getData().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getResult().getCardlist().get(i).getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.discover_fragment_item, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }


        Picasso.with(context).load( bean.getResult().getCardlist().get(i).getData().get(position).getLogo()).into(viewHolder.discoverItemIv);

        viewHolder.discoverItemAdinfoTv.setText(bean.getResult().getCardlist().get(i).getData().get(position).getAdinfo());

        viewHolder.discoverItem_Title_Tv.setText(bean.getResult().getCardlist().get(i).getData().get(position).getTitle());

        viewHolder.discoverItem_Price_Tv.setText(bean.getResult().getCardlist().get(i).getData().get(position).getPrice());
        return convertView;





    }

    class ViewHolder{


        private final ImageView discoverItemIv;
        private final TextView discoverItemAdinfoTv;
        private final TextView discoverItem_Title_Tv;
        private final TextView discoverItem_Price_Tv;

        public ViewHolder(View convertView) {

            discoverItemIv = (ImageView) convertView.findViewById(R.id.discover_item_iv);
            discoverItemAdinfoTv = (TextView) convertView.findViewById(R.id.discover_item_adinfo_tv);
            discoverItem_Title_Tv = (TextView) convertView.findViewById(R.id.discover_item_title_tv);
            discoverItem_Price_Tv = (TextView) convertView.findViewById(R.id.discover_item_price_tv);
        }
    }

}
