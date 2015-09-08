package de.augsburg.seriesmarker2;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tobias on 07.09.2015.
 */
public class Episode implements Parcelable, Serializable{

    private int episodeNo;
    private boolean episodeSeen;

    public Episode(int episodeNo, boolean episodeSeen){
        this.episodeNo = episodeNo;
        this.episodeSeen = episodeSeen;
    }

    public void setEpisodeSeen(boolean episodeSeen){
        this.episodeSeen = episodeSeen;
    }

    public int getEpisodeNo(){
        return episodeNo;
    }

    public boolean getEpisodeSeen(){
        return episodeSeen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(episodeNo);
        dest.writeByte((byte) (episodeSeen ? 1 : 0));
    }

    private Episode(Parcel in){
        episodeNo = in.readInt();
        episodeSeen = in.readByte() != 0;
    }


    public static final Parcelable.Creator<Episode> CREATOR = new Parcelable.Creator<Episode>() {
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };


}
