package lanou.amg1.discoverfragment.horizontallistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import lanou.amg1.R;
import lanou.amg1.bean.DiscoverBean;


/**
 * Created by dllo on 16/9/23.
 */
public class HorizontalListViewAdapter extends BaseAdapter {
    private DiscoverBean bean;

    private Context mContext;

    private int selectIndex = -1;
    private int i;

    public void setBean(DiscoverBean bean,int i) {
        this.bean = bean;
        this.i = i;
    }

    public HorizontalListViewAdapter(Context context) {
        this.mContext = context;
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

        ViewHolder holder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.horizontal_list_item, null);

            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == selectIndex) {
            convertView.setSelected(true);
        } else {
            convertView.setSelected(false);
        }

        Picasso.with(mContext).load(bean.getResult().getCardlist().get(i).getData().get(position).getImageurl()).into(holder.mImage);

        return convertView;
    }

    private static class ViewHolder {
        private ImageView mImage;


        public ViewHolder(View convertView) {
            mImage = (ImageView) convertView.findViewById(R.id.horizontal_List_Item_ImageView);

        }
    }

//    private Bitmap getPropThumnail(int id) {
//        Drawable d = mContext.getResources().getDrawable(id);
//        Bitmap b = BitmapUtil.drawableToBitmap(d);
////      Bitmap bb = BitmapUtil.getRoundedCornerBitmap(b, 100);
//        int w = mContext.getResources().getDimensionPixelOffset(R.dimen.thumnail_default_width);
//        int h = mContext.getResources().getDimensionPixelSize(R.dimen.thumnail_default_height);
//
//        Bitmap thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);
//
//        return thumBitmap;
//    }

    public void setSelectIndex(int i) {
        selectIndex = i;
    }
}