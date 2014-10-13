package com.si5.camash.fruityco.ui.fragments;


import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.si5.camash.fruityco.R;
import com.si5.camash.fruityco.Utils.Constants;
import com.si5.camash.fruityco.Utils.Utils;
import com.si5.camash.fruityco.data.Aliment;
import com.si5.camash.fruityco.ui.activities.GameActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class Theme1 extends Fragment implements View.OnClickListener {


    private TextToSpeech ttobj;
    private ImageView imgMain;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    List<Aliment> aliments = new ArrayList<Aliment>();
    private int positionReponse;


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
        aliments.add(initRandomVegetable());
        aliments.add(initRandomVegetable());
        aliments.add(initRandomVegetable());

        positionReponse = getRandomPosition();

        ttobj = new TextToSpeech(getActivity(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            ttobj.setLanguage(Locale.FRENCH);
                        }
                    }
                });
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_theme1, container, false);
        findViews(v);
        return v;
    }


    @Override
    public void onPause() {
        if (ttobj != null) {
            ttobj.stop();
            ttobj.shutdown();
        }
        super.onPause();
    }


    private void findViews(View v) {
        imgMain = (ImageView) v.findViewById(R.id.imgMain);
        img1 = (ImageView) v.findViewById(R.id.img1);
        img2 = (ImageView) v.findViewById(R.id.img2);
        img3 = (ImageView) v.findViewById(R.id.img3);

        imgMain.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);

        // Sets a long click listener for the ImageView using an anonymous listener object that
        // implements the OnLongClickListener interface
        imgMain.setOnTouchListener(new MyTouchListener());


        populate();
    }

    private void populate() {
        img1.setImageDrawable(Utils.getResId(getActivity(), aliments.get(0).getName(), aliments.get(0).getType()));
        img2.setImageDrawable(Utils.getResId(getActivity(), aliments.get(1).getName(), aliments.get(1).getType()));
        img3.setImageDrawable(Utils.getResId(getActivity(), aliments.get(2).getName(), aliments.get(2).getType()));

        switch (positionReponse){
            case 0:
                img1.setOnDragListener(new MyDragListener());
                break;
            case 1:
                img2.setOnDragListener(new MyDragListener());
                break;
            case 2:
                img3.setOnDragListener(new MyDragListener());
                break;

        }
    }


    @Override
    public void onClick(View view) {
        if (view == img1) {

        } else if (view == img2) {

        } else if (view == img3) {

        } else if (view == imgMain) {
            ttobj.speak(aliments.get(positionReponse).getName(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private Aliment initRandomVegetable() {
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
        if (aliments.contains(aliment)) return initRandomVegetable();

        return aliment;
    }

    private int getRandomPosition() {
        Random random = new Random();
        return random.nextInt(aliments.size());
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ttobj.speak(aliments.get(positionReponse).getName(), TextToSpeech.QUEUE_FLUSH, null);
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
            else{
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            boolean isDropInside=false;
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if(event.getResult()){

                    }else{
                        imgMain.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    }

}
