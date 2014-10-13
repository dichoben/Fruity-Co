package com.si5.camash.fruityco.ui.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.si5.camash.fruityco.R;
import com.si5.camash.fruityco.data.Vegetable;
import com.si5.camash.fruityco.ui.activities.GameActivity;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Random;


public class Theme1 extends Fragment implements View.OnClickListener {

    final int LEGUMES=0;
    final int FRUIT=1;
    private TextToSpeech ttobj;
    private ImageView imgMain;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    private Vegetable myVegetable;
    private Vegetable vegetable2;
    private Vegetable vegetable3;





    public static Theme1 newInstance() {
        Theme1 fragment = new Theme1();
        return fragment;
    }
    public Theme1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        myVegetable = initRandomVegetable();
        vegetable2 = initRandomVegetable();
        vegetable3 = initRandomVegetable();

        ttobj=new TextToSpeech(getActivity(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            ttobj.setLanguage(Locale.CANADA_FRENCH);
                        }
                    }
                });
        ttobj.setLanguage(Locale.FRENCH);
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_theme1, container, false);
        findViews(v);
        return v;
    }


    @Override
    public void onPause(){
        if(ttobj !=null){
            ttobj.stop();
            ttobj.shutdown();
        }
        super.onPause();
    }


    private void findViews(View v) {
        imgMain = (ImageView)v.findViewById( R.id.imgMain );
        img1 = (ImageView)v.findViewById( R.id.img1 );
        img2 = (ImageView)v.findViewById( R.id.img2 );
        img3 = (ImageView)v.findViewById( R.id.img3 );

        imgMain.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);

        populate();
    }

    private void populate(){
        img1.setImageResource(getResId(myVegetable.getName(), Drawable.class, myVegetable.getType()));
        img2.setImageResource(getResId(vegetable2.getName(), Drawable.class, vegetable2.getType()));
        img3.setImageResource(getResId(vegetable3.getName(), Drawable.class, vegetable3.getType()));
    }

    public int getResId(String variableName, Class<?> c, int type) {
        try {
            Field idField = c.getDeclaredField(((type==FRUIT)?"fruit_": "legume_")+variableName+".png");
            Log.e("legumes",((type==FRUIT)?"fruit_": "legume_")+variableName+".png");
            return idField.getInt(idField);
        } catch (Exception e) {
            Log.e("error",e.getMessage());
            return -1;
        }
    }



    @Override
    public void onClick(View view) {
        if(view==img1){

        } else if(view==img2){

        } else if(view==img3){

        } else if(view==imgMain){
            ttobj.speak("caca", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private Vegetable initRandomVegetable(){
        int nbrFruit= GameActivity.listFruits.length;
        int nbrLegume= GameActivity.listLegumes.length;

        Random random=new Random();
        int r=random.nextInt(1);
        switch (r){
            case LEGUMES:
                return new Vegetable(GameActivity.listLegumes[random.nextInt(nbrLegume)], LEGUMES);
            case FRUIT:
                return new Vegetable(GameActivity.listFruits[random.nextInt(nbrFruit)], FRUIT);
        }
        return null;
    }
}
