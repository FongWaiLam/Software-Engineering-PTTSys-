package repository;

import model.TrainingRecord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This repository class provides the ability to manipulate the components with abstract method declared in
 * Editable interface which encapsulate the logic for accessing data in the model class
 * and provide better maintainability and decoupling the infrastructure from the model.
 * 
 * This applied repository pattern for the data structure allow user to add, update, delete and read
 * the data as basic control.
 *
 *
 * this container class stored the teaching requirement object.
 * @author Kai Ching Lo
 *
 */
public class TrainingRecordList implements Editable<TrainingRecord> , Serializable {
    
	private ArrayList<TrainingRecord> trainings = new ArrayList<>();

    public TrainingRecordList(){

    }

    public ArrayList<TrainingRecord> getTrainingList() {
        return trainings;
    }
    public void setTrainingList(ArrayList<TrainingRecord> trainings) {
        this.trainings = trainings;
    }

    // adding new training item to the list
    @Override
    public void add(TrainingRecord training) {
        trainings.add(training);
    }

    // return the training object according to id
    @Override
    public TrainingRecord read(int id) {
        for (TrainingRecord training : trainings) {
            if (training.getId() == id) {
                return training;
            }
        }
        return null;
    }

    // update the existing training record
    @Override
    public void update(int id, TrainingRecord training) {

        TrainingRecord trainingToUpdate = read(id);

        trainingToUpdate.setId(training.getId());
        trainingToUpdate.setContent(training.getContent());
        trainingToUpdate.setLocation(training.getLocation());
        trainingToUpdate.setTrainingDate(training.getTrainingDate());

        trainings.set(trainings.indexOf(trainingToUpdate), trainingToUpdate);
    }

    // remove existing training according to id selected
    @Override
    public void delete(int id) {
        for (int i = 0; i < trainings.size(); i++) {
            if (trainings.get(i).getId() == id) {
                trainings.remove(i);
                return;
            }
        }
    }

    public String toString(){
        String output = "================================ Training Record ================================\n";
        output += String.format("%-5s | %-25s | %-10s | %-15s | %-10s | %-20s\n", "ID", "Content", "Location", "Training Date", "Teacher ID", "Teacher" );
        output +=       "---------------------------------------------------------------------------------\n";
        for(TrainingRecord training : trainings){
            output += training + "\n";
        }
        output +=       "================================ End of Record ================================\n";
        return output;
    }
}





