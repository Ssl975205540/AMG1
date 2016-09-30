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

/**
 * Created by dllo on 16/9/22.
 */
public class DiscoverAdapter extends BaseAdapter {

    private DiscoverBean bean;

    private Context context;
    private int i;

    public DiscoverAdapter(Context context) {
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


        Picasso.with(context).load( bean.getResult().getCardlist().get(i).getData().get(position).getLogo()).into(viewHolder.discoverItemImageView);


            viewHolder.discoverItemAdinfoTextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getAdinfo());

        viewHolder.discoverItemTitleTextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getTitle());

        viewHolder.discoverItemPriceTextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getPrice());
        return convertView;





    }

    class ViewHolder{


        private  TextView discoverItemTitleTextView;
        private  TextView discoverItemAdinfoTextView;
        private  TextView discoverItemPriceTextView;
        private  ImageView discoverItemImageView;

        public ViewHolder(View convertView) {

            discoverItemImageView = (ImageView) convertView.findViewById(R.id.discoverItemImageView);
            discoverItemAdinfoTextView = (TextView) convertView.findViewById(R.id.discoverItemAdinfoTextView);
            discoverItemTitleTextView = (TextView) convertView.findViewById(R.id.discoverItemTitleTextView);
            discoverItemPriceTextView = (TextView) convertView.findViewById(R.id.discoverItemPriceTextView);
        }
    }

}
