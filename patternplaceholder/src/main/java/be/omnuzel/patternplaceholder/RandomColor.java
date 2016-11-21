package be.omnuzel.patternplaceholder;

import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;

/**
 * Created by De Cooman Sammy on 11/11/16.
 */

public final class RandomColor {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            ColorType.ALL,
            ColorType.LIGHT_GREY,
            ColorType.MEDIUM_GREY,
            ColorType.DARK_GREY,
            ColorType.GREY})
    public @interface ColorType {
        int ALL = 0;
        int LIGHT_GREY = 1;
        int MEDIUM_GREY = 2;
        int DARK_GREY = 3;
        int GREY = 4;
    }

    /**
     * Return a random color from provided palette or a random color generated for a given ColorType.
     * <p>
     * Color type default is ColorType.GREY
     *
     * @param palette   an integer array with color codes
     * @param colorType the type of color returned for color randomization, ColorType.GREY by default
     * @param random    the Random instance passed from PatternPlaceholder, non null
     * @return a randomly chosen color from given palette or a randomly generated color if palette is null
     */
    public static int get(@Nullable int[] palette, @ColorType int colorType, @NonNull Random random) {
        if (palette != null) {
            return palette[(int) (random.nextFloat() * palette.length)];
        } else {
            switch (colorType) {
                case ColorType.ALL:
                    return getColor(random);
                case ColorType.LIGHT_GREY:
                    return getLightGrey(random);
                case ColorType.MEDIUM_GREY:
                    return getMediumGrey(random);
                case ColorType.DARK_GREY:
                    return getRandomDarkGrey(random);
                case ColorType.GREY:
                    return getGrey(random);
                default:
                    return getGrey(random);
            }
        }
    }

    /**
     * Generate a random color
     *
     * @param random the Random instance passed from PatternPlaceholder, non null
     * @return the randomly generated color
     */
    public static int getColor(@NonNull Random random) {

        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        return Color.rgb(red, green, blue);
    }

    /**
     * Generate a random grey of range 0-255
     *
     * @param random the Random instance passed from PatternPlaceholder, non null
     * @return the randomly generated grey
     */
    public static int getGrey(@NonNull Random random) {

        int value = random.nextInt(256);
        return Color.rgb(value, value, value);
    }

    /**
     * Generate a random grey of range 155-255
     *
     * @param random the Random instance passed from PatternPlaceholder, non null
     * @return the randomly generated grey
     */
    public static int getLightGrey(@NonNull Random random) {

        int value = random.nextInt(101) + 155;
        return Color.rgb(value, value, value);
    }

    /**
     * Generate a random grey of range 100-200
     *
     * @param random the Random instance passed from PatternPlaceholder, non null
     * @return the randomly generated grey
     */
    public static int getMediumGrey(@NonNull Random random) {

        int value = random.nextInt(101) + 100;
        return Color.rgb(value, value, value);
    }

    /**
     * Generate a random grey of range 0-100
     *
     * @param random the Random instance passed from PatternPlaceholder, non null
     * @return the randomly generated grey
     */
    public static int getRandomDarkGrey(@NonNull Random random) {

        int value = random.nextInt(101);
        return Color.rgb(value, value, value);
    }

}
