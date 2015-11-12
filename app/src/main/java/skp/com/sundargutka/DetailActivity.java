package skp.com.sundargutka;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import skp.com.sundargutka.data.DatabaseContract;
import skp.com.sundargutka.data.DatabaseHelper;

/**
 * Created by Sateesh.Kumar on 11/12/2015.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String LIST_ID = "skp.com.sundargutka.list.id";

    private TextView mDetailTextView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDetailTextView = (TextView) findViewById(R.id.detail_textview);
        if (Build.VERSION.SDK_INT > 16)
            mDetailTextView.setTextDirection(View.TEXT_DIRECTION_RTL);

        else
        {
            //TODO: Set Right To Left Text Direction for SDK Version <=16
        }

        Bundle b = getIntent().getExtras();
        int id = b.getInt(LIST_ID, 0);

        if(id!=0)
        {
            dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            StringBuilder builder = new StringBuilder();

            Cursor c = db.rawQuery("SELECT * FROM "+ DatabaseContract.DetailEntry.TABLE_NAME+" WHERE "+ DatabaseContract.DetailEntry._ID+" = "+id, null);
            if(c.moveToFirst())
            {
                do {
                    builder.append(c.getString(c.getColumnIndex(DatabaseContract.DetailEntry.COLUMN_SCH_TEXT)));
                }while (c.moveToNext());
            }

            mDetailTextView.setText(builder.toString());
        }
    }
}
