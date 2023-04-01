import model.Requirement;
import model.Teacher;
import model.TrainingRecord;
import repository.RequirementList;
import repository.TeacherList;
import repository.TrainingRecordList;


/*
 * This class initialise the data in every lists and records with testing data.
 * @author Kai Ching Lo
 */

public class DataPopulation {
    private TeacherList teacherList = null;
    private TrainingRecordList trainingRecordList = null;
    private RequirementList requirementList = null;

    public DataPopulation(TeacherList teacherList, TrainingRecordList trainingRecordList, RequirementList requirementList){
        teacherList = popTeacherList();
        trainingRecordList = popTrainingRecord();
        requirementList = popRequirementList();
    }

    public DataPopulation(){};

    public TeacherList popTeacherList() {
        TeacherList tl = new TeacherList();
        Teacher t1 = new Teacher("Brandy", "Reader", "Machine Learning");
        Teacher t2 = new Teacher("Cindy", "Reader", "Image processing");
        Teacher t3 = new Teacher("Daisy", "Reader", "Machine Learning");
        Teacher t4 = new Teacher("Elaine", "Professor", "Software Optimization");
        Teacher t5 = new Teacher("Ford", "Professor", "Data Science");
        Teacher t6 = new Teacher("Gorge", "Lecturer", "Embedded System");
        Teacher t7 = new Teacher("Henry", "Lecturer", "Integrated Chip");
        Teacher t8 = new Teacher("Ian", "Associate Professor", "Cyber Security");
        Teacher t9 = new Teacher("Jeffery", "Lecturer", "Networking");
        Teacher t10 = new Teacher("Kelvin", "Lecturer", "HCI");


        tl.add(t1);
        tl.add(t2);
        tl.add(t3);
        tl.add(t4);
        tl.add(t5);
        tl.add(t6);
        tl.add(t7);
        tl.add(t8);
        tl.add(t9);
        tl.add(t10);

        return tl;
    }

    public TrainingRecordList popTrainingRecord(){
        TrainingRecordList tr = new TrainingRecordList();
        Teacher t1 = new Teacher("Brandy", "Reader", "Machine Learning");
        Teacher t2 = new Teacher("Cindy", "Reader", "Image processing");
        Teacher t3 = new Teacher("Daisy", "Reader", "Machine Learning");
        TrainingRecord tr1 = new TrainingRecord("Communication with youth", "Main hall", "15/03/2023", t1 );
        TrainingRecord tr2 = new TrainingRecord("Communication with youth", "Main hall", "15/03/2023", t2 );
        TrainingRecord tr3 = new TrainingRecord("Communication with youth", "Main hall", "15/03/2023", t3 );

        tr.add(tr1);
        tr.add(tr2);
        tr.add(tr3);

        return tr;
    }

    public RequirementList popRequirementList() {
        RequirementList rl = new RequirementList();
        Requirement r1 = new Requirement("Introduction to AI", "Professor", "Machine Learning");
        Requirement r2 = new Requirement("Networking Fundamental", "Lecturer", "Networking");
        Requirement r3 = new Requirement("Electronic engineering", "Reader", "Embedded System");
        Requirement r4 = new Requirement("Electronic engineering", "Reader", "Integrated Chip");
        Requirement r5 = new Requirement("Introduction to AI", "Professor", "Data Science");

        rl.add(r1);
        rl.add(r2);
        rl.add(r3);
        rl.add(r4);
        rl.add(r5);

        return rl;
    }

}
