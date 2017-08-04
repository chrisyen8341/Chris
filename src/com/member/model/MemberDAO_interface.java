package com.member.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.album.model.Album;
import com.emp.model.Emp;
import com.pet.model.Pet;

public interface MemberDAO_interface {
	void add(Member member);
	void addWithPet(Member member,Pet pet);
	void update(Member member);
	void delete(Integer memno);
	Member findByPk(Integer memno);
	List<Pet> findPetsByMemNo(Integer memno);
	Set<Album> findAlbumsByMemNo(Integer memno);
	List<Member> getAll();
	List<Member> findByIdAndName(String search);
	Member findByEmail(String memEmail);
	Member findById(String memId);
	List<Member> getAll(Map<String, String[]> map); 
	
}
