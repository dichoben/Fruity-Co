package com.si5.camash.fruityco.ui.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.si5.camash.fruityco.R;

import java.util.Locale;


public class Theme1 extends Fragment {


    private TextToSpeech ttobj;
    private ImageView imgMain;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;



    public static Theme1 newInstance(String param1, String param2) {
        Theme1 fragment = new Theme1();

        return fragment;
    }
    public Theme1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ttobj=new TextToSpeech(getActivity(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            ttobj.setLanguage(Locale.FRENCH);
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
    }


    public void speakText(String text){
        ttobj.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }

}
