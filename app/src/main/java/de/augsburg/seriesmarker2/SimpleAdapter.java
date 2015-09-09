package de.augsburg.seriesmarker2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.Toast;

public class SimpleAdapter extends ArrayAdapter<Series> implements SectionIndexer {
	
	private ArrayList<Series> seriesList;
	private Context context;
	private static String sections = "abcdefghilmnopqrstuvz";
    LayoutInflater inflater;

    public SimpleAdapter(ArrayList<Series> itemList, Context ctx) {
		super(ctx, R.layout.listviewitem, itemList);
		this.seriesList = itemList;
		this.context = ctx;
        inflater = LayoutInflater.from(context);

    }
	
	public int getCount() {
		return seriesList.size();
	}

	public Series getItem(int position) {
		return seriesList.get(position);
	}

	public long getItemId(int position) {
		return seriesList.get(position).hashCode();
	}

    private class ViewHolder {
        ImageView seriesImage;
        TextView seriesName;
        TextView seasonCounter;
        TextView episodeCounter;
        ImageButton episodeUnseen;
        ImageButton episodeSeen;
    }

	@Override
	public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;
        final int pos = position;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listviewitem, null);
            // Locate the TextViews in listviewitem.xml
            // ADD IMAGEVIEW HERE
            holder.seriesImage = (ImageView)view.findViewById(R.id.seriesImage);
            holder.seriesImage.setClickable(true);
            holder.seriesName = (TextView)view.findViewById(R.id.seriesName);
            holder.seriesName.setLongClickable(true);
            holder.seasonCounter = (TextView)view.findViewById(R.id.seasonCounter);
            holder.seasonCounter.setLongClickable(true);
            holder.episodeCounter = (TextView)view.findViewById(R.id.episodeCounter);
            holder.episodeCounter.setLongClickable(true);
            holder.episodeUnseen = (ImageButton)view.findViewById(R.id.episodeUnseen);
            holder.episodeUnseen.setFocusable(false);
            holder.episodeSeen = (ImageButton)view.findViewById(R.id.episodeSeen);
            holder.episodeSeen.setFocusable(false);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        // Capture position and set to the TextViews
        holder.seriesName.setText(seriesList.get(position).getSeriesName());
        holder.seriesName.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
//                Intent seasonIntent = new Intent(context, SeasonEditorActivity.class);
//                seasonIntent.putExtra("series", (Parcelable)seriesList.get(pos));
//                seasonIntent.putExtra("seriesPosition", pos);
//                ((Activity)context).startActivityForResult(seasonIntent, 1);
                Toast t1 = Toast.makeText(context, "Test onClick SeriesName", Toast.LENGTH_SHORT);
                t1.show();
            }
        });

        holder.seriesImage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
//                Intent seasonIntent = new Intent(context, SeasonEditorActivity.class);
//                seasonIntent.putExtra("series", (Parcelable)seriesList.get(pos));
//                seasonIntent.putExtra("seriesPosition", pos);
//                ((Activity)context).startActivityForResult(seasonIntent, 1);
                Toast t1 = Toast.makeText(context, "Test onClick SeriesImage", Toast.LENGTH_SHORT);
                t1.show();
            }
        });

        if(holder.seasonCounter != null)
            holder.seasonCounter.setText(String.format("S%02d", seriesList.get(position).getLastSeenSeason()));
        if(holder.episodeCounter != null)
            holder.episodeCounter.setText(String.format("E%02d", seriesList.get(position).getLastSeenEpisode()));

        // holder.episodeUnseen

        // holder.episodeSeen


        return view;
	}

	@Override
	public int getPositionForSection(int section) {
		Log.d("ListView", "Get position for section");
		for (int i=0; i < this.getCount(); i++) {
			String item = this.getItem(i).getSeriesName().toLowerCase();
			if (item.charAt(0) == sections.charAt(section))
				return i;
		}
		return 0;
	}

	@Override
	public int getSectionForPosition(int arg0) {
		Log.d("ListView", "Get section");
		return 0;
	}

	@Override
	public Object[] getSections() {
		Log.d("ListView", "Get sections");
		String[] sectionsArr = new String[sections.length()];
		for (int i=0; i < sections.length(); i++)
			sectionsArr[i] = "" + sections.charAt(i);
		
		return sectionsArr;
	}

    public void remove(Series object) {
        seriesList.remove(object);
        notifyDataSetChanged();
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

	


}
