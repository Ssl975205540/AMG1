package lanou.amg1.findcarfragment.fincarpage.findcarone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lanou.amg1.R;

/**
 * Created by dllo on 16/10/9.
 */
public class OnePageGirdViewAdapter extends RecyclerView.Adapter<OnePageGirdViewAdapter.ViewHorlder>{

    private FindCarOnePageGirdViewBean arrayList;
    private AdapterView.OnItemClickListener mOnItemClickListener = null;
    private Context context;
    private List<Boolean> isClicks;

    public void setArrayList(FindCarOnePageGirdViewBean arrayList) {
        this.arrayList = arrayList;

        isClicks = new ArrayList<>();
        for(int i = 0;i<arrayList.getResult().getList().size();i++){
            isClicks.add(false);
        }
    }

    public OnePageGirdViewAdapter(Context context) {
        this.context = context;


    }



    @Override
    public ViewHorlder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.findcar_onepage_girdview_item,null);

        ViewHorlder viewHorlder = new ViewHorlder(view);

        return viewHorlder;
    }

    @Override
    public void onBindViewHolder(final ViewHorlder holder, final int position) {
        Picasso.with(context).load(arrayList.getResult().getList().get(position).getImg()).into(holder.findCar_OnePage_Item_ImagView);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.itemView.setBackgroundResource(R.drawable.relativelayoutselector);

            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.getResult().getList().size();
    }



    class ViewHorlder extends RecyclerView.ViewHolder {


        private  ImageView findCar_OnePage_Item_ImagView;

        public ViewHorlder(View convertView) {
            super(convertView);
            findCar_OnePage_Item_ImagView = (ImageView)convertView.findViewById(R.id.findCar_OnePage_Item_ImagView);

        }
    }
}
