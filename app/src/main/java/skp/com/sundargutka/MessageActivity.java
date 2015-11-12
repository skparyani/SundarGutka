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
        else
        {
            //TODO: Set Right To Left Text Direction for SDK Version <=16
        }

        mTextView.setText(getMessage());

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

    private String getMessage()
    {
        String message = getString(R.string.m1);
        message += getString(R.string.m2);
        message += getString(R.string.m3);
        message += getString(R.string.m4);
        message += getString(R.string.m5);
        message += getString(R.string.m6);
        message += getString(R.string.m7);
        message += getString(R.string.m8);
        message += getString(R.string.m9);
        message += getString(R.string.m10);
        message += getString(R.string.m11);
        message += getString(R.string.m12);

        return message;
    }
}
