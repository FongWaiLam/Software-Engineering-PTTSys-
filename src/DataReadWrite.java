import model.Teacher;
import model.TrainingRecord;
import model.Requirement;
import repository.Editable;
import repository.RequirementList;
import repository.TeacherList;
import repository.TrainingRecordList;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/** Database - Map<String, Editable> classNameToInstances
 * TeacherList : class instance
 * TrainingList : class instance
 * RequirementList : class instance
 *
 * classNameToIDGenerators
 * Teacher : IDGenerator
 * Training : IDGenerator
 * Requirement : IDGenerator
 * @author Fong Wai Lam
 */

public class DataReadWrite {

    private final String fileAddress = "databaseRecords.tmp";
    private Map<String, Editable> classNameToRecords = null;
    private Map<String, Integer> classNameToIDGenerators = null;

    /**
     * This method simply store the byte data into a file
     * @param pttSystem
     */
    public void storeRecords(PTTSystem pttSystem) {
        packRecords(pttSystem);
        try (
                FileOutputStream fos = new FileOutputStream(fileAddress);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(classNameToRecords);
            oos.writeObject(classNameToIDGenerators);
        } catch (IOException e2) {
        }

    }

    /**
     * serialize the object into byte
     * @param pttSystem
     */
    public void packRecords(PTTSystem pttSystem) {
        // Get a new space to save updated records
        classNameToRecords = new HashMap<>();
        classNameToIDGenerators = new HashMap<>();

        classNameToRecords.put("TeacherList", pttSystem.getTeacherList());
        classNameToRecords.put("TrainingRecordList", pttSystem.getTrainingRecordList());
        classNameToRecords.put("RequirementList", pttSystem.getRequirementList());

        classNameToIDGenerators.put("Teacher", Teacher.getIdGenerator());
        classNameToIDGenerators.put("Training", TrainingRecord.getIdGenerator());
        classNameToIDGenerators.put("Requirement", Requirement.getIDGenerator());

    }

    /**
     * this method simply read the byte data from the file to java
     * and then it will pass the byte data to mapper to deserialize into object
     * @param pttSystem
     * @return
     */
    public boolean readRecords(PTTSystem pttSystem) {
        try (
                FileInputStream fis = new FileInputStream(fileAddress);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            classNameToRecords = (Map<String, Editable>)ois.readObject();
            classNameToIDGenerators = (HashMap<String, Integer>)ois.readObject();
        } catch (IOException | ClassNotFoundException e2) {
        }

        mapRecords(pttSystem);

        if (classNameToRecords != null && classNameToIDGenerators != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * deseriazlize the byte from the file and mapped to the corresponding object
     * @param pttSystem
     */
    public void mapRecords(PTTSystem pttSystem) {
        if (classNameToRecords != null) {
            pttSystem.setTeacherList((TeacherList) classNameToRecords.get("TeacherList"));
            pttSystem.setTrainingRecordList((TrainingRecordList) classNameToRecords.get("TrainingRecordList"));
            pttSystem. setRequirementList((RequirementList) classNameToRecords.get("RequirementList"));
        }

        if (classNameToIDGenerators != null) {
            Teacher.setIdGenerator(classNameToIDGenerators.get("Teacher"));
            TrainingRecord.setIdGenerator(classNameToIDGenerators.get("Training"));
            Requirement.setIDGenerator(classNameToIDGenerators.get("Requirement"));
        }
    }


    /**
     * getter/ setter
     * @return
     */
    public String getFileAddress() {
        return fileAddress;
    }

    public Map<String, Editable> getClassNameToRecords() {
        return classNameToRecords;
    }

    public void setClassNameToRecords(Map<String, Editable> classNameToRecords) {
        this.classNameToRecords = classNameToRecords;
    }

    public Map<String, Integer> getClassNameToIDGenerators() {
        return classNameToIDGenerators;
    }

    public void setClassNameToIDGenerators(Map<String, Integer> classNameToIDGenerators) {
        this.classNameToIDGenerators = classNameToIDGenerators;
    }

}
