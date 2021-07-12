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
public class StudentData {
    private int stu_id;

    private String first_name;
    
    private String last_name;

    private String father_name;

    private String email;

    private String number;

    private String dob;

    private String gender;

    private String department;

    private String address;

    private int semester;

    private String section;
    
    private String joined;
    
    private String left;

    public StudentData(int stu_id, String first_name, String last_name, String father_name, String email, String number, String dob, String gender, String department, String address, String section, int semester, String joined, String left) {
        this.stu_id = stu_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.email = email;
        this.number = number;
        this.dob = dob;
        this.gender = gender;
        this.department = department;
        this.address = address;
        this.semester = semester;
        this.section = section;
        this.joined = joined;
        this.left = left;
    }

    public StudentData(int stu_id, String first_name, String last_name, String father_name, String email, String gender) {
        this.stu_id = stu_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.email = email;
        this.gender = gender;
    }
    
    

    public int getStu_id() {
        return stu_id;
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

    public int getSemester() {
        return semester;
    }

    public String getSection() {
        return section;
    }

    public String getJoined() {
        return joined;
    }

    public String getLeft() {
        return left;
    }

    
    
    

    
    
}
