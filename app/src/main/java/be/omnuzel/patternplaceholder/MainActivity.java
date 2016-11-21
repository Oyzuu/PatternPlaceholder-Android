package be.omnuzel.patternplaceholder;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView1, mImageView2, mImageView3, mImageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView1 = (ImageView) findViewById(R.id.image_view_1);
        mImageView2 = (ImageView) findViewById(R.id.image_view_2);
        mImageView3 = (ImageView) findViewById(R.id.image_view_3);
        mImageView4 = (ImageView) findViewById(R.id.image_view_4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Bitmap bitmap;

        int patternType = PatternPlaceholder.PatternType.SCALES;
        int[] palette = new int[]{
                MaterialColor.getColorForValue(MaterialColor.Color.BLUE, 500),
                MaterialColor.getColorForValue(MaterialColor.Color.BLUE, 200),
                MaterialColor.getColorForValue(MaterialColor.Color.BLUE, 800)
        };

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_main);

        new PatternPlaceholder.Builder(this)
                .setTilesPerSide(-6)
                .generate(mImageView1);

        new PatternPlaceholder.Builder(this).generate(mImageView2);

        PatternPlaceholder.Builder builder3 = new PatternPlaceholder.Builder(this);
        builder3.generate(mImageView3);

        PatternPlaceholder.Builder builder4 = new PatternPlaceholder.Builder(this);
        builder4.generate(mImageView4);

        return super.onTouchEvent(event);
    }

}
