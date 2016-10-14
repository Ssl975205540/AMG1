package lanou.amg1.recommendfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import lanou.amg1.R;
import lanou.amg1.gsonrequest.MyItemTouchCallback;

/**
 * Created by dllo on 16/10/12.
 */
public class MoreRecyclerviewAdapter extends RecyclerView.Adapter<MoreRecyclerviewAdapter.ViewHolder> implements MyItemTouchCallback.ItemTouchAdapter {

    private List<MoreActivityBean.ResultBean.MetalistBean.ListBean> moreActivityBean;
    private Context context;

    public MoreRecyclerviewAdapter(Context context) {
        this.context = context;
    }

    public void setMoreActivityBean(List<MoreActivityBean.ResultBean.MetalistBean.ListBean> moreActivityBean) {
        this.moreActivityBean = moreActivityBean;
    }


    @Override
    public MoreRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_recommend_more_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoreRecyclerviewAdapter.ViewHolder holder, int position) {

        Picasso.with(context).load(moreActivityBean.get(position).getIconurl()).into(holder.activity_recommend_more_item_imageview);
        holder.activity_recommend_more_item_textview.setText(moreActivityBean.get(position).getName());


    }


    @Override
    public int getItemCount() {
        return moreActivityBean.size();
    }




    @Override
    public void onMove(int fromPosition, int toPosition) {

// 如果size-1 最后一个就不懂
//        if (fromPosition==moreActivityBean.getResult().getMetalist().get(0).getList().size()-1 || toPosition==moreActivityBean.getResult().getMetalist().get(0).getList().size()-1){
//            return;
//        }

        if (fromPosition==moreActivityBean.size() || toPosition==moreActivityBean.size()){
            return;
        }
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(moreActivityBean, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(moreActivityBean, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);


    }

    @Override
    public void onSwiped(int position) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private  ImageView activity_recommend_more_item_imageview;
        private  TextView activity_recommend_more_item_textview;

        public ViewHolder(View itemView) {
            super(itemView);

            activity_recommend_more_item_imageview = (ImageView) itemView.findViewById(R.id.activity_recommend_more_item_imageview);
            activity_recommend_more_item_textview = (TextView) itemView.findViewById(R.id.activity_recommend_more_item_textview);

        }
    }
}
