package com.si5.camash.fruityco.ui.fragments;


import android.content.ClipData;
import android.os.Bundle;
import android.app.Fragment;
import android.speech.tts.TextToSpeech;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.si5.camash.fruityco.R;
import com.si5.camash.fruityco.Utils.Utils;
import com.si5.camash.fruityco.data.Aliment;
import com.si5.camash.fruityco.data.events.MyFailDragListener;
import com.si5.camash.fruityco.data.events.OnSuccessEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import de.greenrobot.event.EventBus;

import static com.si5.camash.fruityco.Utils.Utils.addRandomAliment;


public class Theme2 extends Fragment implements View.OnClickListener {


    private TextToSpeech ttobj;
    private ImageView imgMain;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    List<Aliment> aliments = new ArrayList<Aliment>();
    private int positionResponse;
    //private int nbClickRep;


    public static Theme2 newInstance() {
        return new Theme2();
    }

    public Theme2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        aliments = addRandomAliment(3);
        //nbClickRep = 0;

        positionResponse = getRandomPosition();

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
        View v = inflater.inflate(R.layout.fragment_theme2, container, false);
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
        imgMain.setImageDrawable(Utils.getResId(getActivity(), aliments.get(positionResponse).getName(), aliments.get(positionResponse).getType()));

        switch (positionResponse) {
            case 0:
                img1.setOnDragListener(new MyDragListener());

                img2.setOnDragListener(new MyFailDragListener());
                img3.setOnDragListener(new MyFailDragListener());
                break;
            case 1:
                img2.setOnDragListener(new MyDragListener());

                img1.setOnDragListener(new MyFailDragListener());
                img3.setOnDragListener(new MyFailDragListener());
                break;
            case 2:
                img3.setOnDragListener(new MyDragListener());

                img1.setOnDragListener(new MyFailDragListener());
                img2.setOnDragListener(new MyFailDragListener());
                break;

        }
    }


    @Override
    public void onClick(View view) {
        if (view == img1) {
            ttobj.speak(aliments.get(0).getName(), TextToSpeech.QUEUE_FLUSH, null);

        } else if (view == img2) {
            ttobj.speak(aliments.get(1).getName(), TextToSpeech.QUEUE_FLUSH, null);

        } else if (view == img3) {
            ttobj.speak(aliments.get(2).getName(), TextToSpeech.QUEUE_FLUSH, null);
        } else if (view == imgMain) {

        }
    }


    private int getRandomPosition() {
        Random random = new Random();
        return random.nextInt(aliments.size());
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //ttobj.speak(aliments.get(positionResponse).getName(), TextToSpeech.QUEUE_FLUSH, null);
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                imgMain.setVisibility(View.VISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            boolean isDropInside = false;
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
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
