package lanou.amg1.discoverfragment.recyclerviewsector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import lanou.amg1.R;
import lanou.amg1.bean.DiscoverBean;


/**
 * Created by dllo on 16/9/23.
 */
public class RecyclerViewServerAdapter extends RecyclerView.Adapter<RecyclerViewServerAdapter.ViewHoler>{


    private Context context;

    private DiscoverBean bean;
    private int i;

    public void setBean(DiscoverBean bean,int i) {
        this.bean = bean;
        this.i = i;
    }

    public RecyclerViewServerAdapter(Context context) {
        this.context = context;

    }

    @Override
    public RecyclerViewServerAdapter.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rcvserveradp_item,null);

        ViewHoler viewHoler = new ViewHoler(view);

        return viewHoler;
    }


    @Override
    public void onBindViewHolder(RecyclerViewServerAdapter.ViewHoler holder, int position) {

        Picasso.with(context).load( bean.getResult().getCardlist().get(i).getData().get(position).getImageurl()).into(holder.server_ImagView);


    }

    @Override
    public int getItemCount() {
        return bean.getResult().getCardlist().get(i).getData().size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        private  ImageView server_ImagView;

        public ViewHoler(View itemView) {
            super(itemView);

            server_ImagView = (ImageView)itemView.findViewById(R.id.server_ImagView);



        }
    }
}
