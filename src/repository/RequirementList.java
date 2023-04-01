package repository;

import model.Requirement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This repository class provides the ability to manipulate the components with abstract method declared in
 * Editable interface which encapsulate the logic for accessing data in the model class
 * and provide better maintainability and decoupling the infrastructure from the model.
 * 
 * This applied repository pattern for the data structure allow user to add, update, delete and read
 * the data as basic control.
 *
 * This class stored all of the teaching requirement objects.
 * 
 * @author Zixuan Zhu
 */
public class RequirementList implements Editable<Requirement>,Serializable{
	
	private List<Requirement> RequirmentList;
	public RequirementList() {
		this.RequirmentList=new ArrayList<Requirement>();
	}

	@Override
	public void add(Requirement r) {
		RequirmentList.add(r);
	}

	@Override
	public Requirement read(int id) {
		for(Requirement r:RequirmentList) {
			if(r.getId()==id) {
				return r;
			}
		}
		return null;
	}

	@Override
	public void update(int id, Requirement o) {

		Requirement requirementToUpdate = read(id);
		requirementToUpdate.setCourse(o.getCourse());
		requirementToUpdate.setAssignedTeacher(o.getAssignedTeacher());
		requirementToUpdate.setSpeciality(o.getSpeciality());
		requirementToUpdate.setLevel(o.getLevel());

	}

	@Override
	public void delete(int ID) {
		Requirement deleteRequirement = null;
		for(Requirement r:RequirmentList) {

			if(r.getId()==ID) {
				deleteRequirement = r;
			}
		}

		if(deleteRequirement != null){
			RequirmentList.remove(deleteRequirement);
		}
	}
	
	public List<Requirement> getList(){
		return RequirmentList;
	}
	
	public void setList(List<Requirement> RequirmentList) {
		this.RequirmentList=RequirmentList;
	}
	
	public String toString() {
		String display= "========================================= Requirement List =========================================\n";
		display +=  String.format("%-5s | %-30s | %-10s | %-20S | %-20s \n", "ID", "Course", "Level", "Speciality", "Assigned Teacher ID");
		display += 		"----------------------------------------------------------------------------------------------------\n";

		for(Requirement c:RequirmentList) {
			display+=c.toString();
		}
		display +=      "============================================ End of Record =========================================\n";
		return display;
	}

	

}
