package lanou.amg1.discoverfragment.recyclerviewnavigation;

import android.content.Context;
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
public class RecyclerViewNavigationAdapter extends RecyclerView.Adapter<RecyclerViewNavigationAdapter.ViewHoler>{


    private Context context;

    private OnRecyclerltemClickListener onRecyclerltemClickListener;

    public void setOnRecyclerltemClickListener(OnRecyclerltemClickListener onRecyclerltemClickListener) {
        this.onRecyclerltemClickListener = onRecyclerltemClickListener;
    }

    private DiscoverBean bean;
    private int i;

    public void setBean(DiscoverBean bean,int i) {
        this.bean = bean;
        this.i = i;
    }

    public RecyclerViewNavigationAdapter(Context context) {
        this.context = context;

    }

    @Override
    public RecyclerViewNavigationAdapter.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rcvnavigationadp_item,null);

        ViewHoler viewHoler = new ViewHoler(view);



        return viewHoler;
    }


    @Override
    public void onBindViewHolder(final RecyclerViewNavigationAdapter.ViewHoler holder, final int position) {

        Picasso.with(context).load( bean.getResult().getCardlist().get(i).getData().get(position).getImageurl()).into(holder.recycler_Navigation_Item_ImageView);


        holder.recycler_Navigation_Item_TextView_Title.setText(bean.getResult().getCardlist().get(i).getData().get(position).getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onRecyclerltemClickListener.click(position,holder);



            }
        });




    }

    @Override
    public int getItemCount() {
        return bean.getResult().getCardlist().get(i).getData().size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        private  ImageView recycler_Navigation_Item_ImageView;
        private  TextView recycler_Navigation_Item_TextView_Title;
        private  View itemView;
        public ViewHoler(View itemView) {
            super(itemView);

            this.itemView = itemView;
            recycler_Navigation_Item_ImageView = (ImageView)itemView.findViewById(R.id.recycler_Navigation_Item_ImageView);

            recycler_Navigation_Item_TextView_Title = (TextView)itemView.findViewById(R.id.recycler_Navigation_Item_TextView_Title);


        }
    }
}
