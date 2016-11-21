package be.omnuzel.patternplaceholderapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.test.mock.MockContext;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Bitmap bitmap;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        Context context = new MockContext();
        int DESIRED_WIDTH = 150;
        int DESIRED_HEIGHT = 150;

        bitmap = new PatternPlaceholder.Builder(context)
                .setSize(DESIRED_WIDTH, DESIRED_HEIGHT)
                .generate();

        assertTrue(bitmap.getWidth() == DESIRED_WIDTH && bitmap.getHeight() == DESIRED_HEIGHT);
    }
}