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


    public static Drawable getRes(Context context, String variableName, int type) {
        String name = variableName;
        name = name.replace("ma iss", "mais");
        name = name.replace(' ', '_');
        name = name.replace('ê', 'e');
        name = name.replace('è', 'e');
        name = name.replace('â', 'a');
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(((type==Constants.FRUIT)?"fruit_": "legume_")+name, "drawable",
                context.getPackageName());
        return resources.getDrawable(resourceId);

    }

    public static int getResId(Context context, String variableName, int type) {
        String name = variableName;
        name = name.replace("ma iss", "mais");
        name = name.replace(' ', '_');
        name = name.replace('ê', 'e');
        name = name.replace('è', 'e');
        name = name.replace('â', 'a');
        Resources resources = context.getResources();
        return resources.getIdentifier(((type==Constants.FRUIT)?"fruit_": "legume_")+name, "drawable",
                context.getPackageName());

    }



    public static List<Aliment> addRandomAliment(int nb){
        int i=0;
        List<Aliment> aliments=new ArrayList<Aliment>();
        Aliment aliment;
        while( i<nb ){
            aliment=initRandomAliment();
            if(!contains(aliments,aliment)){
                aliments.add(aliment);
                i++;
            }
        }

        return aliments;

    }

    private static boolean contains(List<Aliment> aliments, Aliment aliment){
        for(Aliment a:aliments){
            if(a.getName().equals(aliment.getName())) return true;

        }
        return false;
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

    public static List<Integer> getAllRes(Context context, int type){
        List<Integer> reslt=new ArrayList<Integer>();
        if(type==Constants.FRUIT){
            for(int i=0;i<GameActivity.listFruits.length;i++){
                reslt.add(getResId(context, GameActivity.listFruits[i], Constants.FRUIT));
            }
        }else{
            for(int i=0;i<GameActivity.listLegumes.length;i++){
                reslt.add(getResId(context, GameActivity.listLegumes[i], Constants.LEGUMES));
            }
        }

        return reslt;
    }
}
