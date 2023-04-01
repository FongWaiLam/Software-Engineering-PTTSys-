/**
 * @author Kai Ching Lo
 * @author Fong Wai Lam
 */
import controller.AdminController;
import controller.Controller;
import controller.DirectorController;
import repository.RequirementList;
import repository.TeacherList;
import repository.TrainingRecordList;

import java.util.Scanner;

// Part Time Teacher System
public class PTTSystem {

    private Controller controller = null;

    private boolean isPowerOn = true;

    // Database
    DataReadWrite dataReadWrite = new DataReadWrite();
    private TeacherList teacherList = null;
    private TrainingRecordList trainingRecordList = null;
    private RequirementList requirementList = null;

    // Main Program
    public static void main(String[] args) {
        System.out.println("------------------ Welcome to Part Time Teacher System ------------------");
        PTTSystem pttSystem = new PTTSystem();

        pttSystem.initiate(); // read data

        while (pttSystem.isPowerOn) {
           pttSystem.run();   // run program logic
        }
        pttSystem.end();  // store the data before quit

    }

    /**
     * Functional cohesion;
     * initiate the system and read the data from the file:
     */
    public void initiate(){
        dataReadWrite.readRecords(this);
        dataReadWrite.mapRecords(this);
        
        DataPopulation dataPopulation = new DataPopulation();
        
        // If no initial data in database, populate some test data for illustration purpose
        if (teacherList == null) {
            teacherList = dataPopulation.popTeacherList();
        }
        if (trainingRecordList == null) {
            trainingRecordList = dataPopulation.popTrainingRecord();
        }
        if (requirementList == null) {
            requirementList = dataPopulation.popRequirementList();
        }
    }

    /**
     * run the system(strategy pattern);
     * Base on the selected role the different controller will be invoked
     * 1. select the user role
     * 2. get into the controller
     * 3. reset the controller after user complete the task
     */
    public void run(){

        roleSelection();
        if(controller == null) return;
        controller.run();

        //reset the controller after completed the task.
        if (controller != null && isPowerOn == true) {
            // Exit from controller
            controller = null;
        }
    }

    /**
     * Functional cohesion of storing data before end the system.
     * routine coupling, sure the sequence of serializing the data is correct,
     * but it is also a functional based method to store the data to the file.
     */
    public void end() {
        dataReadWrite.packRecords(this);
        dataReadWrite.storeRecords(this);
    }

    /**
     * functional cohesion:
     * store the logic for user to choosing the user role.
     */
    public void roleSelection () {
        boolean isRoleSelected = false;
        while (!isRoleSelected) {
            controller = null; // reset selection
            System.out.println(
                    "=============== Part Time Teacher System (Main Menu) ==============\n" +
                    "Please select your role to enter this system. (Enter 1 or 2)" +
                    "\n" + "(1) Class Director" +
                    "\n" + "(2) Admin" +
                    "\n" + "------------------------------------------------------------------------" +
                    "\n" + "(0) End System, please enter 0." +
                    "\n" + "Enter your option (e.g. \"1\"): ");
            Scanner scanner = new Scanner(System.in);
            try {

                int command = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                switch (command) {
                    case 1:
                        controller = new DirectorController(scanner, this.requirementList);
                        System.out.println("Selected Role: Class Director");

                        break;
                    case 2:
                        controller = new AdminController(this.requirementList, this.teacherList, this.trainingRecordList);
                        System.out.println("Selected Role: Admin");
                        break;
                    case 0:
                        isPowerOn = false;
                        return;
                }
                isRoleSelected = true;

            } catch (Exception e) {
                System.out.println("Invalid command");
            }
        }
    }


    /**
     * getter / setter
     * @return
     */
    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public boolean isPowerOn() {
        return isPowerOn;
    }

    public void setPowerOn(boolean powerOn) {
        isPowerOn = powerOn;
    }

    public TeacherList getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(TeacherList teacherList) {
        this.teacherList = teacherList;
    }

    public TrainingRecordList getTrainingRecordList() {
        return trainingRecordList;
    }

    public void setTrainingRecordList(TrainingRecordList trainingRecordList) {
        this.trainingRecordList = trainingRecordList;
    }

    public RequirementList getRequirementList() {
        return requirementList;
    }

    public void setRequirementList(RequirementList requirementList) {
        this.requirementList = requirementList;
    }
}
