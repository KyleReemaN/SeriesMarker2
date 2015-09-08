package de.augsburg.seriesmarker2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import android.os.Parcel;
import android.os.Parcelable;

public class Series implements Serializable, Parcelable {

    private int seasonCount;
    public ArrayList<Season>seasons = new ArrayList<Season>();
    private String seriesName;
    // lastSeenSeason is here a season which has at least one episode that is seen
    private int lastSeenSeason = 1;
    private int lastSeenEpisode = 1;

    public Series(String seriesName)
    {
        this.seriesName = seriesName;
        seasonCount = 0;
        seasons = new ArrayList<Season>();
    }

    public void addSeason(){
        seasonCount++;
        seasons.add(new Season(seasonCount));
    }

    public void deleteSeason(){
        seasons.remove(seasons.size()-1);
        seasonCount--;
    }

    public String getSeriesName()
    {
        return  seriesName;
    }

    public int getLastSeenSeason() {return lastSeenSeason;}

    public int getLastSeenEpisode() {return lastSeenEpisode;}

    public void setLastSeenSeason(int lastSeenSeason) {this.lastSeenSeason=lastSeenSeason;}

    public void setLastSeenEpisode(int lastSeenEpisode) {this.lastSeenEpisode=lastSeenEpisode;}

    public void changeSeriesName(String seriesName){
        this.seriesName = seriesName;
    }


    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(seasonCount);
        dest.writeTypedList(seasons);
        dest.writeString(seriesName);
        dest.writeInt(lastSeenSeason);
        dest.writeInt(lastSeenEpisode);
    }

    private Series(Parcel in){
        seasonCount = in.readInt();
        in.readTypedList(seasons, Season.CREATOR);
        seriesName = in.readString();
        lastSeenSeason = in.readInt();
        lastSeenEpisode = in.readInt();
    }

    public static final Parcelable.Creator<Series> CREATOR = new Parcelable.Creator<Series>() {
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }
        public Series[] newArray(int size) {
            return new Series[size];
        }
    };

    public String toString(){
        return seriesName;
    }


}
