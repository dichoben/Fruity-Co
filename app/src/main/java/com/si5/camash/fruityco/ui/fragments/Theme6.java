package com.si5.camash.fruityco.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.si5.camash.fruityco.R;
import com.si5.camash.fruityco.Utils.Constants;
import com.si5.camash.fruityco.data.Aliment;
import com.si5.camash.fruityco.ui.adapter.CoverFlowAdapterImage;
import com.si5.camash.fruityco.ui.views.coverFlow.FancyCoverFlow;
import com.si5.camash.fruityco.ui.views.coverFlow.FancyCoverFlowAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.si5.camash.fruityco.Utils.Utils.addRandomAliment;
import static com.si5.camash.fruityco.Utils.Utils.getAllRes;

/**
 * Created by benjamin on 22/10/2014.
 */
public class Theme6 extends Fragment implements View.OnClickListener {


    private TextToSpeech ttobj;
    private FancyCoverFlow legumes;
    private FancyCoverFlow fruits;

    private FancyCoverFlowAdapter legumesAdapter;
    private FancyCoverFlowAdapter fruitsAdapter;

    List<Aliment> aliments = new ArrayList<Aliment>();


    public static Theme6 newInstance() {
        return new Theme6();
    }

    public Theme6() {
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
        View v = inflater.inflate(R.layout.fragment_theme6, container, false);


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

        populate();
    }

    private void populate() {
        legumes.setAdapter(new CoverFlowAdapterImage(getAllRes(getActivity(), Constants.LEGUMES)));
        fruits.setAdapter(new CoverFlowAdapterImage(getAllRes(getActivity(), Constants.FRUIT)));

    }


    @Override
    public void onClick(View view) {

    }




}

