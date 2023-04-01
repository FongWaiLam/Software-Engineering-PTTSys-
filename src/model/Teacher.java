/**
 * A model class for training record
 *  
 * @author Ying Ting Liu
 */
package model;

import java.io.Serializable;

public class Teacher implements Serializable {

	private static int idGenerator = 1;

	private int id;
	private String name;
	private String level; // grade
	private String speciality;

	public Teacher(String name, String level, String speciality) {
		this.id = idGenerator++;
		this.name = name;
		this.level = level;
		this.speciality = speciality;
	}
	
	public Teacher(int id, String level, String speciality, String trainingStatus) {
		this.id = id;
		this.level = level;
		this.speciality = speciality;
	}
	
	public Teacher() {}

	public static int getIdGenerator() {
		return idGenerator;
	}

	public static void setIdGenerator(int idGenerator) {
		Teacher.idGenerator = idGenerator;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("%-5s | %-10s | %-20s |  %-20s\n", id, name,  level, speciality);

	}
	
	
}
