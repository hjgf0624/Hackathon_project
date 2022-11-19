package kr.ac.jbnu.se.hackathon_project;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String student_Number;
    private String password;
    private String name;
    private String department;
    private int score;
    public boolean ismanager;

    public UserInfo(String student_Number, String password, String name, String department, int score, boolean ismanager)
    {
        this.student_Number = student_Number;
        this.password = password;
        this.name = name;
        this.department = department;
        this.score = score;
        this.ismanager = ismanager;
    }



    public UserInfo(){

    }
    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getStudent_Number() {
        return student_Number;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStudent_Number(String student_Number) {
        this.student_Number = student_Number;
    }

    public int getScore(){return score;}

    public  void  setScore(int score){this.score = score;}

    public void setManager(boolean ismanager){this.ismanager = ismanager;}

    public boolean getIsmanager(){return ismanager;}

}
