package com.si5.camash.fruityco.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.si5.camash.fruityco.data.Aliment;
import com.si5.camash.fruityco.ui.activities.GameActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static List<Aliment> addRandomAliment(int nb){
        int i=0;
        List<Aliment> aliments=new ArrayList<Aliment>();
        Aliment aliment;
        while( i<nb ){
            aliment=initRandomAliment();
            if(!aliments.contains(aliment)){
                aliments.add(initRandomAliment());
                i++;
            }
        }

        return aliments;

    }

    public static Aliment initRandomAliment() {
        int nbrFruit = GameActivity.listFruits.length;
        int nbrLegume = GameActivity.listLegumes.length;
        Aliment aliment = null;
        Random random = new Random();
        int r = random.nextInt(2);
        switch (r) {
            case Constants.LEGUMES:
                aliment = new Aliment(GameActivity.listLegumes[random.nextInt(nbrLegume)], Constants.LEGUMES);
                break;
            case Constants.FRUIT:
                aliment = new Aliment(GameActivity.listFruits[random.nextInt(nbrFruit)], Constants.FRUIT);
                break;
        }
        return aliment;
    }
}
