package be.omnuzel.patternplaceholder;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.ImageView;

/**
 * Created by De Cooman Sammy on 18/11/16.
 */

public class PatternGeneratorAsyncTask extends AsyncTask<PatternGenerator.Builder, Void, Bitmap> {

    private PatternGenerator.PatternGeneratorAsyncListener callback;
    private ImageView imageView;

    public PatternGeneratorAsyncTask(@NonNull PatternGenerator.PatternGeneratorAsyncListener callback) {
        this.callback = callback;
    }

    public PatternGeneratorAsyncTask(@NonNull ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(PatternGenerator.Builder... builders) {
        return builders[0].generate();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            callback.onGenerated(bitmap);
        }
    }
}
