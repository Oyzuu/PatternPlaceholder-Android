package be.omnuzel.patternplaceholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.util.Log;
import android.widget.ImageView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by De Cooman Sammy on 11/11/16.
 */

public final class PatternGenerator {

    // Constants used for pattern selection
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            PatternType.SQUARES,
            PatternType.VERTICAL_LINES,
            PatternType.HORIZONTAL_LINES,
            PatternType.SCALES,
            PatternType.RANDOM_TRIANGLES})
    public @interface PatternType {
        int SQUARES = 0;
        int VERTICAL_LINES = 1;
        int HORIZONTAL_LINES = 2;
        int SCALES = 3;
        int RANDOM_TRIANGLES = 4;
    }

    // Constants for text alignment in drawText()
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            TextAlign.UPPER_LEFT,
            TextAlign.LOWER_LEFT,
            TextAlign.CENTER,
            TextAlign.UPPER_RIGHT,
            TextAlign.LOWER_RIGHT
    })
    public @interface TextAlign {
        int UPPER_LEFT = 0;
        int LOWER_LEFT = 1;
        int CENTER = 2;
        int UPPER_RIGHT = 3;
        int LOWER_RIGHT = 4;
    }

    /**
     * Generate a square-tiled Bitmap.
     *
     * @param width              the desired width for the bitmap
     * @param height             the desired height for the bitmap
     * @param tilesOnSmallerSide the number of tiles on the smaller side
     * @param palette            the palette used for color randomization, nullable
     * @param colorType          the type of color returned for color randomization,
     *                           RandomColor.ColorType.GRAY by default
     * @param seed               the seed applied to the Random object passed during generation
     * @return a bitmap with squares generated according to given parameters
     */
    private static Bitmap generateSquares(@IntRange(from = 1) final int width,
                                          @IntRange(from = 1) final int height,
                                          @IntRange(from = 1) final int tilesOnSmallerSide,
                                          @Nullable final int[] palette,
                                          @RandomColor.ColorType final int colorType,
                                          long seed) {

        final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();

        final Random random = getRandomForSeed(seed);
        int smallerSide = Math.min(width, height);
        final float SQUARE_SIZE = (float) smallerSide / tilesOnSmallerSide;

        for (int i = 0, j = 0; j < (height / SQUARE_SIZE); i++) {

            paint.setColor(RandomColor.get(palette, colorType, random));

            canvas.drawRect(i * SQUARE_SIZE, j * SQUARE_SIZE,
                    (i + 1) * SQUARE_SIZE, (j + 1) * SQUARE_SIZE, paint);

            if (i != 0 && i == (width / SQUARE_SIZE) - 1) {
                i = -1;
                j++;
            }
        }

        return bitmap;
    }

    /**
     * Generate a Bitmap of n horizontal lines.
     *
     * @param width         the desired width for the bitmap
     * @param height        the desired height for the bitmap
     * @param numberOfLines the number of horizontal lines
     * @param palette       the palette used for color randomization, nullable
     * @param colorType     the type of color returned for color randomization,
     *                      RandomColor.ColorType.GRAY by default
     * @param seed          the seed applied to the Random object passed during generation
     * @return a bitmap with horizontal lines generated according to given parameters
     */
    private static Bitmap generateHorizontalLines(@IntRange(from = 1) final int width,
                                                  @IntRange(from = 1) final int height,
                                                  @IntRange(from = 1) final int numberOfLines,
                                                  @Nullable final int[] palette,
                                                  @RandomColor.ColorType final int colorType,
                                                  long seed) {

        final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();
        Random random = getRandomForSeed(seed);

        final float LINE_SIZE = (float) height / numberOfLines;

        for (int i = 0; i < numberOfLines; i++) {
            paint.setColor(RandomColor.get(palette, colorType, random));
            canvas.drawRect(0, i * LINE_SIZE, width, (i + 1) * LINE_SIZE, paint);
        }

        return bitmap;
    }

    /**
     * Generate a Bitmap of n vertical lines.
     *
     * @param width         the desired width for the bitmap
     * @param height        the desired height for the bitmap
     * @param numberOfLines the number of vertical lines
     * @param palette       the palette used for color randomization, nullable
     * @param colorType     the type of color returned for color randomization,
     *                      RandomColor.ColorType.GRAY by default
     * @param seed          the seed applied to the Random object passed during generation
     * @return a bitmap with vertical lines generated according to given parameters
     */
    private static Bitmap generateVerticalLines(@IntRange(from = 1) final int width,
                                                @IntRange(from = 1) final int height,
                                                @IntRange(from = 1) final int numberOfLines,
                                                @Nullable final int[] palette,
                                                @RandomColor.ColorType final int colorType,
                                                long seed) {

        final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();
        Random random = getRandomForSeed(seed);

        final float LINE_SIZE = (float) width / numberOfLines;

        for (int i = 0; i < numberOfLines; i++) {
            paint.setColor(RandomColor.get(palette, colorType, random));
            canvas.drawRect(i * LINE_SIZE, 0, (i + 1) * LINE_SIZE, height, paint);
        }

        return bitmap;
    }

    /**
     * Generate a Bitmap with staggered rows of fish-like scales.
     *
     * @param width        the desired width for the bitmap
     * @param height       the desired height for the bitmap
     * @param scalesPerRow the number of scales per row
     * @param palette      the palette used for color randomization, nullable
     * @param colorType    the type of color returned for color randomization,
     *                     RandomColor.ColorType.GRAY by default
     * @param seed         the seed applied to the Random object passed during generation
     * @return a bitmap with staggered rows of scales generated according to given parameters
     */
    private static Bitmap generateFishScales(@IntRange(from = 1) final int width,
                                             @IntRange(from = 1) final int height,
                                             @IntRange(from = 1) final int scalesPerRow,
                                             @Nullable final int[] palette,
                                             @RandomColor.ColorType final int colorType,
                                             long seed) {
        final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        Random random = getRandomForSeed(seed);

        final float SCALE_SIZE = (float) width / scalesPerRow;
        final float RADIUS = SCALE_SIZE / 2;

        for (int i = 0, j = 0; j < (height / RADIUS) + 1; i += 2) {
            paint.setColor(RandomColor.get(palette, colorType, random));

            if (j % 2 == 0) {
                canvas.drawCircle((i + 1) * RADIUS, j * RADIUS, RADIUS, paint);
            } else {
                canvas.drawCircle(i * RADIUS, j * RADIUS, RADIUS, paint);
            }

            if (i * RADIUS > width) {
                i = -2;
                j++;
            }
        }

        return bitmap;
    }

    /**
     * Generate a Bitmap with random triangle strips
     *
     * @param width     the desired width for the bitmap
     * @param height    the desired height for the bitmap
     * @param rows      the number of rows
     * @param palette   the palette used for color randomization, nullable
     * @param colorType the type of color returned for color randomization,
     *                  RandomColor.ColorType.GRAY by default
     * @param seed      the seed applied to the Random object passed during generation
     * @return a bitmap with randomly generated triangles according to given parameters
     */
    private static Bitmap generateRandomTriangles(@IntRange(from = 1) final int width,
                                                  @IntRange(from = 1) final int height,
                                                  @IntRange(from = 1) final int rows,
                                                  @Nullable @Size(min = 1) final int[] palette,
                                                  @RandomColor.ColorType final int colorType,
                                                  long seed) {

        final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1);
        Path path = new Path();
        Random random = getRandomForSeed(seed);

        final Point[] VERTICES = getVerticesForRandomTriangles(width, height, rows, random);
//        Log.i("VERTICES-SIZE", "" + VERTICES.length);
//        Log.i("VERTICES", "" + Arrays.toString(VERTICES));
        final float STEP = (float) height / rows;
        final int COLUMNS = (int) (width / STEP);

        for (int i = 0, j = 0; j < rows; i++) {

            int a = i + j * (COLUMNS + 1);
            int b = a + 1;
            int c = a + (COLUMNS + 1);
            int d = c + 1;

//            Log.i("Point-Indices", String.format("a = %s, b = %s, c = %s, d = %s", a, b, c, d));

            Point pa = VERTICES[a];
            Point pb = VERTICES[b];
            Point pc = VERTICES[c];
            Point pd = VERTICES[d];

            setPathForPoints(path, pa, pb, pc, pa);
            paint.setColor(RandomColor.get(palette, colorType, random));
            canvas.drawPath(path, paint);

            setPathForPoints(path, pb, pc, pd, pb);
            paint.setColor(RandomColor.get(palette, colorType, random));
            canvas.drawPath(path, paint);

            if (i == COLUMNS - 1) {
                i = -1;
                j++;
            }
        }

        return bitmap;
    }

    /**
     * Set given path to represent a shape with given points.
     *
     * @param path   the path to setup
     * @param points variadic Point objects for shape generation
     */
    private static void setPathForPoints(@NonNull Path path, @Size(min = 3) Point... points) {
        path.reset();

        path.moveTo(points[0].getX(), points[0].getY());

        for (Point point : points) {
            path.lineTo(point.getX(), point.getY());
        }

        path.close();
    }

    /**
     * Generate vertices for RANDOM_TRIANGLES generation.
     * <p>
     * Except for corners, every coordinate is generated with a random margin on one or both axes.
     *
     * @param width  the desired width for the bitmap
     * @param height the desired height for the bitmap
     * @param rows   the number of horizontal triangle strips
     * @param random the random instance passed from generateRandomTriangles()
     * @return an array of coordinates used in generateRandomTriangles()
     */
    @SuppressLint("NewApi")
    private static Point[] getVerticesForRandomTriangles(@IntRange(from = 1) final int width,
                                                         @IntRange(from = 1) final int height,
                                                         @IntRange(from = 1) final int rows,
                                                         @NonNull Random random) {

        final float STEP = (float) height / rows;
        final int COLUMNS = (int) (width / STEP);
        final float MARGIN = STEP * .75f;
        final float HALF_MARGIN = MARGIN / 2;

//        Log.i("VERTICES-VALUES", String.format("Height / %s = Step = %s", rows, STEP));

        int arraySize = (rows + 1) * (COLUMNS + 1);
        Point[] vertices = new Point[arraySize];
        int n = 0;

//        Log.i("VERTICES-VALUES", String.format("rows = %s, columns = %s, array size = %s", rows, COLUMNS, arraySize));

        // First line
        vertices[n++] = new Point(0.f, 0.f);

        for (int i = 1; i < COLUMNS; i++) {
            vertices[n++] = new Point((i * STEP) - HALF_MARGIN + (random.nextFloat() * MARGIN), 0.f);
        }

        vertices[n++] = new Point(width, 0.f);

        // N-lines
        for (int i = 0, j = 1; j < rows; i++) {
            if (i == 0) {
                vertices[n++] = new Point(0.f, (j * STEP) - HALF_MARGIN + (random.nextFloat() * MARGIN));
            } else if (i == COLUMNS) {
                vertices[n++] = new Point(width, (j * STEP) - HALF_MARGIN + (random.nextFloat() * MARGIN));

                i = -1;
                j++;
            } else {
                vertices[n++] = new Point((i * STEP) - HALF_MARGIN + (random.nextFloat() * MARGIN),
                        (j * STEP) - HALF_MARGIN + (random.nextFloat() * MARGIN));
            }
        }

        // Last line
        vertices[n++] = new Point(0.f, height);

        for (int i = 1; i < COLUMNS; i++) {
            vertices[n++] = new Point((i * STEP) - HALF_MARGIN + (random.nextFloat() * MARGIN), height);
        }

        vertices[n] = new Point(width, height);

        return vertices;
    }

    /**
     * Draw text on given Bitmap.
     * <p>
     * Margin constant is set at 2.6% of the bitmap smallest side. (4dp for 150dp side)
     * Text size will be set accordingly to the bitmap width minus left and right margins.
     *
     * @param bitmap    the generated bitmap to draw on
     * @param text      the text to be drawn on given bitmap
     * @param textColor the text color
     * @param textAlign the text alignment
     * @return a bitmap previously generated by the builder with given text on it
     */
    public static Bitmap drawText(@NonNull Bitmap bitmap, String text,
                                  @ColorInt final int textColor, @TextAlign final int textAlign) {
        if (text.trim().equals("")) {
            return bitmap;
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(textColor);
        paint.setTypeface(Typeface.create(paint.getTypeface(), Typeface.BOLD));

        final int WIDTH = bitmap.getWidth();
        final int HEIGHT = bitmap.getHeight();
        final int SMALLER_SIDE = Math.min(WIDTH, HEIGHT);
        final float MARGIN = SMALLER_SIDE / 37.5f;

        if (text.length() < 3) {
            paint.setTextSize(SMALLER_SIDE / 2);
        } else {
            paint.setTextSize(SMALLER_SIDE / 3);
        }

        float textWidth = paint.measureText(text);
        if (textWidth + (MARGIN * 2) > WIDTH) {
            paint.setTextSize(paint.getTextSize() * ((WIDTH - MARGIN) / textWidth));
        }

        paint.setColor(textColor);

        // Setting x, y coordinates for TextAlign.CENTER as default
        final Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);
        float x = WIDTH / 2 - textBounds.exactCenterX();
        float y = HEIGHT / 2 - textBounds.exactCenterY();

        switch (textAlign) {
            case TextAlign.UPPER_LEFT:
                paint.setTextAlign(Paint.Align.LEFT);
                x = MARGIN;
                y = textBounds.height() + MARGIN;
                break;
            case TextAlign.LOWER_LEFT:
                paint.setTextAlign(Paint.Align.LEFT);
                x = MARGIN;
                y = HEIGHT - MARGIN;
                break;
            case TextAlign.CENTER:
                paint.setTextAlign(Paint.Align.LEFT);
                break;
            case TextAlign.UPPER_RIGHT:
                paint.setTextAlign(Paint.Align.RIGHT);
                x = WIDTH - MARGIN;
                y = textBounds.height() + MARGIN;
                break;
            case TextAlign.LOWER_RIGHT:
                paint.setTextAlign(Paint.Align.RIGHT);
                x = WIDTH - MARGIN;
                y = HEIGHT - MARGIN;
                break;
        }
        canvas.drawText(text, x, y, paint);

        return bitmap;
    }

    /**
     * Return a seeded Random instance or a null one according to given seed.
     *
     * @param seed the seed for random generation
     *             method will return a null Random object if seed equals zero
     * @return a seeded random or a null one
     */
    private static Random getRandomForSeed(long seed) {
        return new Random(seed);
    }

    private static class Point {

        private final float x;
        private final float y;

        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        float getX() {
            return x;
        }

        float getY() {
            return y;
        }

    }

    /**
     * A bitmap builder with various tiling patterns.
     * <p>
     * Example :
     * <pre>
     *     {@code
     * bitmap = new PatternGenerator.Builder()
     *     .setSize(MyContainer.width())
     *     .setTilesPerSide(4)
     *     .setPatternType(PatternGenerator.PatternType.RANDOM_TRIANGLES)
     *     .setColorGenerationType(RandomColor.ColorType.DARK_GRAY)
     *     .setText("SCALES")
     *     .setTextColor(Color.WHITE)
     *     .generate();
     *     }
     * </pre>
     */
    public static class Builder {

        private int width = 150;
        private int height = 150;
        private int tilesPerSide = 3;
        private int[] palette;
        private int colorGenerationType = RandomColor.ColorType.GREY;
        private int patternType = PatternType.SQUARES;
        private String text;
        private int textColor = Color.BLACK;
        private int textAlign = TextAlign.CENTER;
        private long seed = new Random().nextLong();

        private Bitmap bitmap;
        private final Context context;
        private boolean isCachingEnabled = false;

        public Builder(Context context) {
            this.context = context;
        }

        public PatternGenerator.Builder setSize(@IntRange(from = 1) int width,
                                                @IntRange(from = 1) int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public PatternGenerator.Builder setTilesPerSide(@IntRange(from = 1) int tilesPerSide) {
            this.tilesPerSide = tilesPerSide;
            return this;
        }

        public PatternGenerator.Builder setPalette(@Nullable @Size(min = 1) int[] palette) {
            this.palette = palette;
            return this;
        }

        public PatternGenerator.Builder setColorGenerationType(@RandomColor.ColorType int colorGenerationType) {
            this.colorGenerationType = colorGenerationType;
            return this;
        }

        public PatternGenerator.Builder setPatternType(@PatternType int patternType) {
            this.patternType = patternType;
            return this;
        }

        public PatternGenerator.Builder setSeed(long seed) {
            this.seed = seed;
            return this;
        }

        public PatternGenerator.Builder setText(@Nullable String text) {
            this.text = text;
            return this;
        }

        public PatternGenerator.Builder setTextColor(@ColorInt int textColor) {
            this.textColor = textColor;
            return this;
        }

        public PatternGenerator.Builder setTextAlign(@TextAlign int textAlign) {
            this.textAlign = textAlign;
            return this;
        }

        public PatternGenerator.Builder enableCache(boolean isCachingEnabled) {
            this.isCachingEnabled = isCachingEnabled;
            return this;
        }

        /**
         * Generate and return the bitmap synchronously.
         */
        public Bitmap generate() {
            long start = System.currentTimeMillis();

            BitmapFetcher fetcher = null;
            if (isCachingEnabled) {
                fetcher = new BitmapFetcher(context);

                bitmap = fetcher.getBitmapFromCache(hashCode() + "");
                if (bitmap != null) {
                    Log.i("Fetcher", "Read bitmap from cache");
                    Log.i("BUILDER-TIME", "" + (System.currentTimeMillis() - start));
                    return bitmap;
                }
            }

            switch (patternType) {
                case PatternType.SQUARES:
                    bitmap = generateSquares(width, height, tilesPerSide,
                            palette, colorGenerationType, seed);
                    break;
                case PatternType.VERTICAL_LINES:
                    bitmap = generateVerticalLines(width, height, tilesPerSide,
                            palette, colorGenerationType, seed);
                    break;
                case PatternType.HORIZONTAL_LINES:
                    bitmap = generateHorizontalLines(width, height, tilesPerSide,
                            palette, colorGenerationType, seed);
                    break;
                case PatternType.SCALES:
                    bitmap = generateFishScales(width, height, tilesPerSide,
                            palette, colorGenerationType, seed);
                    break;
                case PatternType.RANDOM_TRIANGLES:
                    bitmap = generateRandomTriangles(width, height, tilesPerSide,
                            palette, colorGenerationType, seed);
                    break;
                default:
                    bitmap = generateSquares(width, height, tilesPerSide,
                            palette, colorGenerationType, seed);
                    break;
            }

            if (text != null) {
                bitmap = drawText(bitmap, text, textColor, textAlign);
            }

            if (isCachingEnabled) {
                if (fetcher != null) {
                    fetcher.addBitmapToCache(hashCode() + "", bitmap);
                    Log.i("Fetcher", "Wrote bitmap on cache");
                }
            }

            Log.i("BUILDER-TIME", "" + (System.currentTimeMillis() - start));

            return bitmap;
        }

        /**
         * Generate the bitmap asynchronously.
         * <p>
         * onGenerated(Bitmap) will be called by the listener on completion.
         */
        public AsyncTask<Builder, Void, Bitmap> generate(PatternGeneratorAsyncListener listener) {
            return new PatternGeneratorAsyncTask(listener).execute(this);
        }

        /**
         * Generate the bitmap asynchronously and load it into provided ImageView.
         */
        public AsyncTask<Builder, Void, Bitmap> generate(ImageView imageView) {
            return new PatternGeneratorAsyncTask(imageView).execute(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Builder builder = (Builder) o;

            if (width != builder.width) return false;
            if (height != builder.height) return false;
            if (tilesPerSide != builder.tilesPerSide) return false;
            if (colorGenerationType != builder.colorGenerationType) return false;
            if (patternType != builder.patternType) return false;
            if (textColor != builder.textColor) return false;
            if (textAlign != builder.textAlign) return false;
            if (seed != builder.seed) return false;
            if (!Arrays.equals(palette, builder.palette)) return false;

            return text != null ? text.equals(builder.text) : builder.text == null;
        }

        @Override
        public int hashCode() {
            int result = width;
            result = 31 * result + height;
            result = 31 * result + tilesPerSide;
            result = 31 * result + (palette != null ? Arrays.hashCode(palette) : 0);
            result = 31 * result + colorGenerationType;
            result = 31 * result + patternType;
            result = 31 * result + (text != null ? text.hashCode() : 0);
            result = 31 * result + textColor;
            result = 31 * result + textAlign;
            result = 31 * result + (int) (seed ^ (seed >>> 32));
            return result;
        }
    }


    public interface PatternGeneratorAsyncListener {

        void onGenerated(Bitmap bitmap);

    }
}
