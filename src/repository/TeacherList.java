package repository;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Teacher;

/**
 * This repository class provides the ability to manipulate the components with abstract method declared in
 * Editable interface which encapsulate the logic for accessing data in the model class
 * and provide better maintainability and decoupling the infrastructure from the model.
 * 
 * This applied repository pattern for the data structure allow user to add, update, delete and read
 * the data as basic control.
 *
 * this container class stored the Teacher object.
 *
 * @author Ying Ting Liu
 */

public class TeacherList implements Editable<Teacher>, Serializable {
	
	private List<Teacher> teachers;
    
    public TeacherList() {
        this.teachers = new ArrayList<>();
    }    

    // Create operation
    @Override
    public void add(Teacher teacher) {
        teachers.add(teacher);
    }

    // Read operation
    @Override
    public Teacher read(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    // Update operation
    @Override
    public void update(int id, Teacher teacher) {
    	
        Teacher teacherToUpdate = read(id);
        
        teacherToUpdate.setLevel(teacher.getLevel());
        teacherToUpdate.setSpeciality(teacher.getSpeciality());
        
        teachers.set(teachers.indexOf(teacherToUpdate), teacherToUpdate);       
    }

    // Delete operation
    @Override
    public void delete(int id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.remove(i);
                return;
            }
        }
    }
    
    public List<Teacher> getTeachers() {
        return teachers;
    }
    
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================= Teacher List ==========================\n");
        sb.append(String.format("%-5s | %-10s | %-20s |  %-20s\n", "ID" , "Name" ,  "Level" , "Speciality"));
        sb.append("-----------------------------------------------------------------\n");
        for (Teacher teacher : teachers) {
            sb.append(teacher);
        }
        sb.append("========================= End of Record =========================\n");
        return sb.toString();
    }    

}
