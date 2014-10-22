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
import android.widget.LinearLayout;

import com.si5.camash.fruityco.R;
import com.si5.camash.fruityco.Utils.Utils;
import com.si5.camash.fruityco.data.Aliment;
import com.si5.camash.fruityco.data.events.OnSuccessEvent;
import com.si5.camash.fruityco.ui.views.DrawView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import de.greenrobot.event.EventBus;

import static com.si5.camash.fruityco.Utils.Utils.addRandomAliment;


public class Theme3 extends Fragment implements View.OnClickListener {

    private static final int NB_FRUIT_VEGETABLE = 3;
    private TextToSpeech ttobj;
    private List<ImageView> imgMain1 = new ArrayList<ImageView>();
    private List<ImageView> img1 = new ArrayList<ImageView>();
    private DrawView drawView;
    private LinearLayout layoutImgMain;
    private LinearLayout layoutImg;

    List<Aliment> aliments = new ArrayList<Aliment>();
    private int [] positionResponse = new int[NB_FRUIT_VEGETABLE];
    private int nbFounded = 0;
    private int onMovement;

    public static Theme3 newInstance() {
        return new Theme3();
    }

    public Theme3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_theme3, container, false);

        aliments=addRandomAliment(NB_FRUIT_VEGETABLE);
        for (int i=0; i<NB_FRUIT_VEGETABLE; i++){
            do {
                positionResponse[i] = getRandomPosition();
            }while(alreadyExist(i,positionResponse[i]));
        }


        ImageView imgMain1;
        ttobj = new TextToSpeech(getActivity(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            ttobj.setLanguage(Locale.FRENCH);
                        }
                    }
                });


        findViews(v);
        return v;
    }

    private boolean alreadyExist(int position, int nb){
        for (int i = 0; i<position; i++) {
            if(positionResponse[i] == nb) {
                return true;
            }
        }
        return false;
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
        imgMain1.add((ImageView) v.findViewById(R.id.imgMain1));
        img1.add((ImageView) v.findViewById(R.id.img1));
        imgMain1.add((ImageView) v.findViewById(R.id.imgMain2));
        img1.add((ImageView) v.findViewById(R.id.img2));
        imgMain1.add((ImageView) v.findViewById(R.id.imgMain3));
        img1.add((ImageView) v.findViewById(R.id.img3));
        drawView = (DrawView) v.findViewById(R.id.drawView);
        layoutImg = (LinearLayout)v.findViewById(R.id.layoutImg);
        layoutImgMain = (LinearLayout)v.findViewById(R.id.layoutImgMain);

        // Sets a long click listener for the ImageView using an anonymous listener object that
        // implements the OnLongClickListener interface
        for(ImageView img:imgMain1){
            img.setOnClickListener(this);
            img.setOnTouchListener(new MyTouchListener());
        }

        populate();
    }

    private void populate() {
        int i=0;
        for(ImageView img:img1){
            img.setImageDrawable(Utils.getRes(getActivity(), aliments.get(i).getName(), aliments.get(i).getType()));
            img.setOnDragListener(new MyDragListener());
            i++;
        }

    }


    @Override
    public void onClick(View view) {
        int i=0;
        for(ImageView img:imgMain1){
            if(img==view){
                ttobj.speak(aliments.get(positionResponse[i]).getName(), TextToSpeech.QUEUE_FLUSH, null);
                break;
            }
        }
        i++;


    }




    private int getRandomPosition() {
        Random random = new Random();
        return random.nextInt(aliments.size());
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {

            int i=0;
            for(ImageView img:imgMain1){
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && view == img) {
                    ttobj.speak(aliments.get(positionResponse[i]).getName(), TextToSpeech.QUEUE_FLUSH, null);
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(data, shadowBuilder, view, 0);
                    view.setVisibility(View.INVISIBLE);
                    onMovement = i;
                    return true;
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP && view == img){
                    img.setVisibility(View.VISIBLE);
                    return true;
                }
                i++;
            }
            return false;
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            boolean isDropInside=false;
                if(v==img1.get(positionResponse[onMovement]) && event.getAction()==DragEvent.ACTION_DROP){
                    nbFounded++;
                    float xImageMain = imgMain1.get(onMovement).getX()+imgMain1.get(onMovement).getWidth()/2;
                    float yImageMain = layoutImgMain.getY()+layoutImgMain.getHeight()/2;
                    float xImage = img1.get(positionResponse[onMovement]).getX()+img1.get(positionResponse[onMovement]).getWidth()/2;
                    float yImage = layoutImg.getY();

                    if(nbFounded == 3) {
                        EventBus.getDefault().post(new OnSuccessEvent());
                    }
                    imgMain1.get(onMovement).setOnTouchListener(null);
                    drawView.drawLine(xImageMain,yImageMain,xImage,yImage);
                } else if (event.getAction()==DragEvent.ACTION_DRAG_ENDED){
                    imgMain1.get(onMovement).setVisibility(View.VISIBLE);
                }
            return true;
        }
    }

}
