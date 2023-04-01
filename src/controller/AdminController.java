/**

 */
package controller;

import model.Requirement;
import model.Teacher;
import model.TrainingRecord;
import repository.RequirementList;
import repository.TeacherList;
import repository.TrainingRecordList;

import java.util.Scanner;
/**
 * Admin controller:
 * 1. Teaching requirements list.
 * 		1.1 Match suitable teachers to teaching requirements list.(loop)
 * 		1.2 Go back to "Administrator".
 * 2. Training.
 * 		2.1 Create a new training.
 * 		2.2 Edit the training.
 * 		2.3 Go back to "Administrator".
 * 3. Training records
 * 4. Teacher list.
 * 0. Log out.
 * 
 * @author Yanning Jia
 * @author Fong Wai Lam
 */
/* variables */
public class AdminController implements Controller {

    private RequirementList requirementList;
    private TeacherList teacherList;
    private TrainingRecordList trainingRecordList;
    private TeacherSearch search ; // tool for searching teach from list

    /* methods */

    /**
     * the constructor intake the data set will be manipulated by the admin
     * @param requirementList
     * @param teacherList
     * @param trainingRecordList
     */
    public AdminController(RequirementList requirementList, TeacherList teacherList, TrainingRecordList trainingRecordList) {
        this.requirementList = requirementList;
        this.teacherList = teacherList;
        this.search = new TeacherSearch(teacherList);
        this.trainingRecordList = trainingRecordList;

    }


    /**
     *  perform different task according to user decision.
     * the state is shifted by switch().
     */
    public void run() {
        while(true){
            String question =
                    "---------------------- Admin's Menu ---------------------\n" +
                            "Please select the record you want to operate.\n" +
                            "(1) Teaching requirements list and Assignment\n"
                            + "(2) Arrange training to teacher\n"
                            + "(3) Display arranged trainings or training records\n"
                            + "(4) Display teacher list\n"
                            + "(0) Log out\n"
                            + "Enter your option (e.g. \"1\"): ";
            switch (userIntegerInput(question)) {
                case 1:// choose teaching requirements list
                    chooseRequirementsList();
                    break;
                case 2:// choose Training
                    arrangeTeacherToTraining();
                    break;
                case 3://Training list
                    System.out.println(trainingRecordList);
                    break;
                case 4://Teacher list
                    System.out.println(teacherList);
                    break;
                case 0://Log out
                    return;
                default:
                    System.out.println("Input error, please enter an option number, e.g., \"1\".");
                    return;
            }
        }
    }
    /*
     * about teaching requirements list---------------------------------------------------------------------
     */
    private void chooseRequirementsList() {
        System.out.println(requirementList);
        String question =
                "(1) Match suitable teacher for a teaching requirements list.\n" +
                        "(0) Go back to admin menu.\n" +
                        " Enter your option (e.g. \"1\"): ";
        switch (userIntegerInput(question)) {
            case 1:// teacher assignment
                teacherAssignment();
                break;
            case 0:// admin menu
                return;
            default:
                System.out.println("Input error, please enter an option number, e.g., \"1\".");
                return;
        }
    }

    private void teacherAssignment() {
        int selectedRequirementID = -1;
        Requirement selectedRequirement = null;
        String question = "";
        while (true) {
            question =
                    "------------------- Teacher Assignment-------------------\n" +
                            "Please enter the teaching requirement ID.\n" +
                            "(0) Cancel assignment, and go back to admin menu.\n" +
                            " Enter the teaching requirement ID or option (0) for cancel (e.g. \"4\"): ";
            selectedRequirementID = userIntegerInput(question);
            switch (selectedRequirementID) {
                case 0: // admin menu
                    return;
                default: // Entered teaching requirement ID
                    selectedRequirement = requirementList.read(selectedRequirementID);
                    if (selectedRequirement == null) {
                        continue;
                    }
            }
            break;
        }
       displayTeacherList(selectedRequirement);
        while (true) {
           question = "Enter the teacher ID your want to assign to this requirement (e.g. \"19\")\n" +
                    "(0) Cancel assignment, and go back to admin menu.\n" +
                    " Enter the teacher ID (e.g. \"2\") or option (0) for go back to Admin Menu: ";

            int selectedTeacherID = userIntegerInput(question);
            switch (selectedTeacherID) {
                case 0: // admin menu
                    return;
                default: // Entered teacher ID
                   Teacher selectedTeacher = teacherList.read(selectedTeacherID);
                    if (selectedTeacher == null) {
                        continue;
                    }
                    requirementList.update(selectedRequirementID, updateRequirement(selectedRequirement, selectedTeacher));
                    System.out.println("Teacher (ID: " + selectedTeacherID + ") is assigned to requirement (ID: " + selectedRequirementID + ")");
                    return;
            }
        }

    }

    private void displayTeacherList(Requirement selectedRequirement) {
        while (true) {
           String question = "Teaching requirement ID for teacher assignment: " + selectedRequirement.getId() + "\n" +
                    "Do you want to sort the list of teacher by specialty and level required by this requirement?\n" +
                    "(1) Sort by specialty and level of this selected requirement\n" +
                    "(2) Display full list of teacher" +
                    "(0) Go back to admin menu.\n" +
                    " Enter your option (e.g. \"1\"): ";
            switch (userIntegerInput(question)) {
                case 1: // Sort
                    TeacherSearch search = new TeacherSearch(teacherList);
                    System.out.println(search.searchTeacherByRequirement(selectedRequirement));
                    return;
                case 2: // Full List
                    System.out.println(teacherList);
                    return;
                case 0: // admin menu
                    return;
                default: // Invalid Input
                    System.out.println("Input error, please enter an option number, e.g., \"1\".");
                    continue;
            }
        }
    }

    // create the object with new requirement and pass to the update function
    // this method have encapsulated all the data need to update and avoiding data coupling that sending
    // too many parameter to the method at once when updated.
    private Requirement updateRequirement(Requirement requirement, Teacher teacher) {
        Requirement updateRequirement = new Requirement(
                requirement.getId(),
                requirement.getCourse(),
                requirement.getLevel(),
                requirement.getSpeciality(),
                teacher );

        return updateRequirement;
    }

    private void arrangeTeacherToTraining() {
        while (true) {
            String question =
                    "---------------- Trainings for Teachers -----------------\n" +
                    "Please select the operation.\n" +
                    "(1) Arrange teacher to a training and create a new record\n"
                +   "(2) Edit the arranged training\n"
                +   "(0) Go back to Admin Menu.\n"
                +   "Enter your option (e.g. \"1\"): ";
            switch (userIntegerInput(question)) {
                case 1: // New training record
                    createTraining();
                    continue;
                case 2: // Edit current record
                    editTraining();
                    continue;
                case 0: // admin menu
                    return;
                default: // Invalid Input
                    System.out.println("Input error, please enter an option number, e.g., \"1\".");
                    continue;
            }
        }
    }


    private void createTraining() {
        String question =
                "----------------- New Trainings Record ------------------\n" +
                        "Please enter training date, location and training content.\n"
                        + "(0) Cancel creation of new training record.\n";
        System.out.println(question);
        //setNewTraining();


        // collecting the input from user
        String content = "";
        String location ="";
        String training_date ="";
        Teacher teacher = null;
        String tempStringInput = null;
        int tempIntInput = 0;


        //1 Collect the content
        tempStringInput = userStringInput("Please enter the training content: ", 20);
        if (tempStringInput == null) {
            return;
        }
        content = tempStringInput;

        // 2. Location
        question = "Please enter the training location: ";
        tempStringInput = userStringInput(question, 10);
        if (tempStringInput == null) {
            return;
        }
        location = tempStringInput;

        // 3. Training date
        question = "Please enter the training date: ";
        tempStringInput = userStringInput(question, 10);
        if (tempStringInput == null) {
            return;
        }
        training_date = tempStringInput;

        // 4. Teacher
        question = "Please enter the teacher's ID.";
        tempIntInput = userIntegerInput(question);
        if (tempIntInput == 0) {
            return;
        }

        teacher = search.searchTeacherByID((tempIntInput));

        // New a training
        TrainingRecord newTraining = new TrainingRecord(content, location, training_date, teacher);
        trainingRecordList.add(newTraining);

        System.out.println(
                "------------------ New Trainings Record Created -------------------\n" +
  String.format("%-5s | %-25s | %-10s | %-15s | %-20s\n", "id", "content", "location", "training_date", "teacher" )
               +"-------------------------------------------------------------------\n" +
                        newTraining);

    }


    private void editTraining() {
        TrainingRecord training = null;

        System.out.println(trainingRecordList);
       String question = "Please enter ID of the training to be edited.";

       int selectedTrainingId = userIntegerInput(question);
        training = trainingRecordList.read(selectedTrainingId);

        if(training == null){
            System.out.println("Selected training does not exist");
            return;
        }
        System.out.println(training);
        chooseEditMethod(training);
    }

    private void chooseEditMethod(TrainingRecord trainingRecord) {
        String question = "(1) Update this training record, enter the item and content to be modified.\n"
                + "(2) Delete this training records.\n"
                //+ "(3) Arrange teachers for this training.\n"
               // + "(4) Re-choose training to be edited.\n"
                + "(3) Back to previous.\n";


        switch (userIntegerInput(question)) {
            case 1://Modify this training, enter the item and content to be modified.
                updateTrainingRecord(trainingRecord);
                break;
            case 2://Delete this training.
                trainingRecordList.delete(trainingRecord.getId());
                break;
            case 3://Go back to "chooseTraining".
                System.out.println("Back to previous");
                break;
            default:
                System.out.println("Input error, please enter the option number and content, e.g., \"1 location learning-hub\"");
        }
    }

    private void updateTrainingRecord(TrainingRecord trainingRecord) {


        String question = "Enter content (or leave blank to keep existing value \"" + trainingRecord.getContent() + "\"): ";
        String content = userStringInput(question, 20);
        if (!content.isEmpty()) {
            trainingRecord.setContent(content);
        }

        question = "Enter location (or leave blank to keep existing value \"" + trainingRecord.getLocation() + "\"): ";
        String location = userStringInput(question, 10);
        if (!location.isEmpty()) {
            trainingRecord.setLocation(location);
        }

        question = "Enter new date (or leave blank to keep existing value \"" + trainingRecord.getTrainingDate() + "\"): ";
        String date = userStringInput(question, 20);
        if (!date.isEmpty()) {
            trainingRecord.setTrainingDate(date);
        }

        Teacher existingTeacher = trainingRecord.getTeacher();
        Integer record_id = trainingRecord.getId();
        TrainingRecord newTrainingRecord = new TrainingRecord(content, location, date, existingTeacher);

        trainingRecordList.update(record_id, newTrainingRecord);
        System.out.println("Record updated successfully.");

    }


    /**
     * this method will handle the user input in integer type, it will handle the input with validation
     * ensure the input from user is correct.
     * @param question
     * @return
     */
    private int userIntegerInput(String question) {
        int option = -1;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(question);
            //System.out.println("(0) Cancel operation and return to previous menu");
            try {
                scanner = new Scanner(System.in);
                option = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (option == 0) {
                    return 0;
                }
            } catch (Exception e) {
                System.out.println("Input error, please enter numbers.");
                continue;
            }
            return option;
        }
    }


    /**
     * this method will handle the user input in String type, it will handle the input with validation
     * ensure the input from user is correct.
     * @param question
     * @return
     */
    private String userStringInput(String question, int size) {

        String stringInput = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(question + "(Within " + size + " character(s))");
            //System.out.println("(0) Cancel operation and return to previous menu");
            try {
                scanner = new Scanner(System.in);
                stringInput = scanner.nextLine();
                if (stringInput.equals("0")) {
                    return null;
                }
                if (stringInput.length() > size) {
                    System.out.println("Input error, please enter less than or equals to " + size + "character(s).");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Input error, please enter characters.");
                continue;
            }
            return stringInput;
        }
    }

}
