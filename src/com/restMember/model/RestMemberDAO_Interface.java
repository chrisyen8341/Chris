package com.restMember.model;

import java.util.List;

public interface RestMemberDAO_Interface {
	void add (RestMember restMember);
	void update (RestMember restMember);
	void delete (String restMemId);
	RestMember findByPK(String restMemId);
	List<RestMember> getAll();
}
