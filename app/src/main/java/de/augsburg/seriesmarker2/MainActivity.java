package de.augsburg.seriesmarker2;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class MainActivity extends AppCompatActivity {

    private final int ADD_DIALOG = 0;
    private final int CHANGE_SERIESNAME_DIALOG = 1;
    private int posOfElementThatStartedContextMenu = 0;

    private ListView listView;
    private SimpleAdapter listviewadapter;
    public ArrayList<Series> seriesList = new ArrayList<Series>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializeSeriesList();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        seriesList.add(new Series("Alpha Series"));
        seriesList.add(new Series("BBlpha Series"));
        seriesList.add(new Series("CClpha Series"));
        seriesList.add(new Series("CCClpsdha Series"));
        seriesList.add(new Series("CCClpsda Series"));
        seriesList.add(new Series("RRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("RRRRCClpha Series"));
        seriesList.add(new Series("ZRRRCClpha Series"));
        seriesList.add(new Series("ZRRRCClpha Series"));

        Collections.sort(seriesList, new Comparator<Series>() {
            public int compare(Series lhs, Series rhs) {
                int res = String.CASE_INSENSITIVE_ORDER.compare(lhs.getSeriesName(), rhs.getSeriesName());
                return res;
            }
        });

        listView = (FastSearchListView) findViewById(R.id.listview);
        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test","test onItemLongClick");
                return true;
            }
        });

        listviewadapter = new SimpleAdapter(seriesList, this);
        listView.setAdapter(listviewadapter);

    }


    private void initializeSeriesList(){
        try {
            FileInputStream fis = openFileInput("Series.txt");
            ObjectInputStream is = new ObjectInputStream(fis);
            seriesList = (ArrayList<Series>)is.readObject();
        } catch (FileNotFoundException e) {
            Log.d("seriesMarker", "Failed to read serialization file Series.txt cause file not found");
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.d("seriesMarker", "Class not found exception");
        } catch (Exception e) {
            Log.d("seriesMarker", "Serialization failed create empty seriesList");
            seriesList = new ArrayList<Series>();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.add_series:  // 1 create dialog with available seriesnames - 2 after choosing one synchronize
                // create_available_series_dialog
                synchronize();
                //showDialog(ADD_DIALOG);
                return true;

            case R.id.search:
                Toast t2 = Toast.makeText(this, "Searchi", Toast.LENGTH_SHORT);
                t2.show();
                return true;

            case R.id.synchronize:
                synchronize();
                return true;

            case R.id.saveTXT:
                saveTXT();
                Toast t4 = Toast.makeText(this, "Data saved to seriesData.txt", Toast.LENGTH_SHORT);
                t4.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // TODO
    // Create background task for synchronize? problems with locks? series objects...
//    private class SynchronizeTVDB extends AsyncTask<String, Void, String> {
//        private Exception exception;
//
//        protected String doInBackground(String... urls) {
//            try {
//
//                return "test";
//            } catch (Exception e) {
//                this.exception = e;
//                return null;
//            }
//        }
//
//        protected void onPostExecute(String result) {
//            // TODO: check this.exception
//            // TODO: do something with the feed
//        }
//    }

    //TODO
    private void synchronize(){
        // SYNCHRONIZE EVERY SERIES
        InputStream inputXMLStream = null;

        try {
            // USE THIS URL CALL to see if the series exists (language=de searches en AND de)
            URL url = new URL("http://thetvdb.com/api/GetSeries.php?seriesname=Dexter&language=de");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            inputXMLStream = (con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        XmlPullParserFactory factory;
        XmlPullParser xpp = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            xpp = factory.newPullParser();
            xpp.setInput(inputXMLStream, null);
        }
        catch(Exception e){
            Log.d("xmltest", "could not create factory for xmlpullparser");
        }

        String seriesID;
        String seriesName;
        String language;

        int eventType;
        try{
            eventType = xpp.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String name = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if(name.equals("seriesid")){
                            seriesID = fetchXmlText(xpp);
                        }
                        else if(name.equals("SeriesName")){
                            seriesName = fetchXmlText(xpp);
                        }
                        else if(name.equals("language")){
                            language = fetchXmlText(xpp);
                        }
                        break;

                    default:
                        break;
                }
                eventType = xpp.next();
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                if(inputXMLStream!=null)
                    inputXMLStream.close();
            } catch (IOException e) {
            }
        }
    }


    // CREATE DIALOG WITH POSSIBILITIES
    // TODO sql attack? "url..." + variable + "..." ....
    // search right series with name
    // save seriesid, SeriesName, language
    // let user choose if the right series is avaiable
    // DEFAULT: if no right series there option to create own series (no syncrhonize available)
    private ArrayList<Series> searchSeries(String seriesName){
        // http://thetvdb.com/api/GetSeries.php?seriesname=dexter&language=de
        String url = "http://thetvdb.com/api/GetSeries.php?seriesname="+ seriesName +"&language=de";
        return null;
    }

    // get more information about series
    // IMPORTANT <Status>Ended</Status> OR <Status>Continuing</Status>
    // get and Set Series Status (ended/continuing)
    // get interesting data like Imdb-Rating ... / Overview...
    // get PICTURE (banner/poster test...)
    private void getDetailSeriesInfoInitial(Series series){
        //http://thetvdb.com/api/1F266C5426BB0174/series/79349/de.xml
        // CHANGE URL LIKE "http ..... "+ series.getSeriesId " ... /" + series.language + ".xml";
    }

    // just to get Series_Status (is status still continuing?)
    // use this in the refresher method (if not Initial was called)
    private void getDetailSeriesInfoRefresh(Series series){
        //http://thetvdb.com/api/1F266C5426BB0174/series/79349/de.xml
    }

    // TEST what is more secure <SeasonNumber>+<EpisodeNumber> OR <DVD_season>+<DVD_episodenumber>
    // CARE DO NOT USE SeasonNumber=0 (0 is combined or shit...) also not use dvd_season0.....
    private void refreshSeriesData(Series series){
        //http://thetvdb.com/api/1F266C5426BB0174/series/79349/all/de.xml

        // create array [seasonNumber][episodeNumber]
        // [1]-[12], [2]-[22]
        // first array_seasonNumber, second max_episodeCount (at the moment)

        // refresh series object
    }

    // get xpp (at tag-level xml.START_TAG <seriesid>78788</seriesid>
    // fetch xpp.next and get text
    private String fetchXmlText(XmlPullParser xpp){
        try{
            xpp.next();
            if(xpp.getEventType()==XmlPullParser.TEXT && !xpp.getText().isEmpty() && xpp.getText()!="null")
                return xpp.getText();
            return null;
        }
        catch(Exception ex){
        }
        return null;
    }


    private void saveTXT(){
        OutputStream myOutput;
        try {
            File dir=Environment.getExternalStoragePublicDirectory("SeriesMarker");
            dir.mkdirs();
            File seriesDataFile = new File(dir, "seriesData.txt");
            myOutput = new FileOutputStream(seriesDataFile);
            //myOutput = openFileOutput("seriesData.txt", Context.MODE_WORLD_READABLE);
            OutputStreamWriter osw = new OutputStreamWriter(myOutput);
            for (Series series : seriesList) {
                String seriesData = series.getSeriesName()+"#"+"S"+series.getLastSeenSeason()+"E"+series.getLastSeenEpisode()+"\n";
                Log.d("testo", seriesData);
                osw.write(seriesData);
            }
            osw.flush();
            osw.close();
            myOutput.flush();
            myOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // HOW TO READ FROM the file
//	private void readTXT(){
//		try {
//			FileInputStream fis = openFileInput("seriesData.txt");
//			BufferedReader insertReader = new BufferedReader(new InputStreamReader(fis));
//
//			Log.d("testo", "TESTOMATE");
//			String statement;
//			while ((statement = insertReader.readLine()) != null) {
//				Log.d("testo", "TESTOMATE2");
//				Log.d("testo", statement);
//			}
//			Log.d("testo", "TESTOMATEN333333");
//			insertReader.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater m = getMenuInflater();

        switch(v.getId()){
            case R.id.listview: m.inflate(R.menu.series_contextmenu, menu);
                break;
            default: break;
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        int position = 0;
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        position = (int) info.position;
        posOfElementThatStartedContextMenu = position;

        switch(item.getItemId()){
            case R.id.deleteSeries:
                seriesList.remove(position);
                listviewadapter.notifyDataSetChanged();
                return true;
            case R.id.changeSeriesName: showDialog(CHANGE_SERIESNAME_DIALOG);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    protected Dialog onCreateDialog(int dialogId){
        Dialog tmpDialog = null;

        switch (dialogId) {
            case ADD_DIALOG: tmpDialog = createAddDialog();
                break;

            case CHANGE_SERIESNAME_DIALOG: tmpDialog = createChangeSeriesNameDialog();
                break;
        }
        return tmpDialog;
    }

    protected void onPrepareDialog(int dialogId, Dialog dialog){

        switch (dialogId) {
            case ADD_DIALOG:	AlertDialog ad1 = (AlertDialog)dialog;
                EditText tView1 = (EditText)ad1.findViewById(R.id.add_series_edit_text);
                tView1.setText("");
                break;

            case CHANGE_SERIESNAME_DIALOG:	AlertDialog ad2 = (AlertDialog)dialog;
                EditText tView2 = (EditText)ad2.findViewById(R.id.changeSeriesNameEditText);
                tView2.setText("");
                break;
        }
    }

    private Dialog createAddDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_add_series, null))
                // Add action buttons
                .setNegativeButton("Abbruch", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText)((AlertDialog)dialog).findViewById(R.id.add_series_edit_text);
                        String seriesName = editText.getText().toString();
                        // create seriesview and add it to the layout
                        if(!seriesName.isEmpty()){
                            Series series = new Series(seriesName);
                            seriesList.add(series);
                            seriesList.get(seriesList.size()-1).addSeason();
                            seriesList.get(seriesList.size()-1).seasons.get(0).addEpisode(false);
                            Collections.sort(seriesList, new Comparator<Series>() {
                                public int compare(Series lhs, Series rhs) {
                                    int res = String.CASE_INSENSITIVE_ORDER.compare(lhs.getSeriesName(), rhs.getSeriesName());
                                    return res;
                                }
                            });
                            listviewadapter.notifyDataSetChanged();
                        }
                        dialog.dismiss();
                    }
                });

        builder.setTitle("Serie hinzufügen");

        return builder.create();
    }

    private Dialog createChangeSeriesNameDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_change_seriesname, null))
                // Add action buttons
                .setNegativeButton("Abbruch", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText)((AlertDialog)dialog).findViewById(R.id.changeSeriesNameEditText);
                        String seriesName = editText.getText().toString();
                        seriesList.get(posOfElementThatStartedContextMenu).changeSeriesName(seriesName);
                        Collections.sort(seriesList, new Comparator<Series>() {
                            public int compare(Series lhs, Series rhs) {
                                int res = String.CASE_INSENSITIVE_ORDER.compare(lhs.getSeriesName(), rhs.getSeriesName());
                                return res;
                            }
                        });
                        listviewadapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

        builder.setTitle("Serienname eingeben");
        return builder.create();
    }


    protected void onPause(){
        super.onPause();
        Log.d("seriesMarker", "Starting onPause method");

        // iterate through the seriesView components and serialize the 'Series' object
        // TODO OUTCOMMENT
//        FileOutputStream fos;
//        try {
//            fos = openFileOutput("Series.txt", Context.MODE_PRIVATE);
//            ObjectOutputStream os = new ObjectOutputStream(fos);
//            os.writeObject(seriesList);
//            os.close();
//
//        } catch (FileNotFoundException e) {
//            Log.d("seriesMarker", "File 'Series.txt' not found");
//        } catch (IOException e) {
//            Log.d("seriesMarker", "IOException");
//            e.printStackTrace();
//        }

    }

}
