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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
    }


    private void findViews() {
        playBtn = (ImageButton)findViewById( R.id.playBtn );

        playBtn.setOnClickListener( this );
    }


    @Override
    public void onClick(View v) {
        if ( v == playBtn ) {
            Intent intent=new Intent(this, GameActivity.class);
            startActivity(intent);
        }
    }


}
