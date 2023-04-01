/**
 * A model class for training record
 * 
 * @author Zixuan Zhu
 */
package model;

import java.io.Serializable;

public class Requirement implements Serializable {
	
	private int id;//course ID
	private static int IDGenerator =1;
	private String course;
	private String level;
	private String speciality;
	private Teacher assignedTeacher;



	public Requirement(String course, String level, String speciality) {
		this.id = IDGenerator;
		IDGenerator++;

		this.course = course;
		this.level = level;
		this.speciality = speciality;
	}

	public Requirement(int id, String course, String level, String speciality, Teacher teacher) {
		this.id = id;
		this.course = course;
		this.level = level;
		this.speciality = speciality;
		this.assignedTeacher = teacher;
	}

	
	public String toString() {
		String teacherInfo = "";
		if (assignedTeacher == null) {
			teacherInfo = "To be assigned";
		} else {
			teacherInfo = "" + assignedTeacher.getId();
		}

		return String.format("%-5d | %-30s | %-10s | %-20s | %-20s \n", id,course, level, speciality, teacherInfo);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id =id;
	} //I assume once ID is generated, it won't be changed

	public static int getIDGenerator() {
		return IDGenerator;
	}

	public static void setIDGenerator(int IDGenerator) {
		Requirement.IDGenerator = IDGenerator;
	}

	public String getCourse() {
		return course;
	}
	public void setCourse(String name) {
		this.course=name;
	}
	public Teacher getAssignedTeacher() {
		return assignedTeacher;
	}
	public void setAssignedTeacher(Teacher t) {
		this.assignedTeacher =t;
	}
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level=level;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality=speciality;
	}
}
