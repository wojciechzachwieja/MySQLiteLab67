package com.example.chart.mysqlite.model.object;

import java.util.List;

/**
 * Created by Wojtek on 2016-12-07.
 */

public class StudentModel {
    private long idStudent;
    private String name;
    private String surname;
    private List<GroupModel> groups;

    public StudentModel(long idStudent, String name, String surname, List<GroupModel> groups) {
        this.idStudent = idStudent;
        this.name = name;
        this.surname = surname;
        this.groups = groups;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<GroupModel> getGroups() {
        return groups;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGroups(List<GroupModel> groups) {
        this.groups = groups;
    }
}
