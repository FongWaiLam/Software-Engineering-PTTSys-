/**
 * @author Ruoyu Wang
 */
package controller;

import java.util.Scanner;
import model.Requirement;
import repository.RequirementList;

public class DirectorController implements Controller {

    private Scanner scanner;
    private RequirementList requirementList;

    public DirectorController(Scanner scanner, RequirementList requirementList) {
        this.scanner = scanner;
        this.requirementList = requirementList;
    }

    public void run() {
        while (true) {
            // Display menu options
            System.out.println("-------------------- Director's Menu --------------------\n" +
                               "Please select the operation. (e.g. \"1\")");
            System.out.println("(1) Display all requirements");
            System.out.println("(2) Add a requirement");
            System.out.println("(3) Update a requirement");
            System.out.println("(4) Delete a requirement");
            System.out.println("(0) Log out");

            // Get user input
            System.out.print("Enter your option (e.g. \"1\"): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Process user choice
            switch (choice) {
                case 1:
                    displayAllRequirements();
                    break;
                case 2:
                    addRequirement();
                    break;
                case 3:
                    updateRequirement();
                    break;
                case 4:
                    deleteRequirement();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void displayAllRequirements() {
        System.out.println(requirementList);
    }

    private void addRequirement() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter course name: ");
        String course = scanner.nextLine();

        System.out.print("Enter required level: ");
        String level = scanner.nextLine();

        System.out.print("Enter required speciality: ");
        String speciality = scanner.nextLine();

        Requirement requirement = new Requirement(course, level, speciality);
        requirementList.add(requirement);

        System.out.println("Requirement added successfully.");
    }

    private void updateRequirement() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter requirement ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Requirement requirement = requirementList.read(id);
        if (requirement == null) {
            System.out.println("Requirement not found.");
            return;
        }

        System.out.print("Enter course name (or leave blank to keep existing value \"" + requirement.getCourse() + "\"): ");
        String course = scanner.nextLine();
        if (!course.isEmpty()) {
            requirement.setCourse(course);
        }

        System.out.print("Enter required level (or leave blank to keep existing value \"" + requirement.getLevel() + "\"): ");
        String level = scanner.nextLine();
        if (!level.isEmpty()) {
            requirement.setLevel(level);
        }

        System.out.print("Enter required speciality (or leave blank to keep existing value \"" + requirement.getSpeciality() + "\"): ");
        String speciality = scanner.nextLine();
        if (!speciality.isEmpty()) {
            requirement.setSpeciality(speciality);
        }

        requirementList.update(id, requirement);

        System.out.println("Requirement updated successfully.");
    }

    private void deleteRequirement() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter requirement ID to delete: ");
        int id = scanner.nextInt();

        requirementList.delete(id);

        System.out.println("Requirement deleted successfully.");
    }

//    testing code
//    public static void main(String[] args){
//        RequirementList rl = new RequirementList();
//        Requirement r1 = new Requirement("AP", "Reader", "CS");
//        Requirement r2 = new Requirement("SE", "Reader", "CS");
//        Requirement r3 = new Requirement("CS", "Reader", "CS");
//        Requirement r4 = new Requirement("DB", "Professor", "CS");
//        Requirement r5 = new Requirement("ECS", "Reader", "CS");
//        Requirement r6 = new Requirement("SN", "Reader", "CS");
//
//        rl.add(r1);
//        rl.add(r2);
//        rl.add(r3);
//        rl.add(r4);
//        rl.add(r5);
//        rl.add(r6);
//
//        DirectorController dc = new DirectorController(rl);
//        dc.run();
//
//
//    }


    // abandon code
    /*private RequirementList requirementList;
    static int option;
    static int content;
    static String input;
    static Scanner s1;
    static Scanner s2;

    public DirectorController() {
        requirementList = new RequirementList();
    }
    public static void directorController() {
    	System.out.println("1. Create a teaching requirement.\r\n"
    			+ "2. Check requirementlist.\r\n"
    			+ "3. Update a teaching requirement.\r\n"
    			+ "4. Delete a teaching requirement.\r\n"
    			+ "Please enter an option number, e.g., \"1\".");
    	s1 = new Scanner(System.in);
    	input = s1.nextLine();
    	s2 = new Scanner(input);
    	option = s2.nextInt();
    	switch(option) {
    	case 1:
    		createRequirement();
    		break;
    	case 2:
    		checkRequirementList();
    		break;
    	case 3:
    		editRequirement();
    		break;
    	case 4:
    		deleteRequirement();
    		break;
    	}
    }

    public static void createRequirement() {
    	System.out.println("Please enter the course name, the required level and speciality, seprated by \" \", e.g., \"physic intermediate physic\"");
    	s1 = new Scanner(System.in);
    	String name = s1.next();
    	String level = s1.next();
    	String speciality = s1.next();
        Requirement requirement = new Requirement(name, level, speciality);
        requirementList.add(requirement);
    }

    public static void checkRequirementList() {
        System.out.println(requirementList.tostring());
    }
    public static void editRequirement() {
    	System.out.println("Please enter the ")
    }
*/

}
