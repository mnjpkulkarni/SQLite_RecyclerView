package SQLite.RecyclerView.Task.app.Example;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by manojkulkarni on 11/6/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private Context context;
    List<MovieData> movieArrayList;
    static  OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public MyAdapter(List<MovieData> movieArrayList,Context context){
        this.movieArrayList=movieArrayList;
        this.context=context;
    }



    class MyHolder extends RecyclerView.ViewHolder {

        TextView name,overview;
        ImageView poster;

        public MyHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.movie_name);
            overview=(TextView)itemView.findViewById(R.id.movie_overview);
            poster=(ImageView)itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(view,getAdapterPosition());
                }
            });
        }
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        MovieData movieData=movieArrayList.get(position);
        holder.name.setText(movieData.getName());
        holder.overview.setText(movieData.getOverview());
        Picasso.with(context).load(movieData.getImage()).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }



}
