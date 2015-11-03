package skp.com.sundargutka;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sateesh Kumar on 10/17/2015.
 */
public class MessageActivity extends AppCompatActivity {

    private Button mLauchList;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);

        mLauchList = (Button) findViewById(R.id.ok_button);
        mTextView = (TextView) findViewById(R.id.textview);

        if (Build.VERSION.SDK_INT > 16)
            mTextView.setTextDirection(View.TEXT_DIRECTION_RTL);


        mTextView.setText(getString(R.string.message_0));

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/urdu.ttf");
        mLauchList.setTypeface(tf, Typeface.BOLD);
        mTextView.setTypeface(tf, Typeface.BOLD);

        mLauchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessageActivity.this, PathList.class));
                finish();
            }
        });
    }
}
