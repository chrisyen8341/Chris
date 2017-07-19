package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	void add(Member member);
	void update(Member member);
	void delete(int memno);
	Member findByPk(int memno);
	List<Member> getAll();
	Member findById(String memId);
}
