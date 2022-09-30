package edu.northeastern.numad22fa_jiyoonjeong;

import android.os.Parcel;
import android.os.Parcelable;

public class Link implements Parcelable {
    private final String name;

    private final String url;

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Link(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    //for arrays and creating from a parcel
    public static final Creator<Link> CREATOR = new Creator<Link>() {
        @Override
        public Link createFromParcel(Parcel in) {
            return new Link(in);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(url);
    }
}
