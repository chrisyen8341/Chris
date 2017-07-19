package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


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
		member.setMemId(memIdNo);
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

	public Member updateMember(Integer memNo, String memId, String memPwd, String memName, String memSname, Integer memGender,
			String memIdNo, Date memBday, String memPhone, String memAddress, String memEmail, byte[] memImg,
			Integer memReported, Integer memStatus, Integer memRelation, String memSelfintro, Integer memFollowed,
			Integer memPoint, Integer memSaleRank, Double memLongtitude, Double memLatitude, Timestamp memLocTime,
			Integer memLocStatus) {

		Member member = new Member();
		member.setMemNo(memNo);
		member.setMemId(memIdNo);
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
	
	
}
