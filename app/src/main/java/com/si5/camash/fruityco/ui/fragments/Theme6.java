package com.si5.camash.fruityco.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.si5.camash.fruityco.R;
import com.si5.camash.fruityco.Utils.Constants;
import com.si5.camash.fruityco.Utils.Utils;
import com.si5.camash.fruityco.data.Aliment;
import com.si5.camash.fruityco.data.events.MyFailDragListener;
import com.si5.camash.fruityco.data.events.OnFailEvent;
import com.si5.camash.fruityco.data.events.OnSuccessEvent;
import com.si5.camash.fruityco.ui.activities.GameActivity;
import com.si5.camash.fruityco.ui.adapter.CoverFlowAdapterImage;
import com.si5.camash.fruityco.ui.adapter.ImageAdapter;
import com.si5.camash.fruityco.ui.views.coverFlow.FancyCoverFlow;
import com.si5.camash.fruityco.ui.views.coverFlow.FancyCoverFlowAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.greenrobot.event.EventBus;

import static com.si5.camash.fruityco.Utils.Utils.addRandomAliment;
import static com.si5.camash.fruityco.Utils.Utils.getAllRes;

/**
 * Created by benjamin on 22/10/2014.
 */
public class Theme6 extends Fragment implements View.OnClickListener {


    private TextToSpeech ttobj;
    private FancyCoverFlow legumes;
    private FancyCoverFlow fruits;
    private ImageView btnSong;
    private GridView answer;

    private ImageAdapter adapter;



    List<Aliment> aliments = new ArrayList<Aliment>();
    List<String> answerFound=new ArrayList<String>();



    public static Theme6 newInstance() {
        return new Theme6();
    }

    public Theme6() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new ImageAdapter(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_theme6, container, false);

        //Init aliments
        aliments = addRandomAliment(5);


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


    @Override
    public void onPause() {
        if (ttobj != null) {
            ttobj.stop();
            ttobj.shutdown();
        }
        super.onPause();
    }


    private void findViews(View v) {
        legumes = (FancyCoverFlow)v.findViewById(R.id.legumes);
        fruits = (FancyCoverFlow) v.findViewById(R.id.fruits);
        btnSong = (ImageView)v.findViewById(R.id.imgMain);
        answer = (GridView) v.findViewById(R.id.gridView);

        answer.setAdapter(adapter);

        btnSong.setOnClickListener(this);
        populate();
    }

    private void populate() {
        legumes.setAdapter(new CoverFlowAdapterImage(getAllRes(getActivity(), Constants.LEGUMES)));
        fruits.setAdapter(new CoverFlowAdapterImage(getAllRes(getActivity(), Constants.FRUIT)));

        legumes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for(Aliment al:aliments){
                    if(al.getType()== Constants.LEGUMES && al.getName().equals(GameActivity.listLegumes[position]) && !answerFound.contains(al.getName())){
                        answerFound.add(al.getName());
                        adapter.addImg(Utils.getResId(getActivity(),al.getName(),Constants.LEGUMES));
                        if(answerFound.size()==3){
                            EventBus.getDefault().post(new OnSuccessEvent());
                        }
                    }else{
                        EventBus.getDefault().post(new OnFailEvent());
                    }

                }
            }
        });

        fruits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for(Aliment al:aliments){
                    if(al.getType()== Constants.FRUIT && al.getName().equals(GameActivity.listFruits[position]) && !answerFound.contains(al.getName())){
                        answerFound.add(al.getName());
                        adapter.addImg(Utils.getResId(getActivity(),al.getName(),Constants.FRUIT));
                        if(answerFound.size()==3){
                            EventBus.getDefault().post(new OnSuccessEvent());
                        }
                    } else {
                        EventBus.getDefault().post(new OnFailEvent());
                    }
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
        if(view==btnSong){
            ttobj.speak(aliments.get(0).getName()+", "+aliments.get(1).getName()+", "+aliments.get(2).getName(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }




}

