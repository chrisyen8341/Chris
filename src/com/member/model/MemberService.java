package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.album.model.Album;
import com.emp.model.Emp;
import com.pet.model.Pet;


public class MemberService {
	private MemberDAO_interface dao;
	
	public MemberService(){
		dao=new MemberDAO();
	}
	
	public Member addMember(String memId, String memPwd, String memName, String memSname, Integer memGender,
			String memIdNo, Date memBday, String memPhone, String memAddress, String memEmail, byte[] memImg,
			Integer memReported, Integer memStatus, Integer memRelation, String memSelfintro, Integer memFollowed,
			Integer memPoint, Integer memSaleRank, Double memLongtitude, Double memLatitude, Timestamp memLocTime,
			Integer memLocStatus) {
		
		Member member = new Member();
		member.setMemId(memId);
		member.setMemPwd(memPwd);
		member.setMemName(memName);
		member.setMemSname(memSname);
		member.setMemGender(memGender);
		member.setMemIdNo(memIdNo);
		member.setMemBday(memBday);
		member.setMemPhone(memPhone);
		member.setMemAddress(memAddress);
		member.setMemEmail(memEmail);
		member.setMemImg(memImg);
		member.setMemReported(memReported);
		member.setMemStatus(memStatus);
		member.setMemRelation(memRelation);
		member.setMemSelfintro(memSelfintro);
		member.setMemFollowed(memFollowed);
		member.setMemPoint(memPoint);
		member.setMemSaleRank(memSaleRank);
		member.setMemLongtitude(memLongtitude);
		member.setMemLatitude(memLatitude);
		member.setMemLocTime(memLocTime);
		member.setMemLocStatus(memLocStatus);
		dao.add(member);

		return member ;
	}

	public Member addMemberWithPet(String memId, String memPwd, String memName, String memSname, Integer memGender,
			String memIdNo, Date memBday, String memPhone, String memAddress, String memEmail, byte[] memImg,
			Integer memReported, Integer memStatus, Integer memRelation, String memSelfintro, Integer memFollowed,
			Integer memPoint, Integer memSaleRank, Double memLongtitude, Double memLatitude, Timestamp memLocTime,
			Integer memLocStatus,String petName, String petKind, Integer petGender, String petSpecies,
			String petIntro, Date petBday, byte[] petImg,Integer petStatus) {
		
		Member member = new Member();
		Pet pet=new Pet();
		member.setMemId(memId);
		member.setMemPwd(memPwd);
		member.setMemName(memName);
		member.setMemSname(memSname);
		member.setMemGender(memGender);
		member.setMemIdNo(memIdNo);
		member.setMemBday(memBday);
		member.setMemPhone(memPhone);
		member.setMemAddress(memAddress);
		member.setMemEmail(memEmail);
		member.setMemImg(memImg);
		member.setMemReported(memReported);
		member.setMemStatus(memStatus);
		member.setMemRelation(memRelation);
		member.setMemSelfintro(memSelfintro);
		member.setMemFollowed(memFollowed);
		member.setMemPoint(memPoint);
		member.setMemSaleRank(memSaleRank);
		member.setMemLongtitude(memLongtitude);
		member.setMemLatitude(memLatitude);
		member.setMemLocTime(memLocTime);
		member.setMemLocStatus(memLocStatus);
		pet.setPetName(petName);
		pet.setPetKind(petKind);
		pet.setPetGender(petGender);
		pet.setPetSpecies(petSpecies);
		pet.setPetIntro(petIntro);
		pet.setPetBday(petBday);
		pet.setPetImg(petImg);
		pet.setPetStatus(petStatus);
		dao.addWithPet(member, pet);

		return member ;
	}
	
	
	
	public Member updateMember(Integer memNo, String memId, String memPwd, String memName, String memSname, Integer memGender,
			String memIdNo, Date memBday, String memPhone, String memAddress, String memEmail, byte[] memImg,
			Integer memReported, Integer memStatus, Integer memRelation, String memSelfintro, Integer memFollowed,
			Integer memPoint, Integer memSaleRank, Double memLongtitude, Double memLatitude, Timestamp memLocTime,
			Integer memLocStatus) {

		Member member = new Member();
		member.setMemNo(memNo);
		member.setMemId(memId);
		member.setMemPwd(memPwd);
		member.setMemName(memName);
		member.setMemSname(memSname);
		member.setMemGender(memGender);
		member.setMemIdNo(memIdNo);
		member.setMemBday(memBday);
		member.setMemPhone(memPhone);
		member.setMemAddress(memAddress);
		member.setMemEmail(memEmail);
		member.setMemImg(memImg);
		member.setMemReported(memReported);
		member.setMemStatus(memStatus);
		member.setMemRelation(memRelation);
		member.setMemSelfintro(memSelfintro);
		member.setMemFollowed(memFollowed);
		member.setMemPoint(memPoint);
		member.setMemSaleRank(memSaleRank);
		member.setMemLongtitude(memLongtitude);
		member.setMemLatitude(memLatitude);
		member.setMemLocTime(memLocTime);
		member.setMemLocStatus(memLocStatus);
		dao.update(member);

		return member;
	}

	public void deleteMember(Integer memberNo) {
		dao.delete(memberNo);
	}

	public Member getOneMember(Integer memberNo) {
		return dao.findByPk(memberNo);
	}

	public List<Member> getAll() {
		return dao.getAll();
	}
	
	public Member getOneMemberById(String memId){
		return dao.findById(memId);
	}
	
	public List<Pet> getPetsByMemNo(Integer memno){
		return dao.findPetsByMemNo(memno);
	}
	
	public Set<Album> getAlbumsByMemNo(Integer memno){
		return dao.findAlbumsByMemNo(memno);
	}
	
	
	public List<Member> getMembersByIdName(String search){
		return dao.findByIdAndName(search);
	}
	
	public List<Member> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public Member getMemberByEmail(String memEmail) {
		return dao.findByEmail(memEmail);
	}
}
