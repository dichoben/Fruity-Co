package com.si5.camash.fruityco.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.si5.camash.fruityco.R;

public class MenuActivity extends Activity implements View.OnClickListener {

    private TextView lvl1;
    private TextView lvl2;
    private TextView lvl3;
    private TextView lvl4;
    private TextView lvl5;
    private TextView lvl6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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

    private void findViews() {
        lvl1 = (TextView)findViewById( R.id.lvl1 );
        lvl2 = (TextView)findViewById( R.id.lvl2 );
        lvl3 = (TextView)findViewById( R.id.lvl3 );
        lvl4 = (TextView)findViewById( R.id.lvl4 );
        lvl5 = (TextView)findViewById( R.id.lvl5 );
        lvl6 = (TextView)findViewById( R.id.lvl6 );

        lvl1.setOnClickListener( this );
        lvl2.setOnClickListener( this );
        lvl3.setOnClickListener( this );
        lvl4.setOnClickListener( this );
        lvl5.setOnClickListener( this );
        lvl6.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        Bundle b = new Bundle();
        if ( v == lvl1 ) {
            b.putInt(GameActivity.PARAM, 1);

        } else if ( v == lvl2 ) {
            b.putInt(GameActivity.PARAM, 2);
        } else if ( v == lvl3 ) {
            b.putInt(GameActivity.PARAM, 3);
        } else if ( v == lvl4 ) {
            b.putInt(GameActivity.PARAM, 4);
        } else if ( v == lvl5 ) {
            b.putInt(GameActivity.PARAM, 5);
        } else if ( v == lvl6 ) {
            b.putInt(GameActivity.PARAM, 6);
        }
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
}
