package com.kh.pet.model.vo;

public class Pet {
	
	private int userNo;
	private String species;
	private String gender;
	private String petName;
	
	public Pet() {
		super();
	}

	public Pet(int userNo, String species, String gender, String petName) {
		super();
		this.userNo = userNo;
		this.species = species;
		this.gender = gender;
		this.petName = petName;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	@Override
	public String toString() {
		return "Pet [userNo=" + userNo + ", species=" + species + ", gender=" + gender + ", petName=" + petName + "]";
	}

	
	
	

}
