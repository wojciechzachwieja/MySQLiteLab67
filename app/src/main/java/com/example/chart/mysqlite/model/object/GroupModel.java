package com.example.chart.mysqlite.model.object;

import java.util.List;

/**
 * Created by Wojtek on 2016-12-07.
 */

public class GroupModel {
    long idGroup;
    String name;
    List<StudentModel> students;

    public GroupModel(long idGroup, String name, List<StudentModel> students) {
        this.idGroup = idGroup;
        this.name = name;
        this.students = students;
    }

    public long getIdGroup() {
        return idGroup;
    }

    public String getName() {
        return name;
    }

    public List<StudentModel> getStudents() {
        return students;
    }

    public void setIdGroup(long idGroup) {
        this.idGroup = idGroup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<StudentModel> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return name;
    }
}
