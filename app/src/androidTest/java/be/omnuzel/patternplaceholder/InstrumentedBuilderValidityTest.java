package be.omnuzel.patternplaceholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedBuilderValidityTest {

    @Test
    public void nonNullBitmap() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Bitmap bitmap = new PatternPlaceholder.Builder(appContext)
                .generate();

        assertTrue(bitmap != null);
    }

    @Test
    public void correctSizeBitmap() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        int DESIRED_WIDTH = 150;
        int DESIRED_HEIGHT = 150;

        Bitmap bitmap = new PatternPlaceholder.Builder(appContext)
                .setSize(DESIRED_WIDTH, DESIRED_HEIGHT)
                .generate();

        assertTrue(bitmap.getWidth() == DESIRED_WIDTH && bitmap.getHeight() == DESIRED_HEIGHT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void WithInvalidSize() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        Bitmap bitmap = new PatternPlaceholder.Builder(appContext)
                .setSize(-1, -1)
                .generate();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void withArrayOfZeroSize() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        Bitmap bitmap = new PatternPlaceholder.Builder(appContext)
                .setPalette(new int[]{})
                .generate();
    }


}
