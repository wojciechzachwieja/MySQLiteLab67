package com.example.chart.mysqlite.table.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wojtek on 2016-12-07.
 */

public class Student implements Parcelable {
    private long idStudent;
    private String name;
    private String surname;

    public Student() {
        super();
    }

    public Student(Parcel in) {
        super();
        this.idStudent = in.readLong();
        this.name = in.readString();
        this.surname = in.readString();
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
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
        Student other = (Student) obj;
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
        parcel.writeLong(getIdStudent());
        parcel.writeString(getName());
        parcel.writeString(getSurname());
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

}
