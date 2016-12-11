package com.example.chart.mysqlite.table.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wojtek on 2016-12-07.
 */

public class Group implements Parcelable {
    private long idGroup;
    private String name;

    public Group() {
        super();
    }

    public Group(Parcel in) {
        this.idGroup = in.readLong();
        this.name = in.readString();
    }

    @Override
    public String toString() {
        return "GroupModel: " + name;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)idGroup;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        if (idGroup != other.idGroup)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(idGroup);
        parcel.writeString(name);
    }

    public static final Parcelable.Creator<Group> CREATOR = new Parcelable.Creator<Group>() {
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(long idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
