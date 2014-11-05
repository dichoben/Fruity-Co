package com.si5.camash.fruityco.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.si5.camash.fruityco.R;


public class HomeActivity extends Activity implements View.OnClickListener {

    private ImageButton playBtn;
    private ImageButton menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
    }


    private void findViews() {
        playBtn = (ImageButton) findViewById(R.id.playBtn);
        menuBtn = (ImageButton) findViewById(R.id.ic_menu);
        playBtn.setOnClickListener(this);
        menuBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == playBtn) {
            Intent intent = new Intent(this, GameActivity.class);
            Bundle b = new Bundle();

            b.putInt(GameActivity.PARAM, 1);

            intent.putExtras(b);
            startActivity(intent);
        }
        if (v == menuBtn) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }


}
