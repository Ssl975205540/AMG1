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
import lanou.amg1.bean.DiscoverBean;


/**
 * Created by dllo on 16/9/23.
 */
public class ForMeRecyclerView extends RecyclerView.Adapter<ForMeRecyclerView.ViewHoler>{


    private Context context;

    private DiscoverBean bean;
    private int i;

    public void setBean(DiscoverBean bean,int i) {
        this.bean = bean;
        this.i = i;
    }

    public ForMeRecyclerView(Context context) {
        this.context = context;

    }

    @Override
    public ForMeRecyclerView.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rcv_formeadp_item,null);

        ViewHoler viewHoler = new ViewHoler(view);

        return viewHoler;
    }


    @Override
    public void onBindViewHolder(ForMeRecyclerView.ViewHoler holder, int position) {

        Picasso.with(context).load( bean.getResult().getCardlist().get(i).getData().get(position).getImageurl()).into(holder.rcvItemIv);

        holder.rcvItemTitleTv.setText(bean.getResult().getCardlist().get(i).getData().get(position).getTitle());

            holder.rcv_Item_Subtitle_Tv.setText(bean.getResult().getCardlist().get(i).getData().get(position).getSubtitle());
        holder.rcvItemPriceTv.setText(bean.getResult().getCardlist().get(i).getData().get(position).getCurrentprice());
        holder.rcv_Item_CurrentPrice_Tv.setText(bean.getResult().getCardlist().get(i).getData().get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return bean.getResult().getCardlist().get(i).getData().size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {


        private final ImageView rcvItemIv;
        private final TextView rcvItemTitleTv;
        private final TextView rcvItemPriceTv;
        private final TextView rcv_Item_Subtitle_Tv;
        private final TextView rcv_Item_CurrentPrice_Tv;



        public ViewHoler(View itemView) {
            super(itemView);

            rcvItemIv = (ImageView)itemView.findViewById(R.id.rcv_item_iv);
            rcvItemTitleTv = (TextView)itemView.findViewById(R.id.rcv_item_title_tv);
            rcvItemPriceTv = (TextView)itemView.findViewById(R.id.rcv_item_price_tv);
            rcv_Item_Subtitle_Tv = (TextView)itemView.findViewById(R.id.rcv_item_subtitle_tv);
            rcv_Item_CurrentPrice_Tv = (TextView)itemView.findViewById(R.id.rcv_item_currentprice_tv);
            rcv_Item_CurrentPrice_Tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
            rcv_Item_CurrentPrice_Tv.getPaint().setAntiAlias(true);// 抗锯齿

        }
    }
}
