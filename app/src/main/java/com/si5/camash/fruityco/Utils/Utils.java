package com.si5.camash.fruityco.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by benjamin on 13/10/2014.
 */
public class Utils {


    public static Drawable getResId(Context context, String variableName, int type) {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(((type==Constants.FRUIT)?"fruit_": "legume_")+variableName, "drawable",
                context.getPackageName());
        return resources.getDrawable(resourceId);

    }
}
