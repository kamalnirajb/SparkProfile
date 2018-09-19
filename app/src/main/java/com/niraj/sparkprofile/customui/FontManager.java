package com.niraj.sparkprofile.customui;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by nirworld on 5/25/17.
 */
public class FontManager {

    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf",
            MyRiadProRegular = ROOT + "Myriad Pro Regular.ttf",
            MyriadProBold = ROOT + "MyriadPro-Bold.otf",
            MyriadProSemibold = ROOT + "MyriadProSemibold.otf",
            PTSansRegular = ROOT + "PT Sans Regular.ttf",
            PTSansNarrow = ROOT + "pt-sans-narrow.ttf",
            PTSnsBold = ROOT + "Ptansbold.ttf",
            PTSansItalic = ROOT + "Ptsansitalic.ttf",
            WorkSansLight = ROOT + "WorkSans-Light.ttf",
            WorkSansRegular = ROOT + "WorkSans-Regular.ttf";


    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

}
