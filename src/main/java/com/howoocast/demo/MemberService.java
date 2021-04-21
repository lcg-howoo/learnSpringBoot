package com.howoocast.demo;

import java.util.List;

import com.howoocast.demo.exception.EmptyValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public boolean create(Member member) {
		if (isInvalid(member.getId())) {
			return false;
		}
		if (isInvalid(member.getName())) {
			return false;
		}

		if (isInvalid(member.getPassword())) {
			return false;
		}
		if (isInvalid(member.getPhone())) {
			return false;
		}

		memberDAO.create(member);
		return true;
	}

	public Member findById(String id) {

		if (isInvalid(id)) {
			return null;
		}
		return memberDAO.findById(id);
	}

	public List<Member> findAll() {
		return memberDAO.findAll();
	}

	public boolean update(Member member) {
		if (isInvalid(member.getId())) {
			return false;
		}
		if (isInvalid(member.getName())) {
			return false;
		}

		if (isInvalid(member.getPassword())) {
			return false;
		}
		if (isInvalid(member.getPhone())) {
			return false;
		}

		memberDAO.update(member);
		return true;
	}

	public boolean delete(String id) {
		if (isInvalid(id)) {
			return false;
		}
		memberDAO.delete(id);
		return true;
	}

	private boolean isInvalid(String value) {
		if(value == null || value.trim().isEmpty()){
			throw new EmptyValueException();
		}
		return value == null || value.trim().isEmpty();
	}

}
