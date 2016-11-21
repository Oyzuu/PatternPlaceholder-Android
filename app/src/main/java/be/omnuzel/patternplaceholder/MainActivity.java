package be.omnuzel.patternplaceholder;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

        int patternType = PatternGenerator.PatternType.SCALES;
        int[] palette = new int[]{
                MaterialColor.getColorForValue(MaterialColor.Color.BLUE, 500),
                MaterialColor.getColorForValue(MaterialColor.Color.BLUE, 200),
                MaterialColor.getColorForValue(MaterialColor.Color.BLUE, 800)
        };

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_main);

        PatternGenerator.Builder builder = new PatternGenerator.Builder(this)
                .setSize(150, 300)
                .setPatternType(PatternGenerator.PatternType.SCALES)
                .setTilesPerSide(4)
                .setPalette(MaterialColor.getSwatch(MaterialColor.Color.TEAL));
        Log.i("Builder-hash", "" + builder.hashCode());
        builder.generate(mImageView1);

        PatternGenerator.Builder builder2 = new PatternGenerator.Builder(this)
                .setSize(150, 150)
                .setPatternType(PatternGenerator.PatternType.RANDOM_TRIANGLES)
                .setTilesPerSide(4)
                .setPalette(MaterialColor.getSwatch(MaterialColor.Color.LIME))
                .setText("User")
                .setTextAlign(PatternGenerator.TextAlign.CENTER);
        Log.i("Builder2-hash", "" + builder2.hashCode());
        builder2.generate(mImageView2);

        PatternGenerator.Builder builder3 = new PatternGenerator.Builder(this)
                .setSize(300, 150)
                .setPatternType(PatternGenerator.PatternType.SQUARES)
                .setTilesPerSide(8)
                .setPalette(MaterialColor.getSwatch(MaterialColor.Color.DEEP_ORANGE));
        Log.i("Builder3-hash", "" + builder3.hashCode());
        builder3.generate(mImageView3);

        PatternGenerator.Builder builder4 = new PatternGenerator.Builder(this)
                .setSize(150, 150)
                .setPatternType(PatternGenerator.PatternType.VERTICAL_LINES)
                .setTilesPerSide(10)
                .setPalette(MaterialColor.getSwatch(MaterialColor.Color.PINK));
        Log.i("Builder3-hash", "" + builder3.hashCode());
        builder4.generate(mImageView4);

        return super.onTouchEvent(event);
    }

}
