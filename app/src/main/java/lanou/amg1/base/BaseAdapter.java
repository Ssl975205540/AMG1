package lanou.amg1.base;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/17.
 */
public abstract class BaseAdapter< T > extends android.widget.BaseAdapter {

    public int anInt;
    public ArrayList<T> arrayList;

    private Context context;
    private final LayoutInflater inflater;

    public BaseAdapter(Context context,int anInt) {
        this.context = context;
        this.anInt = anInt;
        inflater = LayoutInflater.from(context);

    }

    public  void setBean(ArrayList<T> arrayList){
        this.arrayList = arrayList;

    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }


}
