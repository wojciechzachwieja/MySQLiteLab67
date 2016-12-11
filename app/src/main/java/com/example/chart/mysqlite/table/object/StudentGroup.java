package com.example.chart.mysqlite.table.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wojtek on 2016-12-07.
 */

public class StudentGroup implements Parcelable {
    private long idStudentGroup;
    private long idStudent;
    private long idGroup;

    public StudentGroup() {
        super();
    }
    
    public StudentGroup(Parcel in) {
        super();
        this.idStudentGroup = in.readLong();
        this.idStudent = in.readLong();
        this.idGroup = in.readLong();
    }

    public long getIdStudentGroup() {
        return idStudentGroup;
    }

    public void setIdStudentGroup(long idStudentGroup) {
        this.idStudentGroup = idStudentGroup;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    public long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(long idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public String toString() {
        return "StudentGroup: " + idGroup + " " + idStudent + " " +
                idStudentGroup;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)idStudent;
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
        StudentGroup other = (StudentGroup) obj;
        if (idStudent != other.idStudent)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(idStudentGroup);
        parcel.writeLong(idStudent);
        parcel.writeLong(idGroup);
    }

    public static final Parcelable.Creator<StudentGroup> CREATOR = new Parcelable.Creator<StudentGroup>() {
        public StudentGroup createFromParcel(Parcel in) {
            return new StudentGroup(in);
        }

        public StudentGroup[] newArray(int size) {
            return new StudentGroup[size];
        }
    };
}
