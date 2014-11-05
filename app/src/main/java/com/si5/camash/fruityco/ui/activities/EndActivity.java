package com.si5.camash.fruityco.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.si5.camash.fruityco.R;

public class EndActivity extends Activity {
    private int[][] statistic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Bundle b = getIntent().getExtras();
        int[] tempStat = b.getIntArray("idStat");
        Log.e("stat", ""+tempStat.length);
        ((TextView)findViewById(R.id.resultNiv1)).setText(tempStat[0]+" / "+(tempStat[0]+tempStat[1]));
        ((TextView)findViewById(R.id.resultNiv2)).setText(tempStat[2]+" / "+(tempStat[2]+tempStat[3]));
        ((TextView)findViewById(R.id.resultNiv3)).setText(tempStat[4]+" / "+(tempStat[4]+tempStat[5]));
        ((TextView)findViewById(R.id.resultNiv4)).setText(tempStat[6]+" / "+(tempStat[6]+tempStat[7]));
        ((TextView)findViewById(R.id.resultNiv5)).setText(tempStat[8]+" / "+(tempStat[8]+tempStat[9]));
        ((TextView)findViewById(R.id.resultNiv6)).setText(tempStat[10]+" / "+(tempStat[10]+tempStat[11]));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.end, menu);
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
