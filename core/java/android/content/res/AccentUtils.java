package android.content.res;

import android.graphics.Color;
import android.os.SystemProperties;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @hide
 */
public class AccentUtils {
    private static final String TAG = "AccentUtils";

    private static final ArrayList<String> accentResources = new ArrayList<>(
            Arrays.asList("accent_color_red",
                    "accent_device_default",
                    "accent_device_default_dark",
                    "accent_device_default_light",
                    "accent_material_dark",
                    "accent_material_light",
                    "alert_dialog_color_accent_dark",
                    "alert_dialog_color_accent_light",
                    "avatar_bg_red",
                    "colorAccent",
                    "dialer_theme_color",
                    "dialer_theme_color_20pct",
                    "dialer_theme_color_dark",
                    "dismiss_all_icon_color",
                    "folder_indicator_color",
                    "material_pixel_blue_bright",
                    "material_pixel_blue_dark",
                    "omni_color4",
                    "omni_color5",
                    "oneplus_accent_color",
                    "oneplus_accent_text_color",
                    "settingsHeaderColor",
                    "settings_accent_color",
                    "user_icon_1",
                    "user_icon_2",
                    "user_icon_3",
                    "user_icon_4",
                    "user_icon_5",
                    "user_icon_6",
                    "user_icon_7",
                    "user_icon_8"));

    private static final String ACCENT_COLOR_PROP = "persist.sys.theme.accentcolor";

    static boolean isResourceAccent(String resName) {
        return accentResources.stream().anyMatch(resName::contains);
    }

    public static int getNewAccentColor(int defaultColor) {
        return getAccentColor(defaultColor, ACCENT_COLOR_PROP);
    }

    private static int getAccentColor(int defaultColor, String property) {
        try {
            String colorValue = SystemProperties.get(property, "-1");
            return colorValue.equals("-1") || colorValue.equals("ff725aff")
                    ? defaultColor : Color.parseColor("#" + colorValue);
        } catch (Exception e) {
            Log.e(TAG, "Failed to set accent: " + e.getMessage() +
                    "\nSetting default: " + defaultColor);
            return defaultColor;
        }
    }
}
