package be.omnuzel.patternplaceholder;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by De Cooman Sammy on 16/11/16.
 * <p>
 * All the material colors easily accessible.
 *
 * @see <a href="https://material.google.com/style/color.html">https://material.google.com/style/color.html</a>
 */
public final class MaterialColor {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            Color.RED,
            Color.PINK,
            Color.PURPLE,
            Color.DEEP_PURPLE,
            Color.INDIGO,
            Color.BLUE,
            Color.LIGHT_BLUE,
            Color.CYAN,
            Color.TEAL,
            Color.GREEN,
            Color.LIGHT_GREEN,
            Color.LIME,
            Color.YELLOW,
            Color.AMBER,
            Color.ORANGE,
            Color.DEEP_ORANGE,
            Color.BROWN,
            Color.GREY,
            Color.BLUE_GREY})
    public @interface Color {
        int RED = 0;
        int PINK = 1;
        int PURPLE = 2;
        int DEEP_PURPLE = 3;
        int INDIGO = 4;
        int BLUE = 5;
        int LIGHT_BLUE = 6;
        int CYAN = 7;
        int TEAL = 8;
        int GREEN = 9;
        int LIGHT_GREEN = 10;
        int LIME = 11;
        int YELLOW = 12;
        int AMBER = 13;
        int ORANGE = 14;
        int DEEP_ORANGE = 15;
        int BROWN = 16;
        int GREY = 17;
        int BLUE_GREY = 18;
    }

    public static int[] getSwatch(@Color int swatchName) {
        switch (swatchName) {
            case Color.RED:
                return new int[]{
                        0xFFFFEBEE, 0xFFFFCDD2, 0xFFEF9A9A, 0xFFE57373, 0xFFEF5350,
                        0xFFF44336, 0xFFE53935, 0xFFD32F2F, 0xFFC62828, 0xFFB71C1C
                };
            case Color.PINK:
                return new int[]{
                        0xFFFCE4EC, 0xFFF8BBD0, 0xFFF48FB1, 0xFFF06292, 0xFFEC407A,
                        0xFFE91E63, 0xFFD81B60, 0xFFC2185B, 0xFFAD1457, 0xFF880E4F
                };
            case Color.PURPLE:
                return new int[]{
                        0xFFF3E5F5, 0xFFE1BEE7, 0xFFCE93D8, 0xFFBA68C8, 0xFFAB47BC,
                        0xFF9C27B0, 0xFF8E24AA, 0xFF7B1FA2, 0xFF6A1B9A, 0xFF4A148C
                };
            case Color.DEEP_PURPLE:
                return new int[]{
                        0xFFEDE7F6, 0xFFD1C4E9, 0xFFB39DDB, 0xFF9575CD, 0xFF7E57C2,
                        0xFF673AB7, 0xFF5E35B1, 0xFF512DA8, 0xFF4527A0, 0xFF311B92
                };
            case Color.INDIGO:
                return new int[]{
                        0xFFE8EAF6, 0xFFC5CAE9, 0xFF9FA8DA, 0xFF7986CB, 0xFF5C6BC0,
                        0xFF3F51B5, 0xFF3949AB, 0xFF303F9F, 0xFF283593, 0xFF1A237E
                };
            case Color.BLUE:
                return new int[]{
                        0xFFE3F2FD, 0xFFBBDEFB, 0xFF90CAF9, 0xFF64B5F6, 0xFF42A5F5,
                        0xFF2196F3, 0xFF1E88E5, 0xFF1976D2, 0xFF1565C0, 0xFF0D47A1
                };
            case Color.LIGHT_BLUE:
                return new int[]{
                        0xFFE1F5FE, 0xFFB3E5FC, 0xFF81D4FA, 0xFF4FC3F7, 0xFF29B6F6,
                        0xFF03A9F4, 0xFF039BE5, 0xFF0288D1, 0xFF0277BD, 0xFF01579B
                };
            case Color.CYAN:
                return new int[]{
                        0xFFE0F7FA, 0xFFB2EBF2, 0xFF80DEEA, 0xFF4DD0E1, 0xFF26C6DA,
                        0xFF00BCD4, 0xFF00ACC1, 0xFF0097A7, 0xFF00838F, 0xFF006064
                };
            case Color.TEAL:
                return new int[]{
                        0xFFE0F2F1, 0xFFB2DFDB, 0xFF80CBC4, 0xFF4DB6AC, 0xFF26A69A,
                        0xFF009688, 0xFF00897B, 0xFF00796B, 0xFF00695C, 0xFF004D40
                };
            case Color.GREEN:
                return new int[]{
                        0xFFE8F5E9, 0xFFC8E6C9, 0xFFA5D6A7, 0xFF81C784, 0xFF66BB6A,
                        0xFF4CAF50, 0xFF43A047, 0xFF388E3C, 0xFF2E7D32, 0xFF1B5E20
                };
            case Color.LIGHT_GREEN:
                return new int[]{
                        0xFFF1F8E9, 0xFFDCEDC8, 0xFFC5E1A5, 0xFFAED581, 0xFF9CCC65,
                        0xFF8BC34A, 0xFF7CB342, 0xFF689F38, 0xFF558B2F, 0xFF33691E
                };
            case Color.LIME:
                return new int[]{
                        0xFFF9FBE7, 0xFFF0F4C3, 0xFFE6EE9C, 0xFFDCE775, 0xFFD4E157,
                        0xFFCDDC39, 0xFFC0CA33, 0xFFAFB42B, 0xFF9E9D24, 0xFF827717
                };
            case Color.YELLOW:
                return new int[]{
                        0xFFFFFDE7, 0xFFFFF9C4, 0xFFFFF59D, 0xFFFFF176, 0xFFFFEE58,
                        0xFFFFEB3B, 0xFFFDD835, 0xFFFBC02D, 0xFFF9A825, 0xFFF57F17
                };
            case Color.AMBER:
                return new int[]{
                        0xFFFFF8E1, 0xFFFFECB3, 0xFFFFE082, 0xFFFFD54F, 0xFFFFCA28,
                        0xFFFFC107, 0xFFFFB300, 0xFFFFA000, 0xFFFF8F00, 0xFFFF6F00
                };
            case Color.ORANGE:
                return new int[]{
                        0xFFFFF3E0, 0xFFFFE0B2, 0xFFFFCC80, 0xFFFFB74D, 0xFFFFA726,
                        0xFFFF9800, 0xFFFB8C00, 0xFFF57C00, 0xFFEF6C00, 0xFFE65100
                };
            case Color.DEEP_ORANGE:
                return new int[]{
                        0xFFFBE9E7, 0xFFFFCCBC, 0xFFFFAB91, 0xFFFF8A65, 0xFFFF7043,
                        0xFFFF5722, 0xFFF4511E, 0xFFE64A19, 0xFFD84315, 0xFFBF360C
                };
            case Color.BROWN:
                return new int[]{
                        0xFFEFEBE9, 0xFFD7CCC8, 0xFFBCAAA4, 0xFFA1887F, 0xFF8D6E63,
                        0xFF795548, 0xFF6D4C41, 0xFF5D4037, 0xFF4E342E, 0xFF3E2723
                };
            case Color.GREY:
                return new int[]{
                        0xFFFAFAFA, 0xFFF5F5F5, 0xFFEEEEEE, 0xFFE0E0E0, 0xFFBDBDBD,
                        0xFF9E9E9E, 0xFF757575, 0xFF616161, 0xFF424242, 0xFF212121
                };
            case Color.BLUE_GREY:
                return new int[]{
                        0xFFECEFF1, 0xFFCFD8DC, 0xFFB0BEC5, 0xFF90A4AE, 0xFF78909C,
                        0xFF607D8B, 0xFF546E7A, 0xFF455A64, 0xFF37474F, 0xFF263238
                };
            default:
                return new int[]{
                        0xFFFAFAFA, 0xFFF5F5F5, 0xFFEEEEEE, 0xFFE0E0E0, 0xFFBDBDBD,
                        0xFF9E9E9E, 0xFF757575, 0xFF616161, 0xFF424242, 0xFF212121
                };
        }
    }

    public static int getColorForValue(@MaterialColor.Color int colorName, int value) {
        switch (value) {
            case 50:
                return getSwatch(colorName)[0];
            case 100:
                return getSwatch(colorName)[1];
            case 200:
                return getSwatch(colorName)[2];
            case 300:
                return getSwatch(colorName)[3];
            case 400:
                return getSwatch(colorName)[4];
            case 500:
                return getSwatch(colorName)[5];
            case 600:
                return getSwatch(colorName)[6];
            case 700:
                return getSwatch(colorName)[7];
            case 800:
                return getSwatch(colorName)[8];
            case 900:
                return getSwatch(colorName)[9];
            default:
                return getSwatch(colorName)[5];
        }
    }

}
