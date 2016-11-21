package be.omnuzel.patternplaceholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.Environment.isExternalStorageRemovable;

/**
 * Created by De Cooman Sammy on 18/11/16.
 */

public class BitmapFetcher {

    private final File cacheDir;

    public BitmapFetcher(Context context) {
        Log.i("BitmapFetcher init", "");

        cacheDir = getCacheDirectory(context, "images");
        createImageCacheDir();
    }

    public void addBitmapToCache(String fileName, Bitmap bitmap) {
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(new File(cacheDir, fileName + ".png"));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmapFromCache(String fileName) {
        BufferedInputStream stream = null;
        try {
            stream = new BufferedInputStream(new FileInputStream(new File(cacheDir, fileName + ".png")));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            stream.close();
            return bitmap;
        } catch (IOException e) {
            Log.i("BITMAP-FETCHER", "File not found : " + fileName + ".png");
            return null;
        }
    }

    private File getCacheDirectory(Context context, String uniqueName) {

        final String cachePath =
                Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                        !isExternalStorageRemovable() ? context.getExternalCacheDir().getPath() :
                        context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    private boolean createImageCacheDir() {
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }

        return true;
    }

}
