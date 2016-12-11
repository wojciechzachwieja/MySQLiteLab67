package com.example.chart.mysqlite.db;

import com.example.chart.mysqlite.model.object.GroupModel;
import com.example.chart.mysqlite.model.object.StudentModel;
import com.example.chart.mysqlite.table.object.Group;
import com.example.chart.mysqlite.table.object.Student;

interface DataManager {
    StudentModel getStudent(long id);
    GroupModel getGroup(long id);
    boolean saveTransaction(Student student, Group group);
}
