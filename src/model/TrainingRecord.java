/**
 * A model class for training record
 * 
 * @author Kai Ching Lo
 */
package model;

import java.io.Serializable;

public class TrainingRecord  implements Serializable {

	private static int idGenerator = 1;
	
    private int id = 0;
    private String content = "";
    private String location ="";
    private String trainingDate ="";
    private Teacher teacher = null;

    public TrainingRecord() {}
    public TrainingRecord(String content, String location, String training_date, Teacher teacher){
        this.content = content;
        this.location = location;
        this.trainingDate = training_date;
        this.teacher = teacher;
        this.id = idGenerator++;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(int idGenerator) {
        TrainingRecord .idGenerator = idGenerator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(String trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String toString(){

        return String.format("%-5s | %-25s | %-10s | %-15s | %-10s | %-20s", id, content, location, trainingDate, ""+teacher.getId(), teacher.getName() );
    }
}
