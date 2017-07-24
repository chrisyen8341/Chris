package com.pet.model;

import java.sql.Date;
import java.util.List;



public class PetService {
	
	private PetDAO_interface dao;
	
	public PetService(){
		dao=new PetDAO();
	}
	
	public Pet addPet(Integer memNo, String petName, String petKind, Integer petGender, String petSpecies,
			String petIntro, Date petBday, byte[] petImg,Integer petStatus) {
		
		Pet pet =new Pet();
		pet.setMemNo(memNo);
		pet.setPetName(petName);
		pet.setPetKind(petKind);
		pet.setPetGender(petGender);
		pet.setPetSpecies(petSpecies);
		pet.setPetIntro(petIntro);
		pet.setPetBday(petBday);
		pet.setPetImg(petImg);
		pet.setPetStatus(petStatus);	
		dao.add(pet);

		return pet ;
	}

	public Pet updateMember(Integer petNo, Integer memNo, String petName, String petKind, Integer petGender, String petSpecies,
			String petIntro, Date petBday, byte[] petImg,Integer petStatus) {

		Pet pet = new Pet();
		pet.setPetNo(petNo);
		pet.setMemNo(memNo);
		pet.setPetName(petName);
		pet.setPetKind(petKind);
		pet.setPetGender(petGender);
		pet.setPetSpecies(petSpecies);
		pet.setPetIntro(petIntro);
		pet.setPetBday(petBday);
		pet.setPetImg(petImg);
		pet.setPetStatus(petStatus);
		dao.update(pet);

		return pet;
	}

	public void deletePet(Integer petNo) {
		dao.delete(petNo);
	}

	public Pet getOnePet(Integer petNo) {
		return dao.findByPk(petNo);
	}

	public List<Pet> getAll() {
		return dao.getAll();
	}
}
