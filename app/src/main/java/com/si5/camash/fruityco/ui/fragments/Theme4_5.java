package com.si5.camash.fruityco.ui.fragments;

import android.app.Fragment;
import android.content.ClipData;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.si5.camash.fruityco.R;
import com.si5.camash.fruityco.Utils.Constants;
import com.si5.camash.fruityco.Utils.Utils;
import com.si5.camash.fruityco.data.Aliment;
import com.si5.camash.fruityco.data.events.OnSuccessEvent;


import java.util.Locale;

import de.greenrobot.event.EventBus;

/**
 * Created by benjamin on 15/10/2014.
 */
public class Theme4_5 extends Fragment implements View.OnClickListener {
    public static final int THEME4 = 0;
    public static final int THEME5 = 1;

    private final static String ARG_THEM = "theme";

    private ImageView imgMain;
    private ImageView fruits;
    private TextView textFruits;
    private ImageView legumes;
    private TextView textLegumes;

    private TextToSpeech ttobj;

    private Aliment aliment;
    private int theme;


    public static Fragment newInstance(int theme) {
        Fragment fragment = new Theme4_5();
        Bundle args = new Bundle();
        args.putInt(ARG_THEM, theme);
        fragment.setArguments(args);
        return fragment;
    }

    public Theme4_5() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            theme = getArguments().getInt(ARG_THEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        aliment = Utils.initRandomAliment();

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
        View v = inflater.inflate(R.layout.fragment_theme_4_5, container, false);
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
        fruits = (ImageView) v.findViewById(R.id.fruits);
        textFruits = (TextView) v.findViewById(R.id.textFruits);
        legumes = (ImageView) v.findViewById(R.id.legumes);
        textLegumes = (TextView) v.findViewById(R.id.textLegumes);

        imgMain.setOnTouchListener(new MyTouchListener());
        legumes.setOnClickListener(this);
        fruits.setOnClickListener(this);
        textFruits.setOnClickListener(this);
        textLegumes.setOnClickListener(this);

        populate();
    }


    private void populate() {
        if (theme == THEME4) {
            imgMain.setImageDrawable(Utils.getRes(getActivity(), aliment.getName(), aliment.getType()));
        }
        fruits.setOnDragListener(new MyDragListener());
        legumes.setOnDragListener(new MyDragListener());

    }


    @Override
    public void onClick(View view) {
        if (view == fruits && aliment.getType() == Constants.FRUIT) {
            EventBus.getDefault().post(new OnSuccessEvent());

        } else if (view == legumes && aliment.getType() == Constants.LEGUMES) {
            EventBus.getDefault().post(new OnSuccessEvent());

        } else if (view == textFruits) {
            ttobj.speak("Fruit", TextToSpeech.QUEUE_FLUSH, null);

        } else if (view == textLegumes) {
            ttobj.speak("Legume", TextToSpeech.QUEUE_FLUSH, null);

        }
    }


    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (theme == THEME5) {
                    ttobj.speak(aliment.getName(), TextToSpeech.QUEUE_FLUSH, null);
                }
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    if ((v == fruits && aliment.getType() == Constants.FRUIT) || (v == legumes && aliment.getType() == Constants.LEGUMES))
                        EventBus.getDefault().post(new OnSuccessEvent());
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    imgMain.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            return true;
        }
    }

}
