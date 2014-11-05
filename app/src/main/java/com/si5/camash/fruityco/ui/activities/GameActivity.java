package com.si5.camash.fruityco.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.si5.camash.fruityco.R;
import com.si5.camash.fruityco.data.events.OnFailEvent;
import com.si5.camash.fruityco.data.events.OnSuccessEvent;
import com.si5.camash.fruityco.ui.fragments.Theme1;
import com.si5.camash.fruityco.ui.fragments.Theme3;
import com.si5.camash.fruityco.ui.fragments.Theme2;
import com.si5.camash.fruityco.ui.fragments.Theme4_5;
import com.si5.camash.fruityco.ui.fragments.Theme6;


import de.greenrobot.event.EventBus;

public class GameActivity extends Activity {

    public static String PARAM="lvl";

    public static String[] listFruits = {"abricot", "ananas", "banane", "cassis", "cerise", "citron", "fraise", "framboise", "grenade", "kiwi", "litchi", "mangue", "melon", "noix", "noix de coco", "orange", "pamplemousse", "pastèque", "pêche", "poire", "pomme", "prune", "raisin"};
    public static String[] listLegumes = {"ail", "artichaut", "asperge", "aubergine", "avocat", "betterave", "brocoli", "carotte", "chou fleur", "chou rouge", "citrouille", "concombre", "courgette", "endive", "haricot", "laitue", "mâche", "ma iss", "oignon", "petit pois", "poireau", "poivron", "pomme de terre", "radis", "tomate"};

    private int currentLvl = 0;

    private boolean firstlaunch=true;

    private TextView lvlText;

    private Handler handler = new Handler();

    private int statistic[] = {0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle b = getIntent().getExtras();
        int value = b.getInt(PARAM);
        switch(value){
            case 1:
                currentLvl=0;
                break;
            case 2:
                currentLvl = 5;
                break;
            case 3:
                currentLvl = 10;
                break;
            case 4:
                currentLvl = 13;
                break;
            case 5:
                currentLvl = 18;
                break;
            case 6:
                currentLvl = 22;
                break;
        }
        findViews();
        onEvent(new OnSuccessEvent());
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void changeMainContent(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment
        transaction.replace(R.id.gameContent, fragment);

        // Commit the transaction
        transaction.commit();
    }


    private void findViews() {
        lvlText = (TextView) findViewById(R.id.lvlText);
    }

    public void onEvent(OnSuccessEvent event) {
        showSuccess();
        currentLvl++;


        //lvl 1 to 5
        if (currentLvl >= 1 && currentLvl <= 5) {
            changeMainContent(Theme1.newInstance());
        } else if (currentLvl >= 6 && currentLvl <= 10) {
            changeMainContent(Theme2.newInstance());
            //changeMainContent(Theme4_5.newInstance(Theme4_5.THEME4));

        } else if (currentLvl >= 11 && currentLvl <= 13) {
            changeMainContent(Theme3.newInstance());
        } else if (currentLvl >= 14 && currentLvl <= 18) {
            changeMainContent(Theme4_5.newInstance(Theme4_5.THEME4));
        } else if (currentLvl >= 19 && currentLvl <= 23) {
            changeMainContent(Theme4_5.newInstance(Theme4_5.THEME5));
        } else if (currentLvl > 23 && currentLvl <=25){
            changeMainContent(Theme6.newInstance());
        }

        lvlText.setText("Niveau " + Integer.toString(currentLvl));
        //lvlText.setText("Niveau " + Integer.toString(currentLvl)+" avec "+statistic[0]+" "+statistic[1]+" "+statistic[3]+" "+statistic[4]);

    }

    private void showSuccess() {
        if (!firstlaunch) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final Dialog dialog = builder.setMessage("YOU WIN").setCancelable(false).create();
            dialog.show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            }, 1500);

        }
        firstlaunch=false;
    }

    public void onEvent(OnFailEvent event) {
        if (currentLvl >= 0 && currentLvl <= 5) {
            statistic[0]++;
        } else if (currentLvl >= 6 && currentLvl <= 10) {
            statistic[1]++;
        } else if (currentLvl >= 11 && currentLvl <= 13) {
            statistic[2]++;
        } else if (currentLvl >= 14 && currentLvl <= 18) {
            statistic[3]++;
        } else if (currentLvl >= 19 && currentLvl <= 23) {
            statistic[4]++;
        } else {
            statistic[5]++;
        }
        //event.addTentative();
        //statistic[currentLvl] = event.getTentative();
    }

}
