/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

/**
 *
 * @author aadikumar
 */
public class TeacherData {
    private int teacher_id;

    private String first_name;
    
    private String last_name;

    private String father_name;

    private String email;

    private String number;

    private String dob;

    private String gender;

    private String department;

    private String address;

    private String qualification;

    private String joined;
    
    private String left;
    
    public TeacherData(int teacher_id, String first_name, String last_name, String father_name, String email, String number, String dob, String gender, String department, String address, String qualification, String joined, String left) {
        this.teacher_id = teacher_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.email = email;
        this.number = number;
        this.dob = dob;
        this.gender = gender;
        this.department = department;
        this.address = address;
        this.qualification = qualification;
        this.joined = joined;
        this.left = left;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public String getAddress() {
        return address;
    }

    public String getQualification() {
        return qualification;
    }

    public String getJoined() {
        return joined;
    }

    public String getLeft() {
        return left;
    }
    
    
}
