package com.example.camflex.model;

public class UserRegisterRequestDto {
    private String uid;
    private String name;
    private String password;
    private String school;
    private String student_no;

    public UserRegisterRequestDto(String uid, String name, String password, String school, String student_no) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.school = school;
        this.student_no = student_no;
    }

    // Getter / Setter
    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }
    public String getStudent_no() { return student_no; }
    public void setStudent_no(String student_no) { this.student_no = student_no; }
}
