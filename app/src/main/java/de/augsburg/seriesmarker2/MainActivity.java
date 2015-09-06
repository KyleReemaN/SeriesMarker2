package de.augsburg.seriesmarker2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> countries = new ArrayList<String>();

        countries.add("Italy");
        countries.add("Spain");
        countries.add("France");
        countries.add("Germany");
        countries.add("United Kingdom");
        countries.add("Austria");
        countries.add("Ireland");
        countries.add("Portugal");
        countries.add("Belgium");
        countries.add("Denmark");
        countries.add("Finland");
        countries.add("Norway");
        countries.add("Sweden");
        countries.add("Netherlands");
        countries.add("Greece");
        countries.add("Luxembourg");
        countries.add("Malta");
        countries.add("Latvia");
        countries.add("Slovakia");
        countries.add("Slovenia");
        countries.add("Poland");
        countries.add("Hungary");
        countries.add("Romania");

        Collections.sort(countries);

        FastSearchListView listView = (FastSearchListView) findViewById(R.id.listview);

        SimpleAdapter sa = new SimpleAdapter(countries, this);
        listView.setAdapter(sa);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
