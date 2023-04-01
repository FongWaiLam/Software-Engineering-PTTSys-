/**
 * An additional functional class for the teacher list.
 * 
 * The methods allows the using to filter the teacher list by the requirements and
 * search a teacher object by providing an ID.
 * 
 * @author Kai Ching Lo
 * @author Fong Wai Lam
 * 
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import model.Requirement;
import model.Teacher;
import repository.TeacherList;

public class TeacherSearch {
    TeacherList teacherList = null;

    public TeacherSearch(TeacherList teacherList){
        this.teacherList = teacherList;
    }

    /**
     *
     * let user input the requirement and search the teacher accordingly
     * @return Teacher
     */
    /**
     * search the teacher fulfil the requirement of the input parameter
     * @param requirement
     * @return List<Teacher>
     */
    public List<Teacher> searchTeacherByRequirement(Requirement requirement){
        String specialty = requirement.getSpeciality();
        String level = requirement.getLevel();
        List<Teacher> teachers = teacherList.getTeachers();
        ArrayList teachersMeetRequriement = new ArrayList<>();

        for(Teacher teacher : teachers){
            if(!specialty.equals(teacher.getSpeciality())){ // check the specialty
                continue; // skip if not meet
            }

            if(!level.equals(teacher.getLevel())) { // check the level
                continue;
            }

            // add the teacher to the list of fulfill requriement.
            teachersMeetRequriement.add(teacher);
        }
        return teachersMeetRequriement;
    }


    /**
     * search the teacher by ID
     * @param id
     * @return Teacher
     */
    public Teacher searchTeacherByID(Integer id){
        return teacherList.getTeachers().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

}

