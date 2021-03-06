package skp.com.sundargutka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import skp.com.sundargutka.adapter.RecyclerViewAdapter;
import skp.com.sundargutka.data.DatabaseContract;
import skp.com.sundargutka.data.DatabaseHelper;
import skp.com.sundargutka.listener.RecyclerTouchListener;

import static skp.com.sundargutka.data.DatabaseContract.ChapterEntry.TABLE_NAME;

/**
 * Created by Sateesh Kumar on 10/24/2015.
 */
public class PathList extends AppCompatActivity {

    private TextView mPathTextView;
    private ArrayList<String> mPathNameList;
    private RecyclerView mRecyclerView;

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_path);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(cursor.moveToFirst())
        {
            mPathNameList = new ArrayList<>();
            do{
                mPathNameList.add(cursor.getString(cursor.getColumnIndex(DatabaseContract.ChapterEntry.COLUMN_CHAPTER_NAME)));
            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new RecyclerViewAdapter(mPathNameList, this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(PathList.this, DetailActivity.class);
                i.putExtra(DetailActivity.LIST_ID, position+1);
                startActivity(i);
            }
        }));
    }
}
