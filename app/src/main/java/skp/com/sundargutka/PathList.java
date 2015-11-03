package skp.com.sundargutka;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sateesh Kumar on 10/24/2015.
 */
public class PathList extends ListActivity {

    private TextView mPathTextView;
    private List<String> mPathNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_path);


//        mPathTextView = (TextView) findViewById(R.id.path_name);
//        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/urdu.ttf");
//        mPathTextView.setTypeface(tf, Typeface.BOLD);

        mPathNameList = new ArrayList<String>();
        mPathNameList.add("جپ جی صاحب");

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
                R.layout.path_list_view, R.id.path_name, mPathNameList);

        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String selectedItem = (String) getListView().getItemAtPosition(position);

        Toast.makeText(PathList.this, "Selected Item: " + selectedItem, Toast.LENGTH_SHORT).show();
    }
}
