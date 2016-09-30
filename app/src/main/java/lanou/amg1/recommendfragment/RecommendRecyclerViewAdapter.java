package lanou.amg1.recommendfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.amg1.R;

/**
 * Created by dllo on 16/9/20.
 */
public class RecommendRecyclerViewAdapter extends RecyclerView.Adapter<RecommendRecyclerViewAdapter.ViewHoleder>{
    private Context context;
    private final ArrayList<String> arrayList;

    public RecommendRecyclerViewAdapter(Context context) {
        this.context = context;

        arrayList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            arrayList.add(""+i);
        }


    }

    @Override
    public RecommendRecyclerViewAdapter.ViewHoleder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_adapter_listview_recommend_fragment,parent,false);

        ViewHoleder viewHoleder = new ViewHoleder(view);




        return viewHoleder;
    }

    @Override
    public void onBindViewHolder(RecommendRecyclerViewAdapter.ViewHoleder holder, int position) {
        holder.textview_item_adapter_listview_recommend_fragment.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHoleder extends RecyclerView.ViewHolder {
        private final TextView textview_item_adapter_listview_recommend_fragment;

        public ViewHoleder(View itemView) {
            super(itemView);

            textview_item_adapter_listview_recommend_fragment =(TextView)itemView.findViewById(R.id.textview_item_adapter_listview_recommend_fragment);
        }
    }
}
