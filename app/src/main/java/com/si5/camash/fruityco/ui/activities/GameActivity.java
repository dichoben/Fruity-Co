package com.si5.camash.fruityco.ui.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.si5.camash.fruityco.R;
import com.si5.camash.fruityco.ui.fragments.Theme1;
import com.si5.camash.fruityco.ui.fragments.Theme3;

public class GameActivity extends Activity {


    public static String [] listFruits = {"abricot", "ananas", "banane", "cassis", "cerise", "citron", "fraise", "framboise", "grenade", "kiwi", "litchi", "mangue", "melon", "noix", "noix_de_coco", "orange", "pamplemousse", "pasteque", "peche", "poire", "pomme", "prune", "raisin"};
    public static String [] listLegumes = {"ail", "artichaut", "asperge", "aubergine", "avocat", "bettrave", "brocoli", "carotte", "chou_fleur", "chou_rouge", "citrouille", "concombre", "courgette", "endive", "haricot", "laitue", "mache", "mais", "oignon", "petit_pois", "poireau", "poivron", "pomme_de_terre", "radis", "tomate"};


    private TextView lvlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        changeMainContent(Theme3.newInstance());
        findViews();
    }

    private void changeMainContent(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment
        transaction.replace(R.id.gameContent, fragment);

        // Commit the transaction
        transaction.commit();
    }


    private void findViews() {
        lvlText = (TextView)findViewById( R.id.lvlText );
    }

}
