package de.augsburg.seriesmarker2;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Tobias on 07.09.2015.
 */
public class Season implements Parcelable, Serializable{

    private int seasonNo;
    private int episodeCount;
    public ArrayList<Episode> episodes = new ArrayList<Episode>();


    public Season(int seasonNo)
    {
        this.seasonNo = seasonNo;
        episodeCount = 0;
        episodes = new ArrayList<Episode>();
    }

    public void addEpisode(boolean episodeSeen)
    {
        episodeCount++;
        episodes.add(new Episode(episodeCount,episodeSeen));
    }

    public void deleteEpisode()
    {
        if(episodes.size() > 0)
        {
            episodes.remove(episodes.size()-1);
            episodeCount--;
        }
    }

    public void addMultipleUnSeenEpisodes(int count)
    {
        for(int i=0; i<count; i++){
            addEpisode(false);
        }
    }

    public int getSeasonNo(){
        return  seasonNo;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(seasonNo);
        dest.writeInt(episodeCount);
        dest.writeTypedList(episodes);
    }

    private Season(Parcel in){
        this.seasonNo = in.readInt();
        episodeCount = in.readInt();
        in.readTypedList(episodes, Episode.CREATOR);
    }

    public static final Parcelable.Creator<Season> CREATOR = new Parcelable.Creator<Season>() {
        public Season createFromParcel(Parcel in) {
            return new Season(in);
        }
        public Season[] newArray(int size) {
            return new Season[size];
        }
    };


}