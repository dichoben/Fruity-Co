package com.si5.camash.fruityco.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.si5.camash.fruityco.R;

public class GameActivity extends Activity {


    public String [] listFruits = {"abricot", "ananas", "banane", "cassis", "cerise", "citron", "fraise", "framboise", "grenade", "kiwi", "litchi", "mangue", "melon", "noix", "noix_de_coco", "orange", "pamplemousse", "pasteque", "peche", "poire", "pomme", "prune", "raisin"};
    public String [] listLegumes = {"ail", "artichaut", "asperge", "aubergine", "avocat", "bettrave", "brocoli", "carotte", "chou_fleur", "chou_rouge", "citrouille", "concombre", "courgette", "endive", "haricot", "laitue", "mache", "mais", "oignon", "petit_pois", "poireau", "poivron", "pomme_de_terre", "radis", "tomate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
