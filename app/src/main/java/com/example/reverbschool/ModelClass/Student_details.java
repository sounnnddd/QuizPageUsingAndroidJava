package com.example.reverbschool.ModelClass;

public class Student_details {
    //fields
    String fname, sname, username, password, section, sexOfStudent;
    int classOfStudent, rno, age;

    //Constructors
    public Student_details() {
        fname = "";
        sname = "";
        classOfStudent = 0;
        section = "";
        sexOfStudent = "";
        username = "";
        password = "";
        rno = 0;
        age = 0;
    }

    public Student_details(String fname, String sname, int classOfStudent, String section, String sexOfStudent,
                           String username, String password, int rno, int age) {
        this.fname = fname;
        this.sname = sname;
        this.classOfStudent = classOfStudent;
        this.section = section;
        this.sexOfStudent = sexOfStudent;
        this.username = username;
        this.password = password;
        this.rno = rno;
        this.age = age;
    }

    //set
    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setClassOfStudent(int cclass) {
        this.classOfStudent = cclass;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setSexOfStudent(String x) {
        this.section = x;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //get
    public String getFname() {
        return this.fname;
    }

    public String getSname() {
        return this.sname;
    }

    public int getClassOfStudent() {
        return this.classOfStudent;
    }

    public String getSection() {
        return this.section;
    }

    public int getRno() {
        return this.rno;
    }

    public String getSexOfStudent() {
        return this.sexOfStudent;
    }

    public int getAge() {
        return this.age;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }
}
