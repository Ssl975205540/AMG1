package lanou.amg1.findcar.fincarpage.findcarone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lanou.amg1.R;
import lanou.amg1.bean.AnePageFirstBean;


/**
 * Created by dllo on 16/9/23.
 */
public class FindCarOnePageFirstAdp extends RecyclerView.Adapter<FindCarOnePageFirstAdp.ViewHoler>{


    private Context context;

    private AnePageFirstBean bean;


    public void setBean(AnePageFirstBean bean) {
        this.bean = bean;

    }

    public FindCarOnePageFirstAdp(Context context) {
        this.context = context;

    }

    @Override
    public FindCarOnePageFirstAdp.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_findcar_first_adp_item,null);

        ViewHoler viewHoler = new ViewHoler(view);

        return viewHoler;
    }


    @Override
    public void onBindViewHolder(FindCarOnePageFirstAdp.ViewHoler holder, int position) {


        holder.recycler_Findcar_First_Adapter_Item_TextView.setText(bean.getResult().getMetalist().get(0).getList().get(position).getName());

    }

    @Override
    public int getItemCount() {
        return bean.getResult().getMetalist().get(0).getList().size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        private  TextView recycler_Findcar_First_Adapter_Item_TextView;

        public ViewHoler(View itemView) {
            super(itemView);

            recycler_Findcar_First_Adapter_Item_TextView = (TextView)itemView.findViewById(R.id.recycler_Findcar_First_Adapter_Item_TextView);



        }
    }
}
