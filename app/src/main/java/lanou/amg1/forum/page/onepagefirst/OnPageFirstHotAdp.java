package lanou.amg1.forum.page.onepagefirst;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import lanou.amg1.R;
import lanou.amg1.bean.OnePageFirstHotBean;

/**
 * Created by dllo on 16/9/29.
 */
public class OnPageFirstHotAdp extends BaseAdapter{

    private Context context;
    private OnePageFirstHotBean bean;

    public void setBean(OnePageFirstHotBean bean) {
        this.bean = bean;
    }

    public OnPageFirstHotAdp(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return bean.getResult().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return  bean.getResult().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {


            convertView = LayoutInflater.from(context).inflate(R.layout.onepagefgt_hot_lv_adp_item, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        if(bean.getResult().getList().get(position).getHeadimg().length()>10){
            Picasso.with(context).load(bean.getResult().getList().get(position).getHeadimg()).transform(new CircleTransform()).into(viewHolder.ima);
        }else {
            Picasso.with(context).load(R.mipmap.ahlib_userpic_default).transform(new CircleTransform()).into(viewHolder.ima);
        }

        viewHolder.hot_Item_Topicinfo_TextView.setText(bean.getResult().getList().get(position).getTopicinfo());
        viewHolder.hot_Item_Title_TextView.setText(bean.getResult().getList().get(position).getTitle());
        viewHolder.hot_Item_Replycounts_TextView.setText(bean.getResult().getList().get(position).getReplycounts() + "回帖");
        viewHolder.hot_Item_Nickname_TextView.setText(bean.getResult().getList().get(position).getNickname());
        viewHolder.hot_Item_Authseries_TextView.setText(bean.getResult().getList().get(position).getAuthseries());
        viewHolder.hot_Item_Lastreplydate_TextView.setText(bean.getResult().getList().get(position).getLastreplydate().substring(5,16));

        viewHolder.hot_Item_Bbsname_TextView.setText(bean.getResult().getList().get(position).getBbsname());




        return convertView;
    }




    class ViewHolder {


        private  TextView hot_Item_Topicinfo_TextView;
        private  TextView hot_Item_Title_TextView;
        private  TextView hot_Item_Replycounts_TextView;
        private  TextView hot_Item_Nickname_TextView;
        private TextView hot_Item_Authseries_TextView;
        private TextView hot_Item_Lastreplydate_TextView;
        private ImageView ima;
        private  TextView hot_Item_Bbsname_TextView;

        public ViewHolder(View convertView) {

            ima = (ImageView) convertView.findViewById(R.id.asd);
            hot_Item_Authseries_TextView = (TextView)convertView.findViewById(R.id.hot_Item_Authseries_TextView);

            hot_Item_Bbsname_TextView = (TextView)convertView.findViewById(R.id.hot_Item_Bbsname_TextView);
            hot_Item_Lastreplydate_TextView = (TextView)convertView.findViewById(R.id.hot_Item_Lastreplydate_TextView);
            hot_Item_Nickname_TextView = (TextView)convertView.findViewById(R.id.hot_Item_Nickname_TextView);
            hot_Item_Replycounts_TextView = (TextView)convertView.findViewById(R.id.hot_Item_Replycounts_TextView);
            hot_Item_Title_TextView = (TextView)convertView.findViewById(R.id.hot_Item_Title_TextView);
            hot_Item_Topicinfo_TextView = (TextView)convertView.findViewById(R.id.hot_Item_Topicinfo_TextView);


        }
    }
    public static class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }

    }


}
