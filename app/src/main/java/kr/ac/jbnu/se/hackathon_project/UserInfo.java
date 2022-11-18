package kr.ac.jbnu.se.hackathon_project;

public class UserInfo {
    private String student_Number;
    private String password;
    private String name;
    private String department;

    public UserInfo(String student_Number, String password, String name, String department)
    {
        this.student_Number = student_Number;
        this.password = password;
        this.name = name;
        this.department = department;
    }

    public UserInfo(){}

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



}
