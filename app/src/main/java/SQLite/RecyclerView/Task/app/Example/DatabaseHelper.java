package SQLite.RecyclerView.Task.app.Example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manojkulkarni on 11/6/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE = "database.db";
    public static String TABLE ="mytable";
    public static String NAME ="name";
    public static int MOVIEID;
    public static String OVERVIEW ="overview";
    public static String RELEASEDATE ="releasedate";
    public static String GENRE ="genre";
    public static String IMAGEURL="image";

    String createTable;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable = "CREATE TABLE "+TABLE+"("+NAME+ " Text, "+OVERVIEW+ " Text, "+RELEASEDATE+ " Text, "+GENRE+ " Text, "+IMAGEURL+ " Text);";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE+" ;");
    }

    public void insertdata(String name,String overview ,String releaseDate,String genre,String image){
        System.out.print("Hello "+createTable);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();


        contentValues.put(NAME, name);
        contentValues.put(OVERVIEW, overview);
        contentValues.put(RELEASEDATE,releaseDate);
        contentValues.put(GENRE,genre);
        contentValues.put(IMAGEURL,image);
        db.insert(TABLE,null,contentValues);


    }

    public List<MovieData> getdata(){
        // DataModel dataModel = new DataModel();
        List<MovieData> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        MovieData dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new MovieData();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow("overview"));
            String releasedate = cursor.getString(cursor.getColumnIndexOrThrow("releasedate"));
            String genre = cursor.getString(cursor.getColumnIndexOrThrow("genre"));
            String image= cursor.getString(cursor.getColumnIndexOrThrow("image"));
            dataModel.setName(name);
            dataModel.setOverview(overview);
            dataModel.setReleaseDate(releasedate);
            dataModel.setGenre(genre);
            dataModel.setImage(image);
            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        for (MovieData mo:data ) {

            Log.i("Hellomo",""+mo.getName());
        }

        //

        return data;
    }

    public void deleteTable( ){

        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE);

    }
}
