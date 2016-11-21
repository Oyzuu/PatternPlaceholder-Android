package be.omnuzel.patternplaceholder;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.ImageView;

/**
 * Created by De Cooman Sammy on 18/11/16.
 */

public class PatternPlaceholderAsyncTask extends AsyncTask<PatternPlaceholder.Builder, Void, Bitmap> {

    private PatternPlaceholder.PatternGeneratorAsyncListener callback;
    private ImageView imageView;

    public PatternPlaceholderAsyncTask(@NonNull PatternPlaceholder.PatternGeneratorAsyncListener callback) {
        this.callback = callback;
    }

    public PatternPlaceholderAsyncTask(@NonNull ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(PatternPlaceholder.Builder... builders) {
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
