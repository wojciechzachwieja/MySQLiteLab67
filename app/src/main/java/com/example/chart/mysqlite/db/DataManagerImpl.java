package com.example.chart.mysqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import com.example.chart.mysqlite.model.object.GroupModel;
import com.example.chart.mysqlite.model.object.StudentModel;
import com.example.chart.mysqlite.table.object.Group;
import com.example.chart.mysqlite.table.object.GroupDAO;
import com.example.chart.mysqlite.table.object.Student;
import com.example.chart.mysqlite.table.object.StudentDAO;
import com.example.chart.mysqlite.table.object.StudentGroup;
import com.example.chart.mysqlite.table.object.StudentGroupDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojtek on 2016-12-07.
 */

public class DataManagerImpl implements DataManager {
    private final Context context;
    private ArrayList<Student> students;
    private ArrayList<Group> groups;
    private ArrayList<StudentGroup> studentGroups;


    public DataManagerImpl(Context context) {
        this.context = context;
        this.students = new StudentDAO(context).getStudents();
        this.groups = new GroupDAO(context).getGroups();
        this.studentGroups = new StudentGroupDAO(context).getStudentsGroup();
    }

    @Override
    public StudentModel getStudent(long id)
    {
        List<Long> idStudentModelGroups = getJoinTableStudentGroup(id);
        List<GroupModel> joinGroup = new ArrayList<>();
        getJoinTableGroup(idStudentModelGroups, joinGroup);
        Student student = new Student();
        student = getStudent(id, student);
        return new StudentModel(id, student.getName(), student.getSurname(), joinGroup);
    }

    private Student getStudent(long id, Student student) {
        for (Student x: students) {
            if(x.getIdStudent() == id){
                student = x;
                break;
            }
        }
        return student;
    }

    private void getJoinTableGroup(List<Long> idStudentModelGroups, List<GroupModel> joinGroup) {
        for (Long x: idStudentModelGroups) {
            for(Group z : groups){
                if(z.getIdGroup() == x){
                    joinGroup.add(new GroupModel(x, z.getName(), null));
                    break;
                }
            }
        }
    }

    @NonNull
    private List<Long> getJoinTableStudentGroup(long id) {
        List<Long> idStudentModelGroups = new ArrayList<>();
        for (StudentGroup x: studentGroups) {
            if(x.getIdStudent() == id){
                idStudentModelGroups.add(x.getIdGroup());
            }
        }
        return idStudentModelGroups;
    }

    @Override
    public GroupModel getGroup(long id) {
        List<Long> idStudentModelGroups = new ArrayList<>();
        getJoinTableStudentModel(id, idStudentModelGroups);
        List<StudentModel> joinStudent = new ArrayList<>();
        getJoinTableStudent(idStudentModelGroups, joinStudent);
        Group group = new Group();
        group = getGroup(id, group);
        return new GroupModel(id, group.getName(), joinStudent);
    }

    private Group getGroup(long id, Group group) {
        for (Group x: groups) {
            if(x.getIdGroup() == id){
                group = x;
                break;
            }
        }
        return group;
    }

    private void getJoinTableStudent(List<Long> idStudentModelGroups, List<StudentModel> joinStudent) {
        for (Long x: idStudentModelGroups) {
            for(Student z : students){
                if(z.getIdStudent() == x){
                    joinStudent.add(new StudentModel(x, z.getName(), z.getSurname(), null));
                    break;
                }
            }
        }
    }

    private void getJoinTableStudentModel(long id, List<Long> idStudentModelGroups) {
        for (StudentGroup x: studentGroups) {
            if(x.getIdGroup() == id){
                idStudentModelGroups.add(x.getIdStudent());
            }
        }
    }

    @Override
    public boolean saveTransaction(Student student, Group group) {
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        mySQLiteHelper.openWritableMode();
        SQLiteDatabase sqLiteDatabase = mySQLiteHelper.getDb();
        try {
            sqLiteDatabase.beginTransaction();
            new GroupDAO(context).save(sqLiteDatabase, group);
            new StudentDAO(context).save(sqLiteDatabase, student);
            StudentGroup studentGroup = new StudentGroup();
            studentGroup.setIdStudent(student.getIdStudent());
            studentGroup.setIdGroup(group.getIdGroup());
            new StudentGroupDAO(context).save(sqLiteDatabase, studentGroup);
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            //handling error
            e.printStackTrace();
        } finally {
            sqLiteDatabase.endTransaction();
        }
        return false;
    }
}
