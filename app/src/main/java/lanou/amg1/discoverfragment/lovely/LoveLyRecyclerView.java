package lanou.amg1.discoverfragment.lovely;

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
public class LoveLyRecyclerView extends RecyclerView.Adapter<LoveLyRecyclerView.ViewHoler>{


    private Context context;

    private DiscoverBean bean;
    private int i;

    public void setBean(DiscoverBean bean,int i) {
        this.bean = bean;
        this.i = i;
    }

    public LoveLyRecyclerView(Context context) {
        this.context = context;

    }

    @Override
    public LoveLyRecyclerView.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_lovelyadapter_item,null);

        ViewHoler viewHoler = new ViewHoler(view);

        return viewHoler;
    }


    @Override
    public void onBindViewHolder(LoveLyRecyclerView.ViewHoler holder, int position) {

        Picasso.with(context).load( bean.getResult().getCardlist().get(i).getData().get(position).getImageurl()).into(holder.lovely_ImagView);

        holder.lovely_Title_TextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getTitle());

            holder.lovely_Subtitle_TextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getSubtitle());

        holder.lovely_Price_TextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getCurrentprice());


        holder.lovely_currentprice_TextView.setText(bean.getResult().getCardlist().get(i).getData().get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return bean.getResult().getCardlist().get(i).getData().size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        private  TextView lovely_currentprice_TextView;
        private  TextView lovely_Subtitle_TextView;
        private  TextView lovely_Price_TextView;
        private  TextView lovely_Title_TextView;
        private  ImageView lovely_ImagView;

        public ViewHoler(View itemView) {
            super(itemView);

            lovely_ImagView = (ImageView)itemView.findViewById(R.id.lovely_ImagView);
            lovely_Title_TextView = (TextView)itemView.findViewById(R.id.lovely_Title_TextView);
            lovely_Price_TextView = (TextView)itemView.findViewById(R.id.lovely_Price_TextView);
            lovely_Subtitle_TextView = (TextView)itemView.findViewById(R.id.lovely_Subtitle_TextView);
            lovely_currentprice_TextView = (TextView)itemView.findViewById(R.id.lovely_currentprice_TextView);
            lovely_currentprice_TextView.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
            lovely_currentprice_TextView.getPaint().setAntiAlias(true);// 抗锯齿

        }
    }
}
