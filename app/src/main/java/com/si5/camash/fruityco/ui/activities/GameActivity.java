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
import com.si5.camash.fruityco.data.events.OnSuccessEvent;
import com.si5.camash.fruityco.ui.fragments.Theme1;
import com.si5.camash.fruityco.ui.fragments.Theme3;
import com.si5.camash.fruityco.ui.fragments.Theme2;
import com.si5.camash.fruityco.ui.fragments.Theme4_5;


import de.greenrobot.event.EventBus;

public class GameActivity extends Activity {


    public static String[] listFruits = {"abricot", "ananas", "banane", "cassis", "cerise", "citron", "fraise", "framboise", "grenade", "kiwi", "litchi", "mangue", "melon", "noix", "noix de coco", "orange", "pamplemousse", "pastèque", "pêche", "poire", "pomme", "prune", "raisin"};
    public static String[] listLegumes = {"ail", "artichaut", "asperge", "aubergine", "avocat", "betterave", "brocoli", "carotte", "chou fleur", "chou rouge", "citrouille", "concombre", "courgette", "endive", "haricot", "laitue", "mâche", "ma iss", "oignon", "petit pois", "poireau", "poivron", "pomme de terre", "radis", "tomate"};

    private int currentLvl = 0;

    private TextView lvlText;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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
        currentLvl++;
        showSuccess();

        //lvl 1 to 5
        if (currentLvl >= 1 && currentLvl <= 5) {
            changeMainContent(Theme1.newInstance());
        } else if (currentLvl >= 6 && currentLvl <= 10) {
            changeMainContent(Theme2.newInstance());

        } else if (currentLvl >= 11 && currentLvl <= 13) {
            changeMainContent(Theme3.newInstance());
        } else if (currentLvl >= 14 && currentLvl <= 18) {
            changeMainContent(Theme4_5.newInstance(Theme4_5.THEME4));
        } else if (currentLvl >= 19 && currentLvl <= 23) {
            changeMainContent(Theme4_5.newInstance(Theme4_5.THEME5));
        }

        lvlText.setText("Niveau " + Integer.toString(currentLvl));

    }

    private void showSuccess() {
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


}
