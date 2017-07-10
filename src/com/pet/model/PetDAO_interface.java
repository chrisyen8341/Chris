package com.pet.model;

import java.util.List;



public interface PetDAO_interface {
	void add(Pet pet);
	void update(Pet pet);
	void delete(int petNo);
	Pet findByPk(int petNo);
	List<Pet> getAll();
}
