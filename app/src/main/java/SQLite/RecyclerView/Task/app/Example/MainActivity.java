package SQLite.RecyclerView.Task.app.Example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<MovieData> movieDataList;
    MovieData movieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertData();
        movieDataList=new ArrayList<MovieData>();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        databaseHelper=new DatabaseHelper(this);
        movieDataList=databaseHelper.getdata();
        myAdapter=new MyAdapter(movieDataList,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


        //Load detail view here
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                movieData=movieDataList.get(position);
                Log.d("Hello", "onItemClick: "+movieData.getName());
                String name=movieData.getName();
                String releaseDate=movieData.getReleaseDate();
                String overview=movieData.getOverview();
                String image=movieData.getImage();
                android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.detail_view,DetailViewFragment.newInstance(name,releaseDate,overview,image));
                ft.addToBackStack(null);
                ft.commit();


            }
        });
    }


    public void insertData(){
        databaseHelper=new DatabaseHelper(this);
        databaseHelper.deleteTable();
        databaseHelper.insertdata("Baazigar","My favorite","1993","Thriller","http://image3.mouthshut.com/images/imagesp/925008075s.jpg");
    }



}
