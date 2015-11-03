package skp.com.sundargutka;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Sateesh Kumar on 10/23/2015.
 */
//public class FontFitTextView extends TextView {
//
//    public FontFitTextView(Context context) {
//        super(context);
//        initialise();
//    }
//
//    public FontFitTextView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initialise();
//    }
//
//    private void initialise() {
//        mTestPaint = new Paint();
//        mTestPaint.set(this.getPaint());
//        //max size defaults to the initially specified text size unless it is too small
//    }
//
//    /* Re size the font so the specified text fits in the text box
//     * assuming the text box is the specified width.
//     */
//    private void refitText(String text, int textWidth)
//    {
//        if (textWidth <= 0)
//            return;
//        int targetWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
//        float hi = 100;
//        float lo = 2;
//        final float threshold = 0.5f; // How close we have to be
//
//        mTestPaint.set(this.getPaint());
//
//        while((hi - lo) > threshold) {
//            float size = (hi+lo)/2;
//            mTestPaint.setTextSize(size);
//            if(mTestPaint.measureText(text) >= targetWidth)
//                hi = size; // too big
//            else
//                lo = size; // too small
//        }
//        // Use lo so that we undershoot rather than overshoot
//        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, lo);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//    {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
//        int height = getMeasuredHeight();
//        refitText(this.getText().toString(), parentWidth);
//        this.setMeasuredDimension(parentWidth, height);
//    }
//
//    @Override
//    protected void onTextChanged(final CharSequence text, final int start, final int before, final int after) {
//        refitText(text.toString(), this.getWidth());
//    }
//
//    @Override
//    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
//        if (w != oldw) {
//            refitText(this.getText().toString(), w);
//        }
//    }
//
//    //Attributes
//    private Paint mTestPaint;
//}

public class FontFitTextView extends TextView {

    //Attributes
    private Paint testPaint;
    private float minTextSize;
    private float maxTextSize;

    public FontFitTextView(Context context) {
        super(context);
        initialise();
    }

    public FontFitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    private void initialise() {
        testPaint = new Paint();
        testPaint.set(this.getPaint());
        //max size defaults to the intially specified text size unless it is too small
        maxTextSize = this.getTextSize();
        if (maxTextSize < 11) {
            maxTextSize = 20;
        }
        minTextSize = 10;
    }

    /* Re size the font so the specified text fits in the text box
     * assuming the text box is the specified width.
     */
    private void refitText(String text, int textWidth) {
        if (textWidth > 0) {
            int availableWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
            float trySize = maxTextSize;

            testPaint.setTextSize(trySize);
            while ((trySize > minTextSize) && (testPaint.measureText(text) > availableWidth)) {
                trySize -= 1;
                if (trySize <= minTextSize) {
                    trySize = minTextSize;
                    break;
                }
                testPaint.setTextSize(trySize);
            }

            this.setTextSize(trySize);
        }
    }

    @Override
    protected void onTextChanged(final CharSequence text, final int start, final int before, final int after) {
        refitText(text.toString(), this.getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            refitText(this.getText().toString(), w);
        }
    }

    //Getters and Setters
    public float getMinTextSize() {
        return minTextSize;
    }

    public void setMinTextSize(int minTextSize) {
        this.minTextSize = minTextSize;
    }

    public float getMaxTextSize() {
        return maxTextSize;
    }

    public void setMaxTextSize(int minTextSize) {
        this.maxTextSize = minTextSize;
    }

}