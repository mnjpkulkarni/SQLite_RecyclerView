package SQLite.RecyclerView.Task.app.Example;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailViewFragment extends Fragment {

    TextView name,year,overview;
    private HashMap<String, ?> movie;
    String nameData;
    String releaseDateData;
    String overviewData;
    String imageUrlData;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null)
        {
           nameData=getArguments().get("Name").toString();
            releaseDateData=getArguments().get("ReleaseDate").toString();
            overviewData=getArguments().get("Overview").toString();
            imageUrlData=getArguments().get("ImageURL").toString();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_detail_view, container, false);

        name=(TextView)rootView.findViewById(R.id.movietitle);
        year=(TextView)rootView.findViewById(R.id.movieyear);
        overview=(TextView)rootView.findViewById(R.id.movieoverview);
        imageView=(ImageView)rootView.findViewById(R.id.img1);

        name.setText(nameData);
        year.setText(releaseDateData);
        overview.setText(overviewData);
        Picasso.with(getContext()).load(imageUrlData).into(imageView);

        return rootView;
    }

    public static DetailViewFragment newInstance(String name,String releaseDate, String overview,String image){
        DetailViewFragment detailViewFragment=new DetailViewFragment();
        Bundle args=new Bundle();
        args.putString("Name",name);
        args.putString("ReleaseDate",releaseDate);
        args.putString("Overview",overview);
        args.putString("ImageURL",image);
        detailViewFragment.setArguments(args);
        return detailViewFragment;
    }

}
