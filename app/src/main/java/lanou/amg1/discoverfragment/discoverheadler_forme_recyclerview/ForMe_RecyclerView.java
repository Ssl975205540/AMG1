package lanou.amg1.discoverfragment.discoverheadler_forme_recyclerview;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.amg1.R;
import lanou.amg1.discoverfragment.discovermain.DiscoverBean;


/**
 * Created by dllo on 16/9/23.
 */
public class ForMe_RecyclerView extends RecyclerView.Adapter<ForMe_RecyclerView.ViewHoler>{


    private Context context;

    private DiscoverBean bean;
    private int i;

    public void setBean(DiscoverBean bean,int i) {
        this.bean = bean;
        this.i = i;
    }

    public ForMe_RecyclerView(Context context) {
        this.context = context;

    }

    @Override
    public ForMe_RecyclerView.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_formeadapter_item,null);

        ViewHoler viewHoler = new ViewHoler(view);

        return viewHoler;
    }


    @Override
    public void onBindViewHolder(ForMe_RecyclerView.ViewHoler holder, int position) {

        Picasso.with(context).load( bean.getResult().getCardlist().get(i).getData().get(position).getImageurl()).into(holder.forme_ImagView);

        holder.forme_Title_TextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getTitle());

            holder.forme_Subtitle_TextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getSubtitle());

        holder.forme_Price_TextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getCurrentprice());


        holder.forme_currentprice_TextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return bean.getResult().getCardlist().get(i).getData().size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        private  TextView forme_Title_TextView;
        private  TextView forme_Price_TextView;
        private  TextView forme_Subtitle_TextView;
        private  TextView forme_currentprice_TextView;
        private  ImageView forme_ImagView;

        public ViewHoler(View itemView) {
            super(itemView);

            forme_ImagView = (ImageView)itemView.findViewById(R.id.forme_ImagView);
            forme_Title_TextView = (TextView)itemView.findViewById(R.id.forme_Title_TextView);
            forme_Price_TextView = (TextView)itemView.findViewById(R.id.forme_Price_TextView);
            forme_Subtitle_TextView = (TextView)itemView.findViewById(R.id.forme_Subtitle_TextView);
            forme_currentprice_TextView = (TextView)itemView.findViewById(R.id.forme_currentprice_TextView);
            forme_currentprice_TextView.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
            forme_currentprice_TextView.getPaint().setAntiAlias(true);// 抗锯齿

        }
    }
}
