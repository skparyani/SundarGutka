package skp.com.sundargutka.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import skp.com.sundargutka.R;

/**
 * Created by Sateesh.Kumar on 11/11/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListHolder> {

    private ArrayList<String> pathList;
    private static Context mContext;

    public RecyclerViewAdapter(ArrayList<String> pathList, Context context)
    {
        this.pathList = new ArrayList<>(pathList);
        mContext = context;
    }

    public static class ListHolder extends RecyclerView.ViewHolder{

        public TextView mTextview;

        public ListHolder(View itemView) {
            super(itemView);
            Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/urdu.ttf");
            mTextview = (TextView) itemView.findViewById(R.id.path_name);
            mTextview.setTypeface(tf, Typeface.BOLD);
        }
    }

    @Override
    public RecyclerViewAdapter.ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.path_list_view, parent, false);

        return new ListHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ListHolder holder, int position) {


        holder.mTextview.setText(pathList.get(position));
    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }
}
